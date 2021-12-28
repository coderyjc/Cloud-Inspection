<template>
	<view>
		<view class="u-m-t-20">
			<u-cell-group>
				<u-cell-item icon="trash-fill" title="清除缓存" :arrow="false" @click="cleanStorage">
					<u-loading u-slot="icon" mode="circle" color="#2c8bff" :show="showLoading"></u-loading>
				</u-cell-item>
			</u-cell-group>
			<u-cell-group>
				<u-cell-item icon="minus-circle-fill" title="退出登录" :arrow="false" @click="logout">
				</u-cell-item>
			</u-cell-group>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				showLoading: false
			}
		},
		methods: {
			cleanStorage(){
				this.showLoading = true
				uni.clearStorageSync()
				setTimeout(()=>{
					this.showLoading = false
					uni.showToast({
						title:"已清除所有缓存",
						duration:1500
					})
				}, 1000)
			},
			logout(){
				uni.navigateTo({
					url:'./login'
				})
			}
		}
	}
</script>

<style lang="scss">
	
	

</style>
