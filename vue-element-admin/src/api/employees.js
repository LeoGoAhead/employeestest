import request from '@/utils/request'

export const employeesLoginService = (loginData) => {
  return request({
    url: '/employees/login',
    method: 'post',
    params: loginData
  })
}
