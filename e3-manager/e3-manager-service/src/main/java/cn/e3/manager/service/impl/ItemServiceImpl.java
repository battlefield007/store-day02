package cn.e3.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.e3.manager.service.ItemService;
import cn.e3.mapper.TbItemMapper;
import cn.e3.pojo.TbItem;
import cn.e3.pojo.TbItemExample;
import cn.e3.utils.DatagridPagebean;
@Service
public class ItemServiceImpl implements ItemService {
	
	//注入商品mapper接口代理对象
	@Autowired
	private TbItemMapper itemMapper;

	/**
	 * 需求:根据id查询商品数据
	 */
	public TbItem findItemByID(Long itemId) {
		// 根据主键查询商品数据
		TbItem item = itemMapper.selectByPrimaryKey(itemId);
		return item;
	}

	//===分页查询。===service使用分页插件
	@Override
	public DatagridPagebean findItemListByPage(Integer page, Integer rows) {
		//===单表，使用mybatis逆向工程产生的example类===分装条件，使用生成的API
		TbItemExample example = new TbItemExample();
//		example.setDistinct(distinct);//分页条件使用插件。===不能封装到example。
		PageHelper.startPage(page, rows);//====分页条件使用插件
		List<TbItem> list = itemMapper.selectByExample(example);//===查询前加句话
		
		
		//===封装返回的result
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		//====pageInfo没有total和rows两个属性，所以需要自定义 PageBean:DatagridPagebean
		DatagridPagebean pagebean = new DatagridPagebean();
		pagebean.setRows(list);
		long total = pageInfo.getTotal();
		pagebean.setTotal(total);
		
		return pagebean;
	}

}
