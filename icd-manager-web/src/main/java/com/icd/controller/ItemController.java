package com.icd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icd.common.pojo.EasyUIDataGridResult;
import com.icd.common.utils.icdResult;
import com.icd.pojo.TbItem;
import com.icd.service.ItemService;

/**
 * 商品管理Controller
 * @author Hacker_Jp
 *
 */
@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/item/{itemId}")
	@ResponseBody
	private TbItem getItemById(@PathVariable Long itemId) {
		TbItem tbItem = itemService.getItemById(itemId);
		return tbItem;
	}
	
	@RequestMapping("/item/list")
	@ResponseBody
	public EasyUIDataGridResult getItemList(Integer page, Integer rows) {
		EasyUIDataGridResult result = itemService.getItemList(page, rows);
		return result;
	}

	@RequestMapping(value="/item/save", method=RequestMethod.POST)
	@ResponseBody
	public icdResult addItem(TbItem item, String desc){
		icdResult result = itemService.addItem(item, desc);
		return result;
	}
}
