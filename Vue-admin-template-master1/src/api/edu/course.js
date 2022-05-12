import request from '@/utils/request'

export default{

    getListTeacher(){
        return request({
            
             url: '/edu/teacher/findAll',
            method: 'get',
        
          })
    },
    addCourseInfo(courseInfo){
        return request({       
             url: '/edu/course/addCourse',
            method: 'post',
            data:courseInfo
          })
    },
    getCourseInfoId(id){
      return request({       
           url: '/edu/course/getCourse/'+id,
          method: 'get'
        })
  },
  updateCourseInfo(courseInfo){
    return request({       
         url: '/edu/course/updataCourse',
        method: 'post',
        data:courseInfo
      })
},
getSureCourseInfoId(id){
  return request({       
       url: '/edu/course/getSureCourse/'+id,
      method: 'get'
    })
},
publihCourse(courseId){
  return request({       
    url: '/edu/course/publishCourse/'+courseId,
   method: 'get'
 })
},
getListCourse(current,courseParemt){
  return request({       
    url: '/edu/course/getCourseList/'+current,
   method: 'post',
   data:courseParemt
 })
},
delCourse(courseId){
  return request({       
    url: '/edu/course/del/'+courseId,
   method: 'get'
   })
}
}