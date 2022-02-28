package com.zhao.seckill.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhao.seckill.pojo.Order;
import com.zhao.seckill.pojo.SeckillOrder;
import com.zhao.seckill.pojo.User;
import com.zhao.seckill.service.IGoodsService;
import com.zhao.seckill.service.IOrderService;
import com.zhao.seckill.service.ISeckillOrderService;
import com.zhao.seckill.vo.GoodsVo;
import com.zhao.seckill.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/seckill")
public class SeckillController {
  @Autowired
  private IGoodsService goodsService;
  @Autowired
  private ISeckillOrderService seckillOrderService;
  @Autowired
  private IOrderService orderService;

  @RequestMapping("/doSeckill")
  public String doSeckill(Model model, User user, Long goodsId) {
    if (null == user) {
      return "login";
    }
    model.addAttribute("user", user);
    GoodsVo goods = goodsService.findGoodsVoByGoodsId(goodsId);
    if (goods.getStockCount() < 1) {
      model.addAttribute("errmsg", RespBeanEnum.EMPTY_STOCK.getMessage());
      return "secKillFail";
    }

    SeckillOrder seckillOrder = seckillOrderService.getOne(new QueryWrapper<SeckillOrder>().eq("user_id", user.getId()).eq("goods_id", goodsId));
    if(seckillOrder!=null){
      model.addAttribute("errmsg",RespBeanEnum.REPEAT_ERROR);
      return "secKillFail";
    }
    Order order = orderService.seckill(user,goods);
    model.addAttribute("order",order);
    model.addAttribute("goods",goods);
    return "orderDetail";

  }
}
