package com.icd.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 首页展示Controller
 * 
 * @author Hacker_Jp
 *
 */
@Controller
public class IndexController {

	// @Value("${CONTENT_LUNBO_ID}")
	// private Long CONTENT_LUNBO_ID;
	//
	// @Autowired
	// private ContentService contentService;

	@RequestMapping("/index")
	public String showIndex(Model model) {
		// // 查询内容列表
		// List<TbContent> ad1List =
		// contentService.getContentList(CONTENT_LUNBO_ID);
		// // 把结果传递给页面
		// model.addAttribute("ad1List", ad1List);
		return "index";
	}
}
