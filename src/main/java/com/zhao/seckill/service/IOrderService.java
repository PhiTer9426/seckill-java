package com.zhao.seckill.service;

import com.zhao.seckill.pojo.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhao.seckill.pojo.User;
import com.zhao.seckill.vo.GoodsVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhao
 * @since 2022-02-27
 */
public interface IOrderService extends IService<Order> {

  Order seckill(User user, GoodsVo goods);
}
