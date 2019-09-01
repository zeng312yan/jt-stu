package com.jt.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.jt.pojo.BasePojo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName("tb_item_desc")
public class ItemDesc extends BasePojo{
	private Long itemId;
	private String itemDesc;
}
