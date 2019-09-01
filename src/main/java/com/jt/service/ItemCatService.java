package com.jt.service;

import java.util.List;

import com.jt.vo.EasyUiTree;

public interface ItemCatService {

	public String findItemCatNameById(Long itemCatId);

	public List<EasyUiTree> findItemCatByParentId(Long parentId);

}
