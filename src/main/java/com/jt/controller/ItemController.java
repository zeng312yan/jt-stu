package com.jt.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jt.pojo.Item;
import com.jt.pojo.SysResult;
import com.jt.service.ItemService;
import com.jt.vo.EasyUiData;
import com.jt.vo.ItemDesc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/item")
@Api(tags="商品模块") // Swagger-UI 描述在类上面，可以做分组。
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	
	//实现商品下架
	@RequestMapping("/instock")
    @ApiOperation(value = "实现商品下架") // 让Swagger-UI解析这个接口。
	public SysResult instock(Long[] ids) {
		try{
			int status=2;//下架
			itemService.updateStatus(ids,status);
			return SysResult.ok();
		} catch (Exception e) {

			return SysResult.fail();
		}
	}
	
	//实现商品上架
	@RequestMapping("/reshelf")
	@ApiOperation("实现商品上架")
	public SysResult reshelf(Long[] ids) {
		try {
			int status = 1;//上架
			itemService.updateStatus(ids,status);
			return SysResult.ok();
		} catch (Exception e) {
			return SysResult.fail();
		}
		
	}
	
	@RequestMapping("/delete")
	public SysResult deleteItem(Long[] ids) {
		
		try {
			itemService.deleteItems(ids);
			return SysResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.fail();
		}
	}
	
	@RequestMapping("/update")
	public SysResult updateItem(Item item,ItemDesc itemDesc) {
		
		try {
			itemService.updateItem(item,itemDesc);
			return SysResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.fail();
		}
	}
	
	@RequestMapping("/query")
	public EasyUiData findItemByPage(@PathParam("page")Integer page, @PathParam("rows")Integer rows) {
		
		EasyUiData itemList = itemService.findItemByPage(page, rows);
		return itemList;
	}
	
	@RequestMapping("/save")
	public SysResult saveItem(Item item,ItemDesc itemDesc) {
		
		try {
			itemService.saveItem(item,itemDesc);
			return SysResult.ok();
		} catch (Exception e) {
			
			e.printStackTrace();
			return SysResult.fail();
		}
		
	}
	
	@RequestMapping("/query/item/desc/{itemId}")
	public SysResult findItemDescById(@PathVariable Long itemId) {
		try {
			itemService.findItenDescById(itemId);
			return SysResult.ok();
		} catch (Exception e) {
			return SysResult.fail();
		}
		
	}
	
	
	
}
