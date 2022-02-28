package com.zhao.seckill.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhao
 * @since 2022-02-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID 手机号
     */
    private Long id;

    private String nickname;

    /**
     * MD5(MD5(pass + 固定salt)+固定salt)
     */
    private String password;

    private String salt;

    /**
     * 头像
     */
    private String avatar;

    private Date registerDate;

    private Date lastLoginDate;

    private Integer loginCount;


}
