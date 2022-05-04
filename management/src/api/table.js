import request from '@/utils/request'

export function getList(pn, limit) {
  return request({
    url: '/task/checking',
    method: 'get',
    params: {
      pn: pn,
      limit: limit
    }
  })
}

export function getDamageImage(id) {
  return request({
    url: '/damage_image/get',
    method: 'get',
    params: {
      id: id
    }
  })
}

export function getTaskImage(id) {
  return request({
    url: '/task/task_image',
    method: 'get',
    params: {
      id: id
    }
  })
}

export function submit(id) {
  return request({
    url: '/task/complete',
    method: 'post',
    params: {
      id: id
    }
  })
}

export function getTaskAll(pn, limit) {
  return request({
    url: '/task/all',
    method: 'get',
    params: {
      pn: pn,
      limit: limit
    }
  })
}

export function getDamageAll(pn, limit) {
  return request({
    url: '/damage/all',
    method: 'get',
    params: {
      pn: pn,
      limit: limit
    }
  })
}
