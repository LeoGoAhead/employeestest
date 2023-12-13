import request from '@/utils/request'

export const employeesLoginService = (loginData) => {
  return request({
    url: '/employees/login',
    method: 'post',
    params: loginData
  })
}

export const listSex = () => {
  return request({
    url: '/employees/listsex',
    method: 'post'
  })
}

export const listDepartment = () => {
  return request({
    url: '/employees/listdepartment',
    method: 'post'
  })
}

export const listDegreeList = () => {
  return request({
    url: '/employees/listdegree',
    method: 'post'
  })
}

export const listEmployees = (data) => {
  return request({
    url: '/employees/listemployees',
    method: 'post',
    params: data
  })
}

export const createEmployees = (data) => {
  return request({
    url: '/employees/addemployees',
    method: 'post',
    params: data
  })
}

export const updateEmployees = (data) => {
  return request({
    url: '/employees/updateemployees',
    method: 'post',
    params: data
  })
}

export const deleteEmployees = (id) => {
  return request({
    url: '/employees/deleteemployees',
    method: 'post',
    params: { id: id }
  })
}
