package com.zhao.seckill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhao.seckill.pojo.Goods;
import com.zhao.seckill.vo.GoodsVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhao
 * @since 2022-02-27
 */
public interface GoodsMapper extends BaseMapper<Goods> {
  List<GoodsVo> findGoodsVo();

  GoodsVo findGoodsVoById(Long goodsId);
}
