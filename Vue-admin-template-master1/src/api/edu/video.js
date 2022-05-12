import request from '@/utils/request'

export default{

    addVideo(video){
        return request({
            
             url: '/edu/video/saveVideo',
            method: 'post',
            data:video
          })
    },
    deleteVideo(id){
        return request({
            
             url: '/edu/video/del/'+id,
            method: 'get',
          
          })
    },
    updateVideo(video){
        return request({
            
            url: '/edu/video/updateVideo',
           method: 'post',
            data: video
         })
    },
     getVideo(videoId){
        return request({
            
            url: '/edu/video/get/'+videoId,
           method: 'get',
         })
    },

}