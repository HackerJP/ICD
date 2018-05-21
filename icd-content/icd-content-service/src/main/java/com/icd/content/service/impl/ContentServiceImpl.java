package com.icd.content.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.icd.common.pojo.EasyUIDataGridResult;
import com.icd.common.utils.icdResult;
import com.icd.content.service.ContentService;
import com.icd.mapper.TbContentMapper;
import com.icd.pojo.TbContent;
import com.icd.pojo.TbContentExample;
import com.icd.pojo.TbContentExample.Criteria;

/**
 * 内容管理Service
 * 
 * @author Hacker_Jp
 *
 */
@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper contentMapper;

	@Override
	public EasyUIDataGridResult getContentList(long categoryId, int page, int rows) {

		// 设置分页信息
		PageHelper.startPage(page, rows);
		// 查询
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		List<TbContent> list = contentMapper.selectByExample(example);
		// 取分页信息
		PageInfo<TbContent> pageInfo = new PageInfo<>(list);
		// 创建返回结果对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setTotal(pageInfo.getTotal());
		result.setRows(list);

		return result;
	}

	@Override
	public icdResult addContent(TbContent content) {
		// 补全属性
		content.setCreated(new Date());
		content.setUpdated(new Date());
		// 插入数据
		contentMapper.insert(content);
		return icdResult.ok();

	}

	@Override
	public void deleteContent(long id) {
		contentMapper.deleteByPrimaryKey(id);
		return;
	}

	@Override
	public List<TbContent> getContentList(long cid) {
		// 根据分类id查询内容列表
		// 设置查询条件
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(cid);
		// 执行查询
		List<TbContent> list = contentMapper.selectByExample(example);
		return list;

	}

	// @Override
	// public List<TbContent> getContentList(long cid) {
	// //查询缓存
	// try {
	// String json = jedisClient.hget(CONTENT_KEY, cid + "");
	// //判断json是否为空
	// if (StringUtils.isNotBlank(json)) {
	// //把json转换成list
	// List<TbContent> list = JsonUtils.jsonToList(json, TbContent.class);
	// return list;
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// //根据cid查询内容列表
	// TbContentExample example = new TbContentExample();
	// //设置查询条件
	// Criteria criteria = example.createCriteria();
	// criteria.andCategoryIdEqualTo(cid);
	// //执行查询
	// List<TbContent> list = contentMapper.selectByExample(example);
	// //向缓存中添加数据
	// try {
	// jedisClient.hset(CONTENT_KEY, cid + "", JsonUtils.objectToJson(list));
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return list;
	// }

	// @Override
	// public icdResult addContent(TbContent content) {
	// // 补全属性
	// content.setCreated(new Date());
	// content.setUpdated(new Date());
	// // 插入数据
	// contentMapper.insert(content);
	// // 缓存同步
	// jedisClient.hdel(CONTENT_KEY, content.getCategoryId().toString());
	//
	// return icdResult.ok();
	// }

}
