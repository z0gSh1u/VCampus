package tech.zxuuu.server.messageQueue;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.alibaba.fastjson.JSON;

import tech.zxuuu.net.Request;
import tech.zxuuu.net.Response;
import tech.zxuuu.server.main.App;

/**
 * 处理服务器端请求消息队列中的请求
 * 
 * @author c1119
 */
public class RequestHandler extends Thread {

	@Override
	public void run() {

		while (true) {
			if (App.requestQueue.hasNext()) {

				// 消费顶端请求
				Request req = App.requestQueue.consume();
				
				System.out.println("When consuming, hash="+req.getHashCode());

				// 获取请求处理函数
				String targetApi = req.getTargetApi();
				// 取出方法名和全类名
				String[] parts = targetApi.split("\\.");
				String methodName = parts[parts.length - 1];
				String fullClassName = targetApi.substring(0, targetApi.length() - methodName.length() - 1);
				// 调用函数
				Class<?> clazz = null;
				try {
					// 获取clazz
					clazz = Class.forName(fullClassName);
					// 获取参数类型
					Class<?>[] paramTypes = null;
					Method[] methods = clazz.getMethods();
					// 扫描所有方法
					for (int i = 0; i < methods.length; i++) {
						// 匹配
						if (methods[i].getName().equals(methodName)) {
							Class<?>[] params = methods[i].getParameterTypes();
							paramTypes = new Class[params.length];
							for (int j = 0; j < params.length; j++) {
								paramTypes[j] = Class.forName(params[j].getName());
							}
						}
						break;
					}
					// 调用方法（接口），获取返回值
					// 注意，接口必须是静态的
					Method method = clazz.getMethod(methodName, paramTypes);
					Object[] processedParams = new Object[paramTypes.length];
					for (int i = 0; i < req.getParams().length; i++) {
						processedParams[i] = JSON.parseObject(req.getParams()[i].toString(), paramTypes[i]);
					}
					Object ret = method.invoke(null, processedParams);

					// 组织并发送响应
					new Response(req.getConnToClient(), req.getHashCode(), req.getTargetApi(), ret).send();

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}

	}

}
