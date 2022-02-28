package com.zhao.seckill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhao.seckill.mapper.GoodsMapper;
import com.zhao.seckill.pojo.Goods;
import com.zhao.seckill.service.IGoodsService;
import com.zhao.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhao
 * @since 2022-02-27
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

  @Autowired
  private GoodsMapper goodsMapper;

  @Override
  public List<GoodsVo> findGoodsVo() {
    return goodsMapper.findGoodsVo();
  }

  @Override
  public GoodsVo findGoodsVoByGoodsId(Long goodsId) {
    return goodsMapper.findGoodsVoById(goodsId);
  }
}
