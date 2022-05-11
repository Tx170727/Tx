package com.tx.service.impl;


import com.alibaba.nacos.common.utils.MD5Utils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tx.config.MyException;
import com.tx.entity.UcenterMember;
import com.tx.entity.Vo.RegisterParemt;
import com.tx.entity.Vo.UcenterMemberOrder;
import com.tx.mapper.UcenterMemberMapper;
import com.tx.service.UcenterMemberService;
import com.tx.utils.JwtUtils;
import com.tx.utils.PhoneNumCheck;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-04-20
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public String login(UcenterMember ucenterMember) {
        if (StringUtils.isEmpty(ucenterMember.getMobile()) || StringUtils.isEmpty(ucenterMember.getPassword())) {
            throw new MyException(5201, "请输入账号及密码");
        }
        if (!PhoneNumCheck.CheckMobilePhoneNum(ucenterMember.getMobile())) {
            throw new MyException(5202, "请输入正确的手机号码格式");
        }
        try {

            UcenterMember user =
                    baseMapper.selectOne(new LambdaQueryWrapper<UcenterMember>()
                            .eq(UcenterMember::getMobile, ucenterMember.getMobile())
                            .eq(UcenterMember::getPassword, MD5Utils.md5Hex(ucenterMember.getPassword().getBytes())));
            if (user == null) {
                throw new MyException(5201, "登陆失败,请检查用户名及密码");
            }

            if (user.getIsDisabled()) {
                throw new MyException(5201, "该账号已被冻结");
            }
            String jwtToken = JwtUtils.getJwtToken(user.getId(), user.getNickname());

            return jwtToken;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
      }

    @Override
    public Boolean register(RegisterParemt register) throws NoSuchAlgorithmException {
        String mobile = register.getMobile();
        String nickname = register.getNickname();
        String password = register.getPassword();
        String code = register.getCode();

        if(StringUtils.isEmpty(mobile)||StringUtils.isEmpty(nickname)||StringUtils.isEmpty(password)||StringUtils.isEmpty(code)){
           throw new MyException(20014,"请输入完整信息");
        }
        UcenterMember ucenterMember = baseMapper.selectOne(new LambdaQueryWrapper<UcenterMember>()
                                                            .eq(UcenterMember::getMobile, register.getMobile()));
        if( ucenterMember != null){
            throw new MyException(20012,"该号码已被注册");
        }
        Object o = redisTemplate.opsForValue().get(register.getMobile());
        System.out.println(o);
        if(!o.toString().equals(register.getCode())){
            throw new MyException(20013,"验证码错误");
        }
        UcenterMember ucen = new UcenterMember();
        BeanUtils.copyProperties(register,ucen);
        ucen.setPassword(MD5Utils.md5Hex(register.getPassword().getBytes()));
        ucen.setAvatar("http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83epMicP9UT6mVjYWdno0OJZkOXiajG0sllJTbGJ9DYiceej2XvbDSGCK8LCF7jv1PuG2uoYlePWic9XO8A/132");
        int insert = baseMapper.insert(ucen);

        return insert>0;
    }

    @Override
    public UcenterMember getUserByOpenid(String userInfoopenid) {

        UcenterMember ucenterMember = baseMapper.selectOne(new LambdaQueryWrapper<UcenterMember>()
                                                            .eq(UcenterMember::getOpenid, userInfoopenid)
                                                            .eq(UcenterMember::getIsDisabled, 0));
        return ucenterMember;
    }

    public UcenterMemberOrder get(String id){
        UcenterMember byId = baseMapper.selectById(id);
        UcenterMemberOrder ucenterMemberOrder = new UcenterMemberOrder();
        BeanUtils.copyProperties(byId, ucenterMemberOrder);
        return ucenterMemberOrder;
    }

    @Override
    public Integer countResign(String day) {
        Integer count=baseMapper.countResign(day);
        return count;
    }
}
