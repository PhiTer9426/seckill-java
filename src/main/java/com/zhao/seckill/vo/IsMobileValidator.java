package com.zhao.seckill.vo;

import com.zhao.seckill.utils.ValidatorUtil;
import com.zhao.seckill.validator.IsMobile;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsMobileValidator implements ConstraintValidator<IsMobile,String> {

    private boolean required = false;

    @Override
    public void initialize(IsMobile constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if(required){
            return ValidatorUtil.isMobile(value);
        }else {
            if(StringUtils.hasLength(value)){
                return true;
            }
            else{
                return ValidatorUtil.isMobile(value);
            }
        }
    }
}
