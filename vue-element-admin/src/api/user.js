import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/account/login',
    method: 'post',
    params: data
  })
}

export function getInfo(token) {
  return request({
    url: '/account/userinfo',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/account/logout',
    method: 'post'
  })
}
