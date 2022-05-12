import request from '@/utils/request'

export default {
    getHostTeacherList() {
    return request({
      url: 'edu/UserEdu/hostTeacherlist',
      method: 'get'
    })
  },
  getHostCourseList() {
    return request({
      url: 'edu/UserEdu/hostCourseList',
      method: 'get'
    })
  },

}