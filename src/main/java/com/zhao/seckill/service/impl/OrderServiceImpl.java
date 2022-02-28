package com.zhao.seckill.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhao.seckill.mapper.OrderMapper;
import com.zhao.seckill.pojo.Order;
import com.zhao.seckill.pojo.SeckillGoods;
import com.zhao.seckill.pojo.SeckillOrder;
import com.zhao.seckill.pojo.User;
import com.zhao.seckill.service.IOrderService;
import com.zhao.seckill.service.ISeckillGoodsService;
import com.zhao.seckill.service.ISeckillOrderService;
import com.zhao.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

  @Autowired
  private ISeckillGoodsService seckillGoodsService;
  @Autowired
  private OrderMapper orderMapper;
  @Autowired
  private ISeckillOrderService seckillOrderService;

  @Override
  public Order seckill(User user, GoodsVo goods) {
    SeckillGoods seckillGoods = seckillGoodsService.getOne(new QueryWrapper<SeckillGoods>().eq("goods_id", goods.getId()));
    seckillGoods.setStockCount(seckillGoods.getStockCount() - 1);
    seckillGoodsService.updateById(seckillGoods);

    Order order = new Order();
    order.setUserId(user.getId());
    order.setGoodsId(goods.getId());
    order.setDeliveryAddrId(0L);
    order.setGoodsName(goods.getGoodsName());
    order.setGoodsCount(1);
    order.setGoodsPrice(seckillGoods.getSeckillPrice());
    order.setOrderChannel(1);
    order.setStatus(0);
    order.setCreateDate(new Date());
    orderMapper.insert(order);

    SeckillOrder seckillOrder = new SeckillOrder();
    seckillOrder.setUserId(user.getId());
    seckillOrder.setOrderId(order.getId());
    seckillOrder.setGoodsId(goods.getId());
    seckillOrderService.save(seckillOrder);

    return order;
  }
}
