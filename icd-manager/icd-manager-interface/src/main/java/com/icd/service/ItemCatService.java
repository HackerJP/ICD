package com.icd.service;

import java.util.List;

import com.icd.common.pojo.EasyUITreeNode;

public interface ItemCatService {

	List<EasyUITreeNode> getCatList(long parentId);
}
