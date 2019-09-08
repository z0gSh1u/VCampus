package tech.zxuuu.dao;

import java.util.List;
import java.util.Map;

import tech.zxuuu.entity.NoticeInfo;

public interface INoticeMapper {

	public List<NoticeInfo> getTop4Notice();

	public Integer getMaxId();

	public Boolean insertNewNotice(Map<String, String> map);

}
