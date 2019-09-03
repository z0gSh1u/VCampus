package tech.zxuuu.dao;

import java.util.List;

import tech.zxuuu.entity.NoticeInfo;

public interface INoticeMapper {

	public List<NoticeInfo> getTop4Notice();

}
