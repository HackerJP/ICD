package com.icd.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icd.common.pojo.EasyUITreeNode;
import com.icd.mapper.TbItemCatMapper;
import com.icd.pojo.TbItemCat;
import com.icd.pojo.TbItemCatExample;
import com.icd.pojo.TbItemCatExample.Criteria;
import com.icd.service.ItemCatService;

/**
 * 商品分类管理
 * @author Hacker_Jp
 *
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper itemCatMapper; 
	
	@Override
	public List<EasyUITreeNode> getCatList(long parentId) {
		
		//根据parentId查询节点列表
		TbItemCatExample example = new TbItemCatExample();
		//设置查询条件
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		//执行查询
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		//转换成EasyUITreeNode列表。
		List<EasyUITreeNode> resultList = new ArrayList<>();
		for (TbItemCat tbItemCat : list) {
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(tbItemCat.getId());
			node.setText(tbItemCat.getName());
			node.setState(tbItemCat.getIsParent()?"closed":"open");
			//添加到列表
			resultList.add(node);
		}
		//返回。
		return resultList;

	}

}
