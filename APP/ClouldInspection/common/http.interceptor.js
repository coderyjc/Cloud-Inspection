const install = (Vue, vm) => {
	// 此为自定义配置参数，具体参数见上方说明
	Vue.prototype.$u.http.setConfig({
		// baseUrl: 'http://101.201.64.102:9090',
		baseUrl: 'http://localhost:8086',
		loadingText: '努力加载中~',
		loadingTime: 800,
		// 设置自定义头部content-type
		header: {
			'Content-type':'application/x-www-form-urlencoded'
		}
		// ......
	});
	
	
	
	// 请求拦截部分，如配置，每次请求前都会执行
	Vue.prototype.$u.http.interceptor.request = (config) => {
		// config.header.Token = 'xxxxxx';
		
		// 可以对某个url进行特别处理，此url参数为this.$u.get(url)中的url值
		// if(config.url == '/user/login') config.header.noToken = true;
		// 最后需要将config进行return
		return config;
	}
	
	
	// 响应拦截，判断状态码是否通过
	Vue.prototype.$u.http.interceptor.response = (res) => {
		if(res.code == 100) {
			// 请求成功，后端返回 0
			return res.extend;
		} else {
			// 请求失败，后端返回非 0
			
			// 这里加上类型代码的判断，根据类型代码给出相应的提示。
			
			let msg = res.extend.msg
			vm.$u.toast(msg === null ? "请求失败" : msg);
			return false;
		}
	}
}

export default {
	install
}