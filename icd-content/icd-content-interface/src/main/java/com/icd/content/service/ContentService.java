package com.icd.content.service;

import java.util.List;

import com.icd.common.pojo.EasyUIDataGridResult;
import com.icd.common.utils.icdResult;
import com.icd.pojo.TbContent;

public interface ContentService {

	EasyUIDataGridResult getContentList(long categoryId, int page, int rows);

	public icdResult addContent(TbContent content);

	public void deleteContent(long id);

	public List<TbContent> getContentList(long cid);
}
