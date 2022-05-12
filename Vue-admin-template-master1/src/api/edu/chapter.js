import request from '@/utils/request'

export default{

    getAllChapterVideo(id){
        return request({
        
             url: '/edu/chapter/get/'+id,
             method: 'get',
        
          })
    },
    addChapter(chapter){
        return request({
             url: '/edu/chapter/saveChapter/',
             method: 'post',
             data:chapter
          })
    },
    getChapter(id){
        return request({     
             url: '/edu/chapter/'+id,
             method: 'get',
          })
    },
    updateChapter(chapter){
        return request({
             url: '/edu/chapter/updateChapter/',
             method: 'post',
             data:chapter
          })
    },
    deleteChapter(id){
        return request({
            url: '/edu/chapter/del/'+id,
            method: 'get',
         })
    },
    deleteVideo(id){
        return request({
            url: '/oss/delVideo/'+id,
            method: 'get',
           
         })
    }
}