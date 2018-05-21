package com.icd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icd.common.pojo.EasyUIDataGridResult;
import com.icd.common.utils.icdResult;
import com.icd.content.service.ContentService;
import com.icd.pojo.TbContent;

/**
 * 内容管理Controller
 * 
 * @author Hacker_Jp
 *
 */
@Controller
public class ContentController {

	@Autowired
	private ContentService contentService;

	@RequestMapping("/content/query/list")
	@ResponseBody
	public EasyUIDataGridResult getContentList(Long categoryId, Integer page, Integer rows) {
		EasyUIDataGridResult result = contentService.getContentList(categoryId, page, rows);
		return result;
	}

	@RequestMapping(value = "/content/save", method = RequestMethod.POST)
	@ResponseBody
	public icdResult addContent(TbContent tbContent) {
		icdResult result = contentService.addContent(tbContent);
		return result;
	}

	@RequestMapping(value = "/content/delete", method = RequestMethod.POST)
	@ResponseBody
	public void deleteContent(@PathVariable Long id) {
		contentService.deleteContent(id);
		return;
	}
}
