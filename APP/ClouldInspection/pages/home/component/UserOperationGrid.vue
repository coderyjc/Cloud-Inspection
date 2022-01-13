<template>
	<view class="">
		<u-cell-group>
			<view class="panel-main">
				<u-cell-item title="完成记录" @click="nav_history()"></u-cell-item>
			</view>
		</u-cell-group>
		<u-cell-group>
			<view class="panel-main">
				<u-cell-item title="更换账号"></u-cell-item>
				<u-cell-item title="清理缓存" @click="cleanStorage"><u-loading u-slot="icon" mode="circle" size="46" color="#2c8bff" :show="showLoading"></u-loading></u-cell-item>
				<u-cell-item title="退出登录" @click="nav_logout"></u-cell-item>
				<u-cell-item title="版本"></u-cell-item>
			</view>
		</u-cell-group>
	</view>

</template>

<script>
	export default {
		name: "UserOperationGrid",
		data() {
			return {
				showLoading: false
			};
		},
		onLoad() {


		},
		methods: {
			// 清理缓存
			cleanStorage(){
				this.showLoading = true
				uni.clearStorageSync()
				setTimeout(()=>{
					this.showLoading = false
					uni.showToast({
						title:"已清除缓存",
						duration:1500
					})
				}, 1000)
			},
			// 更换账号
			nav_change_account(){
				
			},
			nav_history(){
				this.$u.route('/pages/home/history')
			},
			// 退出登录
			nav_logout(){
				uni.clearStorageSync();
				uni.navigateTo({
					url: '/pages/home/login'
				})
			}
		}
	}
</script>

<style lang="scss">


</style>
