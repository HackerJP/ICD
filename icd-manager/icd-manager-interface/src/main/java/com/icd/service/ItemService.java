package com.icd.service;

import com.icd.common.pojo.EasyUIDataGridResult;
import com.icd.common.utils.icdResult;
import com.icd.pojo.TbItem;

public interface ItemService {
	TbItem getItemById(long itemId);
	EasyUIDataGridResult getItemList(int page, int rows);
	icdResult addItem(TbItem item, String desc);
}
