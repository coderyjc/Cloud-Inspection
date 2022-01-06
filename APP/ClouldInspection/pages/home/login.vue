<template>
	<view>
		<view class="wrap">
			<u-toast ref="uToast"></u-toast>
			<u-modal v-model="showModal" :content="content"></u-modal>
			<view class="content">
				<form @submit="formSubmit">
					<view class="form-item account">
						<u-icon size="40" name="account"></u-icon>
						<input class="uni-form-item" name="userId" placeholder="请输入工号" :value="id" />
					</view>
					<view class="form-item password">
						<u-icon size="40" name="lock"></u-icon>
						<input class="uni-form-item" type="password" name="password" placeholder="请输入密码" :value="pwd" />
					</view>
					<u-checkbox v-model="rememberMe">记住我</u-checkbox>
					<button form-type="submit" class="submitBtn">登录</button>
				</form>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				rememberMe: false,
				index: 0,
				content: '请输入账号和密码',
				showModal: false,
				id: '',
				pwd :''
			}
		},
		onShow() {
			let loginData = uni.getStorageSync('login')
			this.id = loginData.id
			this.pwd = loginData.pwd
		},
		methods: {
			bindPickerChange: function(e) {
				this.index = e.detail.value
			},
			formSubmit(e) {
				
				let data = e.detail.value
				let userId = data.userId
				let password = data.password
				
				if (userId === "" || password === "") {
					this.showModal = true
					return
				}
				
				// 登录请求
				this.$u.api.login(userId, password).then(res => {
					
					let msg = '账号或密码错误'
					let type = 'error'
					
					// 登录成功
					if(res.suc){
						// 发起注册或者登录的请求成功
						msg = '登录成功'
						type = 'success'
						
						// 缓存中存入用户
						uni.setStorageSync('user', res.user)
						
						// 判断有没有记住密码
						if (this.rememberMe) {
							let userLogin = {
								id: userId,
								pwd: password
							}
							// 记住密码的情况下向缓存中写入密码, 登录的时候就直接填充
							uni.setStorageSync('login', userLogin)
						}
					}
					
					this.$refs.uToast.show({
						title: msg,
						type: type,
					})
				})
				
				


			},
			switchChange: function(e) {
				// 记住密码
				this.rememberMe = e.target.value
			}
		}
	}
</script>

<style lang="scss" scoped>
	.wrap {
		font-size: 28rpx;

		.content {
			width: 650rpx;
			margin: 40rpx auto 0;
			
			.form-item{
				display: flex;
				justify-content: center;
				line-height: 20px;
				align-items: center;
				height: 70rpx;
				// border: 1px solid #a4b0be;
				// border-radius: 5px;
				margin-bottom: 10px;
				
				.u-icon{
					margin-right: 10px;
				}
			}
			
			.uni-form-item{
				width: 600rpx;
			}
			
			.submitBtn {
				margin-top: 30rpx;
				color: white;
				border: none;
				font-size: 30rpx;
				padding: 6rpx 0;
				background-color: #3DB0FC;

				&:active {
					background-color: #74b9ff;
				}
			}

			.radio {
				margin-top: 10rpx;
				margin-right: 10px;
			}

			.rememberme {
				margin-top: 20px;
			}
		}

	}
</style>
