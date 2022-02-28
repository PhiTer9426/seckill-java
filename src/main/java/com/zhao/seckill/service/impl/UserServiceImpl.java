package com.zhao.seckill.service.impl;

import com.zhao.seckill.exception.GlobalException;
import com.zhao.seckill.pojo.User;
import com.zhao.seckill.mapper.UserMapper;
import com.zhao.seckill.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhao.seckill.utils.CookieUtil;
import com.zhao.seckill.utils.MD5Util;
import com.zhao.seckill.utils.UUIDUtil;
import com.zhao.seckill.utils.ValidatorUtil;
import com.zhao.seckill.vo.LoginVO;
import com.zhao.seckill.vo.RespBean;
import com.zhao.seckill.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhao
 * @since 2022-02-25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

  @Autowired
  private UserMapper userMapper;
  @Autowired
  private RedisTemplate redisTemplate;

  @Override
  public RespBean doLogin(LoginVO loginVO, HttpServletRequest request, HttpServletResponse response) {
    String mobile = loginVO.getMobile();
    String password = loginVO.getPassword();
//    if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
//      return RespBean.error(RespBeanEnum.LOGIN_ERROR);
//    }
//    if(!ValidatorUtil.isMobile(mobile)) {
//      return RespBean.error(RespBeanEnum.LOGIN_MOBILE_ERROR);
//    }
    User user = userMapper.selectById(mobile);
    if(null == user) {
      throw new GlobalException(RespBeanEnum.LOGIN_NO_USER_ERROR);
//      return RespBean.error(RespBeanEnum.LOGIN_NO_USER_ERROR);
    }
    if(!MD5Util.fromPwdToDBPwd(password,user.getSalt()).equals((user.getPassword()))) {
//      return RespBean.error(RespBeanEnum.LOGIN_ERROR);
      throw new GlobalException(RespBeanEnum.LOGIN_ERROR);
    }

//    cookie
    String ticket = UUIDUtil.uuid();
//    将用户信息存入redis
    redisTemplate.opsForValue().set("user:" + ticket, user);
//    request.getSession().setAttribute(ticket, user);
    CookieUtil.setCookie(request, response, "userTicket", ticket);
    return RespBean.success();
  }

  @Override
  public User getUserByCookie(String userTicket, HttpServletRequest request, HttpServletResponse response) {
    if (!StringUtils.hasLength(userTicket)) {
      return null;
    }
    User user = (User) redisTemplate.opsForValue().get("user:" + userTicket);
    if(user != null) {
      CookieUtil.setCookie(request, response, "userTicket", userTicket);
    }
    return user;
  }
}
