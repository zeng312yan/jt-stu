package com.jt.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class EasyUiTree {
	
	private Long id;	//节点id值
	private String text;	//名称
	private String state;	//节点状态值 closed,open

}
