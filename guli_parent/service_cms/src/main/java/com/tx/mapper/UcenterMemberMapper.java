package com.tx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tx.entity.UcenterMember;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2022-04-20
 */
public interface UcenterMemberMapper extends BaseMapper<UcenterMember> {

    Integer countResign(String day);
}
