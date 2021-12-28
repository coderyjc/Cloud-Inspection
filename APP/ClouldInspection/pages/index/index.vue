<template>
	<view class="index">

		<!-- 功能项目 -->
		<view class="index-function">
			<IndexFunction></IndexFunction>
		</view>

		<view class="index-process">
			<!-- 待完成、超时、已完成工单 -->
			<IndexProcess></IndexProcess>
		</view>

	</view>
</template>

<script>
	import IndexProcess from './component/IndexProcess.vue'
	import IndexFunction from './component/IndexFunction.vue'

	export default {
		components: {
			IndexFunction,
			IndexProcess
		},
		data() {
			return {
				user: {}
			}
		},
		onShow() {
			// 判断用户有没有登录，以跳转到登录页
			if (this.getGlobalUser() == null) {
				// 跳转到登录页
				// uni.navigateTo({
				// 	url: '/pages/home/login'
				// })
			}
			// 获取本页面的数据
		},
		onLoad() {

		},
		onReachBottom() {

		},
		methods: {

		}
	};
</script>

<style lang="scss" scoped>
.index {
	background-color: $uni-bg-color-grey;
	min-height: 100vh;
	padding: 13px 7px;

}
</style>
