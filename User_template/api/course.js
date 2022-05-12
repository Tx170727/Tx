import request from '@/utils/request'

export default {
    //查询前两条banner数据
    getCourseList(searchObj,current) {
    return request({
      url: '/edu/userCourse/getEduCoursePage/'+current,
      method: 'post',
      data:searchObj
    })
  },
  getAllSubject(){
    return request({
            
        url: '/edu/subject/getTreeSubject',
       method: 'get'
      
     })
  },
  getCourseInfo(courseId){
    return request({
            
        url: 'edu/userCourse/getEduCouresInfo/'+courseId,
       method: 'get'
      
     })
  }
}