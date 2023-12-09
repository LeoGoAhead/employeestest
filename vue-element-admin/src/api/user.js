import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/employees/login',
    method: 'post',
    params: data
  })
}

export function getInfo(token) {
  return request({
    url: '/employees/userinfo',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/employees/logout',
    method: 'post'
  })
}
