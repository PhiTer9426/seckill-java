package com.zhao.seckill.controller;

import com.zhao.seckill.pojo.User;
import com.zhao.seckill.service.IGoodsService;
import com.zhao.seckill.service.IUserService;
import com.zhao.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/goods")
public class GoodsController {

  @Autowired
  private IGoodsService goodsService;

  @RequestMapping("/toList")
  public String toList(Model model, User user) {
    if (null == user) {
      return "login";
    }
    model.addAttribute("user", user);
    model.addAttribute("goodsList", goodsService.findGoodsVo());
    return "goodsList";
  }

  @RequestMapping("/toDetail/{goodsId}")
  public String toDetail(Model model, User user, @PathVariable Long goodsId) {
    model.addAttribute("user", user);
    GoodsVo goodsVo = goodsService.findGoodsVoByGoodsId(goodsId);
    Date startDate = goodsVo.getStartDate();
    Date endDate = goodsVo.getEndDate();
    Date nowDate = new Date();
    //秒杀状态
    int secKillStatus = 0;
    //秒杀倒计时
    int remainSeconds = 0;
    //秒杀还未开始
    if (nowDate.before(startDate)) {
      remainSeconds = ((int) ((startDate.getTime() - nowDate.getTime()) / 1000));
    } else if (nowDate.after(endDate)) {
      //	秒杀已结束
      secKillStatus = 2;
      remainSeconds = -1;
    } else {
      //秒杀中
      secKillStatus = 1;
      remainSeconds = 0;
    }
    model.addAttribute("remainSeconds", remainSeconds);
    model.addAttribute("secKillStatus", secKillStatus);
    model.addAttribute("goods", goodsVo);
    return "goodsDetail";
  }
}
