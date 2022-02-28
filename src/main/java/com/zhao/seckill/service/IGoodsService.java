package com.zhao.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhao.seckill.pojo.Goods;
import com.zhao.seckill.vo.GoodsVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhao
 * @since 2022-02-27
 */
public interface IGoodsService extends IService<Goods> {
  List<GoodsVo> findGoodsVo();

  GoodsVo findGoodsVoByGoodsId(Long goodsId);
}
