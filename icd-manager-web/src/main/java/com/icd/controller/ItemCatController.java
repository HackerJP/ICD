package com.icd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icd.common.pojo.EasyUITreeNode;
import com.icd.service.ItemCatService;

/**
 * 商品分类管理Controller
 * @author Hacker_Jp
 *
 */
@Controller
public class ItemCatController {

	@Autowired
	private ItemCatService itemCatService;
	
	@RequestMapping("/item/cat/list")
	@ResponseBody
	public List<EasyUITreeNode> getItemCatList(@RequestParam(name="id", defaultValue="0") Long parentId){
		List<EasyUITreeNode> list = itemCatService.getCatList(parentId);
		return list;
	}
}
