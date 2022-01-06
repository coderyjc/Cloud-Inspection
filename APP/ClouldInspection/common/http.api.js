// 后端的服务器地址
let BACKEND_BASE_URL = 'http://localhost:8086'

const install = (Vue, vm) => {

	let login =  (username, pwd) => vm.$u.get('/user/login', {id: username,pwd: pwd,});
	
	let userAvatar = (userName) => BACKEND_BASE_URL + '/static/avatar/' + userName + '.png'
	
	vm.$u.api = {login, userAvatar};
}

export default {
	install
}