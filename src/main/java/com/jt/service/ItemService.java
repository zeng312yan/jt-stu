package com.jt.service;

import com.jt.pojo.Item;
import com.jt.vo.EasyUiData;
import com.jt.vo.ItemDesc;

public interface ItemService {
	
	 EasyUiData findItemByPage(Integer page, Integer rows);

	void saveItem(Item item, ItemDesc itemDesc);

	void updateItem(Item item, ItemDesc itemDesc);

	void deleteItems(Long[] ids);

	void updateStatus(Long[] ids, int status);

	void findItenDescById(Long itemId);



}
