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
	//------------------------------------------------------
	
	
	vm.$u.api = {
		// Uer
		login, userAvatar, 
		// Damage
		removeDamagePicture, postDamage, getDamageList
	};
}

export default {
	install
}