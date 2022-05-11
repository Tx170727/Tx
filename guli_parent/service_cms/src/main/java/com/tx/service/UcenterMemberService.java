package com.tx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tx.entity.UcenterMember;
import com.tx.entity.Vo.RegisterParemt;
import com.tx.entity.Vo.UcenterMemberOrder;

import java.security.NoSuchAlgorithmException;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-04-20
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    String login(UcenterMember ucenterMember);

    Boolean register(RegisterParemt register) throws NoSuchAlgorithmException;

    UcenterMember getUserByOpenid(String userInfoopenid);

    UcenterMemberOrder get(String id);

    Integer countResign(String day);
}
