package com.icd.content.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icd.common.pojo.EasyUITreeNode;
import com.icd.common.utils.icdResult;
import com.icd.content.service.ContentCategoryService;
import com.icd.mapper.TbContentCategoryMapper;
import com.icd.pojo.TbContentCategory;
import com.icd.pojo.TbContentCategoryExample;
import com.icd.pojo.TbContentCategoryExample.Criteria;

/**
 * 内容分类管理Service
 * 
 * @author Hacker_Jp
 *
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

	@Autowired
	private TbContentCategoryMapper contentCategoryMapper;

	@Override
	public List<EasyUITreeNode> getContentCatList(long parentId) {
		// 根据parentId查询子节点列表
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		// 设置查询条件
		criteria.andParentIdEqualTo(parentId);
		// 执行查询
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
		// 转成EasyUITreeNode列表
		List<EasyUITreeNode> nodeList = new ArrayList<EasyUITreeNode>();
		for (TbContentCategory tbContentCategory : list) {
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(tbContentCategory.getId());
			node.setText(tbContentCategory.getName());
			node.setState(tbContentCategory.getIsParent() ? "closed" : "open");
			// 添加到列表
			nodeList.add(node);
		}
		return nodeList;
	}

	@Override
	public icdResult addContentCategory(long parentId, String name) {

		// 创建一个TbContentCategory对象
		TbContentCategory tbContentCategory = new TbContentCategory();
		// 补全TbContentCategory对象的属性
		tbContentCategory.setIsParent(false);
		tbContentCategory.setName(name);
		tbContentCategory.setParentId(parentId);
		// 排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数
		tbContentCategory.setSortOrder(1);
		// 状态。可选值:1(正常),2(删除)
		tbContentCategory.setStatus(1);
		tbContentCategory.setCreated(new Date());
		tbContentCategory.setUpdated(new Date());

		// 向tb_content_category表中插入数据
		contentCategoryMapper.insert(tbContentCategory);

		// 判断父节点的isparent是否为true，不是true需要改为true。
		TbContentCategory parentNode = contentCategoryMapper.selectByPrimaryKey(parentId);
		if (!parentNode.getIsParent()) {
			parentNode.setIsParent(true);
			// 更新父节点
			contentCategoryMapper.updateByPrimaryKey(parentNode);
		}
		// 需要主键返回。
		// 返回icdResult，其中包装TbContentCategory对象
		return icdResult.ok(tbContentCategory);

	}

	@Override
	public icdResult updateContentCategory(long id, String name) {

		TbContentCategory tbContentCategory = contentCategoryMapper.selectByPrimaryKey(id);
		tbContentCategory.setName(name);
		contentCategoryMapper.updateByPrimaryKey(tbContentCategory);
		return icdResult.ok(tbContentCategory);
	}

	@Override
	public void deleteContentCategory(long id) {
		contentCategoryMapper.deleteByPrimaryKey(id);
		return;
	}

}
