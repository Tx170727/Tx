import request from '@/utils/request'

export default{

    getTeacherPage(current,pageParemt){
        return request({
            
             url: '/edu/teacher/page/'+current,
            method: 'post',
            data: pageParemt
          })
    },
    deleteTeacher(id){
        return request({
            
             url: '/edu/teacher/delete/'+id,
            method: 'get'
          })
    },
    addTeacher(teacher){
        return request({
            
             url: '/edu/teacher/inserTeacher',
            method: 'post',
            data:teacher
          })
    },
    findTeacherById(id){
        return request({
            
             url: '/edu/teacher/get/'+id,
            method: 'get',
          })
    },
    updateTeacherInfo(teacher){
        return request({
            
             url: '/edu/teacher/updateTeacher',
            method: 'post',
            data:teacher
          })
    }
}