import request from '@/utils/request'

export default {
    //查询前两条banner数据
    getPlayAuth(id) {
    return request({
      url: '/oss/getPlay/'+id,
      method: 'get'
    })
  },
}