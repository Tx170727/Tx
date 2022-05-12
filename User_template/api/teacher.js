import request from '@/utils/request'

export default {
    //查询前两条banner数据
    getTeacherList(page) {
    return request({
      url: '/edu/userTeacher/getTeacherList/'+page,
      method: 'get',
      
    })
  },
    getTeacherInfo(teacherId){
      return request({
        url: '/edu/userTeacher/getTeacherInfo/'+teacherId,
        method: 'get', 
    }) 
    }
}