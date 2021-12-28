<template>
	<view>
		<u-modal v-model="avatarConfirm" content="修改头像？" @confirm="nav_changeAvatar" show-cancel-button></u-modal>

		<view class="u-flex user-box u-p-l-30 u-p-r-20 u-p-b-30" v-if="userIsLogin" @click="userDetails">
			<view class="u-m-r-30">
				<u-avatar @click="chooseAvatar" src="../../static/logo/default-navatur.png" :show-level="false"
					size="140"></u-avatar>
			</view>
			<view class="u-flex-1">
				<view class="u-font-18 u-p-b-25">用户名</view>
				<view class="u-font-14 u-tips-color">ID: 10000</view>
			</view>
		</view>

		<view class="grid">
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
				userIsLogin: true,
				userInfo: {},
				avatarConfirm: false
			}
		},
		onShow() {

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
				// 此为uView的跳转方法，详见"文档-JS"部分，也可以用uni的uni.navigateTo
				this.avatarConfirm = true
			},
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
