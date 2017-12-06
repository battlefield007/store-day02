package cn.e3.manager.service;

import cn.e3.pojo.TbItem;
import cn.e3.utils.DatagridPagebean;

public interface ItemService {
	
	/**
	 * 需求:根据id查询商品数据
	 */
	public TbItem findItemByID(Long itemId);

	public DatagridPagebean findItemListByPage(Integer page, Integer rows);

}
