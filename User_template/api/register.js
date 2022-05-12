import request from '@/utils/request'

export default {
    //查询前两条banner数据
    sendCode(mobile) {
    return request({
      url: '/cms/msm/sendCode/'+mobile,
      method: 'get',
      
    })
  },
    
  registerMember(params){
    return request({
        url: '/cms/ucenter-member/register',
        method: 'post',
        data: params
      })
  }



}