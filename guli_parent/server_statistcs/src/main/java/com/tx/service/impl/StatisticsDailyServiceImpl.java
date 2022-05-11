package com.tx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tx.client.cmsClient;
import com.tx.entity.StatisticsDaily;
import com.tx.entity.vo.param.ShowParam;
import com.tx.mapper.StatisticsDailyMapper;
import com.tx.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-05-05
 */
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {
    @Autowired
    private StatisticsDailyMapper mapper;
    @Autowired
    private cmsClient cmsClient;
    @Override
    public int countstatistics(String date) {
        baseMapper.delete(new LambdaQueryWrapper<StatisticsDaily>().eq(StatisticsDaily::getDateCalculated,date));
        Integer integer = cmsClient.countResign(date);
        StatisticsDaily sta = new StatisticsDaily();
        sta.setDateCalculated(date);
        sta.setRegisterNum(integer);
        sta.setLoginNum(11);
        sta.setVideoViewNum(11);
        int insert = baseMapper.insert(sta);
        return insert;
    }

    @Override
    public HashMap<String, Object> show(ShowParam show) {
        QueryWrapper<StatisticsDaily> wr = new QueryWrapper<>();
        wr.select("date_calculated",show.getType());
        wr.between("date_calculated",show.getBegin(),show.getEnd());
        List<StatisticsDaily> statisticsDailies = baseMapper.selectList(wr);
        List<Object> dayList = new ArrayList<>();
        List<Integer> countList = new ArrayList<>();
        for (StatisticsDaily daily : statisticsDailies) {
            dayList.add(daily.getDateCalculated());
            switch(show.getType()){
                case "register_num": countList.add(daily.getRegisterNum());
                break;
                case "login_num" : countList.add(daily.getLoginNum());
                break;
                case "video_view_num": countList.add(daily.getVideoViewNum());
                break;
            }
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("date_calulatedList",dayList);
        map.put("numDataList",countList);
        return map;
    }
}
