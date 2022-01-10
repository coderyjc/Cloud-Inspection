// 后端的服务器地址
let BACKEND_BASE_URL = 'http://localhost:8086'

const install = (Vue, vm) => {

	// User
	// 登录
	let login =  (username, pwd) => vm.$u.get('/user/login', {id: username,pwd: pwd,});
	// 获取用户头像
	let userAvatar = (userName) => BACKEND_BASE_URL + '/static/avatar/' + userName + '.png'
	
	//------------------------------------------------------
	// Damage
	// 损伤上报功能
	let postDamage = (location, type, postId, description) => vm.$u.post('/damage/post', 
		{location:location, type:type, postId:postId, source:'1',description:description})
	// 删除损伤图片
	let removeDamagePicture = (filename) => vm.$u.delete('/damage_image/remove', {filename: filename})
	// 获取现在有的损伤列表
	let getDamageList = (pn, limit) => vm.$u.get('/damage/listDamageUpToNow', {pn:pn, limit:limit})
	// 获取指定损伤的图片
	let getDamagePicture = (id) => vm.$u.get('/damage_image/get', {id: id})
	// 获取指定损伤的详细信息
	let getDamageDetail = (id) => vm.$u.get('/damage/getDamageById', {damageId: id})
	
	//------------------------------------------------------
	// 获取图片
	let getPicture = (filename) => BACKEND_BASE_URL + '/static/damage_picture/' + filename
	
	//------------------------------------------------------
	// Task
	// 接收任务
	let acquireTask = (userId, damageId) => vm.$u.post('/task/acquire', {damageid: damageId, id: userId})
	// 获取用户正在进行的任务
	let taskOnGoing = (userId, page, limit) => vm.$u.get('/task/user', {id: userId, pn: page, limit: limit}) 
	
	vm.$u.api = {
		// Uer
		login, userAvatar, 
		// Damage
		removeDamagePicture, postDamage, getDamageList, getDamagePicture, getDamageDetail,
		// Picture
		getPicture,
		// Task
		acquireTask, taskOnGoing
	};
}

export default {
	install
}