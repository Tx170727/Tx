import request from '@/utils/request'

export default {
    createOrders(id) {
    return request({
      url: '/cms/order/createOrder/'+id,
      method: 'post',
      
    })
  },
  getOrdersInfo(id) {
    return request({
      url: '/cms/order/getOdrderById/'+id,
      method: 'get',
      
    })
  },
  createNatvie(id){
    return request({
      url: '/cms/paylog/createNavite/'+id,
      method: 'get',
    })
  },
  queryPayStatus(id){
    return request({
      url: '/cms/paylog/getPayStatus/'+id,
      method: 'get',
    })
  }
}