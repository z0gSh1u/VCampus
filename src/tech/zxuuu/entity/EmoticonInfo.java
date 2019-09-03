package tech.zxuuu.entity;

/**
 * 标签类（聊天室用）
 */
public class EmoticonInfo {
	private String name;
	private String addr;
	
	public EmoticonInfo() {
		this("", "");
	}
	public EmoticonInfo(String name, String addr) {
		this.name = name;
		this.addr = addr;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
}
