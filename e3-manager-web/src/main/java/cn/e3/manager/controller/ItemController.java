package cn.e3.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3.manager.service.ItemService;
import cn.e3.pojo.TbItem;
import cn.e3.utils.DatagridPagebean;

@Controller
public class ItemController {
	
	//注入service服务对象
	@Autowired
	private ItemService itemService;
	
	/**
	 * 需求:根据id查询商品数据
	 * 请求:item/list/{itemId}
	 */
//	@RequestMapping("item/list/{itemId}")
	@RequestMapping("item/list/{itemId}")
	@ResponseBody
	public TbItem findItemByID(@PathVariable Long itemId){
		//调用service服务方法
		TbItem item = itemService.findItemByID(itemId);
		return item;
	}
	/**
	 * 需求:根据id查询商品数据
	 * 请求:item/list/{itemId}
	 */
//	@RequestMapping("item/list")//===404==少个“/”
//	@RequestMapping("/item/list")//===和findItemByID 匹配地址是不一样的。不会冲突   ===
	@RequestMapping("/item/listPage")//===匹配地址不一样。不会冲突
	@ResponseBody
	//ajax分页参数：page、rows
	//====restFull===######【传参格式不对】404。默认?param1=xxx&param2=yyy
	//public DatagridPagebean findItemListByPage(@PathVariable(value="1") Integer page,@PathVariable(value="30") Integer rows){//===页面30条
	public DatagridPagebean findItemListByPage(@RequestParam(defaultValue="1") Integer page,
			@RequestParam(defaultValue="30") Integer rows){
		//调用service服务方法
		DatagridPagebean bean = itemService.findItemListByPage(page,rows);
		return bean;
	}

}
