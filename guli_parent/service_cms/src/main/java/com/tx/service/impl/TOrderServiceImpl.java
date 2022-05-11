package com.tx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tx.entity.TOrder;
import com.tx.entity.Vo.CourseInfoVo;
import com.tx.entity.Vo.UcenterMemberOrder;
import com.tx.mapper.TOrderMapper;
import com.tx.service.TOrderService;
import com.tx.service.UcenterMemberService;
import com.tx.utils.OrderNoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-04-27
 */
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements TOrderService {
    @Autowired
    private com.tx.client.client client;
    @Autowired
    private UcenterMemberService ucenterMemberService;

    @Override
    public String create(String courseid, String memberIdByJwtToken) {
        CourseInfoVo courseInfo = client.getCourseInfo(courseid);
        UcenterMemberOrder ucenterMemberOrder = ucenterMemberService.get(memberIdByJwtToken);
        TOrder tOrder = new TOrder();
        tOrder.setOrderNo(OrderNoUtil.getOrderNo());
        tOrder.setCourseId(courseid);
        tOrder.setTeacherName(courseInfo.getTeahcerName());
        tOrder.setCourseTitle(courseInfo.getTitle());
        tOrder.setCourseCover(courseInfo.getCover());
        tOrder.setTotalFee(courseInfo.getPrice());
        tOrder.setMemberId(memberIdByJwtToken);
        tOrder.setMobile(ucenterMemberOrder.getMobile());
        tOrder.setNickname(ucenterMemberOrder.getNickname());
        tOrder.setStatus(0);
        tOrder.setPayType(1);

        int insert = baseMapper.insert(tOrder);

        return tOrder.getOrderNo();

    }

    @Override
    public Boolean isBuyCourse(String memberId, String courseId) {
        Integer integer = baseMapper.selectCount(new LambdaQueryWrapper<TOrder>().eq(TOrder::getCourseId, courseId)
                .eq(TOrder::getMemberId, memberId)
                .eq(TOrder::getStatus, 1));
        System.out.println(integer);
        if(integer==0){
            return false;
        }
            return  true;
    }



}
