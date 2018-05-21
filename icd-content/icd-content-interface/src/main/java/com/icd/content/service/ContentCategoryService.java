package com.icd.content.service;

import java.util.List;

import com.icd.common.pojo.EasyUITreeNode;
import com.icd.common.utils.icdResult;

public interface ContentCategoryService {

	List<EasyUITreeNode> getContentCatList(long parentId);

	icdResult addContentCategory(long parentId, String name);

	icdResult updateContentCategory(long id, String name);

	void deleteContentCategory(long id);
}
