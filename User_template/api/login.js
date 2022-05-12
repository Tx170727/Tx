import request from '@/utils/request'

export default {
    //查询前两条banner数据
    submitLoginUser(user) {
    return request({
      url: '/cms/ucenter-member/login',
      method: 'post',
      data:user
    })
  },
  getLoginUserInfo(){
    return request({
        url: '/cms/ucenter-member/getJwtInfo',
        method: 'get',
       
      })
  }
}