<template>
	<view>
		<u-modal v-model="avatarConfirm" content="修改头像？" @confirm="nav_changeAvatar" show-cancel-button></u-modal>

		<view class="u-flex user-box u-p-l-30 u-p-r-20 u-p-b-30 u-p-t-20" @click="nav_login">
			<view class="u-m-r-30">
				<u-avatar @click="chooseAvatar" :src="userAvatar" :show-level="false"
					size="140"></u-avatar>
			</view>
			<view class="u-flex-1">
				<view class="u-font-18 u-p-b-25"> {{ (userIsLogin && userInfo.userName) || '点击登录' }}</view>
			</view>
		</view>

		<view class="grid" v-if="userIsLogin">
			<UserOperationGrid></UserOperationGrid>
		</view>

	</view>
</template>

<script>
	import UserOperationGrid from './component/UserOperationGrid.vue'
	export default {
		components: {
			UserOperationGrid
		},
		data() {
			return {
				userAvatar: '../../static/logo/default-navatur.png',
				userIsLogin: false,
				userInfo: {},
				avatarConfirm: false
			}
		},
		onShow() {
			let user = this.getGlobalUser()
			if(null != user){
				this.userIsLogin = true
				this.userInfo = user
				this.userAvatar = this.$u.api.userAvatar(this.userInfo.userId)
			}
		},
		methods: {
			nav_changeAvatar() {
				this.$u.route({
					// 关于此路径，请见下方"注意事项"
					url: '/uview-ui/components/u-avatar-cropper/u-avatar-cropper',
					// 内部已设置以下默认参数值，可不传这些参数
					params: {
						// 输出图片宽度，高等于宽，单位px
						destWidth: 300,
						// 裁剪框宽度，高等于宽，单位px
						rectWidth: 200,
						// 输出的图片类型，如果'png'类型发现裁剪的图片太大，改成"jpg"即可
						fileType: 'jpg',
					}
				})
			},
			chooseAvatar() {
				if(!this.userIsLogin) return
				// 此为uView的跳转方法，详见"文档-JS"部分，也可以用uni的uni.navigateTo
				this.avatarConfirm = true
			},
			nav_login(){
				if(this.userIsLogin) return
				uni.navigateTo({
					url: '/pages/home/login'
				})
			}
		}
	}
</script>

<style lang="scss" scoped>
	page {
		background-color: #f6f6f6;
	}

	.user-box {
		background-color: #fff;
	}
	
</style>
