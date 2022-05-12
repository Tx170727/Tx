import request from '@/utils/request'
export default {
    //1 生成统计数据
    createStaData(day) {
        return request({
            url: '/statistics/countStatistics/'+day,
            method: 'get'
          })
    },
    //2 获取统计数据
    getDataSta(searchObj) {
        return request({
            url: `/statistics/show`,
            method: 'post',
            data:searchObj
          })
    }
}