import request from '@/utils/request'

export default{

  getTreeSubject(){
        return request({
            
             url: '/edu/subject/getTreeSubject',
            method: 'get',
           
          })
    },
    
}