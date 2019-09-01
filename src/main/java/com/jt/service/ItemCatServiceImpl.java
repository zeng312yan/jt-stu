package com.jt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.ItemCatMapper;
import com.jt.pojo.ItemCat;
import com.jt.vo.EasyUiTree;

@Service
public class ItemCatServiceImpl implements ItemCatService {
	
	@Autowired
	private ItemCatMapper itemCatMapper; 

	@Override
	public String findItemCatNameById(Long itemCatId) {
		ItemCat itemCat = itemCatMapper.selectById(itemCatId);
		System.out.println("itemCat: "+ itemCat);
		return itemCat.getName();
	}

	/**
	 * 
	 * 	1.根据parentId查询数据库记录返回itemCatList集合
	 * 	2.将itemCatList集合中的数据按照指定的格式封装为List<EasyUiTree>
	 */
	@Override
	public List<EasyUiTree> findItemCatByParentId(Long parentId) {
		List<ItemCat> cartList = findItemCatList(parentId);
		List<EasyUiTree> treeList = new ArrayList<>();
		//遍历集合数据,实现数据的转化
		for(ItemCat itemCat: cartList) {
			EasyUiTree uiTree = new EasyUiTree();
			uiTree.setId(itemCat.getId());
			uiTree.setText(itemCat.getName());
			String state = itemCat.getIsParent()?"closed":"open";
			//如果是父级则closed，不是则open
			uiTree.setState(state);
			treeList.add(uiTree);
		}
		return treeList;
	}

	private List<ItemCat> findItemCatList(Long parentId) {
		QueryWrapper<ItemCat> queryWrapper = new QueryWrapper<ItemCat>();
		queryWrapper.eq("parent_id", parentId);
		return itemCatMapper.selectList(queryWrapper);
	}

}
