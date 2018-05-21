package com.icd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icd.common.pojo.EasyUITreeNode;
import com.icd.common.utils.icdResult;
import com.icd.content.service.ContentCategoryService;

/**
 * 内容管理Controller
 * 
 * @author Hacker_Jp
 *
 */
@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {

	@Autowired
	private ContentCategoryService contentCategoryService;

	@RequestMapping("/list")
	@ResponseBody
	public List<EasyUITreeNode> getContentCatList(@RequestParam(name = "id", defaultValue = "0") Long parentId) {

		List<EasyUITreeNode> nodeList = contentCategoryService.getContentCatList(parentId);
		return nodeList;
	}

	/**
	 * 添加分类节点
	 * 
	 * @param parentId
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public icdResult createCategory(Long parentId, String name) {
		icdResult result = contentCategoryService.addContentCategory(parentId, name);
		return result;
	}

	/**
	 * 修改分类节点
	 *
	 * @param parentId
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public icdResult updateCategory(Long parentId, String name) {
		icdResult result = contentCategoryService.updateContentCategory(parentId, name);
		return result;
	}

	/**
	 * 删除分类节点
	 * 
	 * @param id
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public void deleteCategory(Long id) {
		contentCategoryService.deleteContentCategory(id);
	}
}
