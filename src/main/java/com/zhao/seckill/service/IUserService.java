package com.zhao.seckill.service;

import com.zhao.seckill.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhao.seckill.vo.LoginVO;
import com.zhao.seckill.vo.RespBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhao
 * @since 2022-02-25
 */
public interface IUserService extends IService<User> {
  RespBean doLogin(LoginVO loginVO, HttpServletRequest request, HttpServletResponse response);

  User getUserByCookie(String userTicket, HttpServletRequest request, HttpServletResponse response);
}
