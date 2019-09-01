package com.jt.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jt.mapper.ItemDescMapper;
import com.jt.mapper.ItemMapper;
import com.jt.pojo.Item;
import com.jt.vo.EasyUiData;
import com.jt.vo.ItemDesc;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private ItemDescMapper itemDescMapper;

	

	@Override
	public EasyUiData findItemByPage(Integer page, Integer rows) {
		//获取商品数量总数
		int total = itemMapper.selectCount(null);
		
		/*
		 * 	2、.分页之后，回传数据
		 * 	sql: selcet * from tb_item limit 起始位置，查询行数
		 * 	第一页：20
		 * 	select * from tb_item limit 0,20
		 * 	第二页：20
		 * 	select * from tb_item limit 20,20
		 * 	第三页：20
		 * 	select * from tb_item limit 40,20
		 * 
		 * 	第N页
		 * 	select * from tb_item limit (N-1)*rows,rows
		 * 
		 */
		//计算起始位置
		int start  = (page-1)*rows;		
		List<Item> itemList = itemMapper.findItemByPage(start,rows); 
		return new EasyUiData(total,itemList);
	}


	
	@Transactional//添加事务控制
	@Override
	public void saveItem(Item item, ItemDesc itemDesc) {
		item.setStatus(1)
		.setCreated(new Date())
		.setUpdated(item.getCreated());		
		itemMapper.insert(item);
		
		//同时入库两张表
		itemDesc.setItemId(item.getId())
		.setCreated(item.getCreated())
		.setUpdated(item.getUpdated());
		
		itemDescMapper.insert(itemDesc);
		
		
	}



	@Override
	public void updateItem(Item item, ItemDesc itemDesc) {
		item.setUpdated(new Date());
		itemMapper.updateById(item);
		
		//同时修改两张表数据
		itemDesc.setItemId(item.getId())
		.setUpdated(item.getUpdated());
		
		itemDescMapper.updateById(itemDesc);
		
		
	}



	@Override
	public void deleteItems(Long[] ids) {
		
		//1.自动删除
		/*
		List<long[]> asList = Arrays.asList(ids);		
		itemMapper.deleteBatchIds(asList);
		*/
		//2.手动删除
		itemMapper.deleteItem(ids);
		
		//两张表一起删除
		List<Long> idList = Arrays.asList(ids);
		itemDescMapper.deleteBatchIds(idList);
		
		
	}



	/**
	 * sql:
	 * update tb_item
	 * set status = #{status},
	 * updated = #{updated}
	 * where id in (100,200,300...)
	 * 	  
	 */
	@Override
	public void updateStatus(Long[] ids, int status) {

		Item item = new Item();
		item.setStatus(status).setUpdated(new Date());
		List<Long> idList = Arrays.asList(ids);
		UpdateWrapper<Item> updateWrapper = new UpdateWrapper<>();
		updateWrapper.in("id", idList);
		itemMapper.update(item, updateWrapper);
	}



	@Override
	public void findItenDescById(Long itemId) {

		itemDescMapper.selectById(itemId);
	}



	
	
	
	
	
	
	
	
	
	
}
