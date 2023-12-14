import request from '@/utils/request'

export const downloadExcel = (url, data) => {
  return request({
    url: url,
    method: 'post',
    params: data
  })
}
