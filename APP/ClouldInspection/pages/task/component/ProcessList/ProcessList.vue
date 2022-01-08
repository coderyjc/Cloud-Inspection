<template>
	<view class="process-list">
		<scroll-view scroll-y style="height: calc(100vh - var(--window-top) - var(--window-bottom))"
			refresher-enabled="true" refresher-background="#5098ff" :refresher-triggered="triggered"
			@refresherrefresh="onRefresh" @refresherrestore="onRestore" @scrolltolower="reachBottom">
			<u-toast ref="uToast" />

			<ProcessItem v-for="(item) in [1, 2, 3, 4, 5]" margin="8px" padding="10" @body-click="nav_details">
			</ProcessItem>

			<u-loadmore :status="loadStatus" bgColor="#f2f2f2" :load-text="loadText"></u-loadmore>
		</scroll-view>
	</view>
</template>

<script>
	import ProcessItem from '../ProcessItem.vue'
	
	export default {
		name: "ProcessList",
		components: {
			ProcessItem
		},
		data() {
			return {
				triggered: true,
				_freshing: false,
				loadStatus: 'loading',
				loadText: {
					loadmore: '轻轻上拉加载更多',
					loading: '努力加载中',
					nomore: '我是有底线的'
				}
			};
		},
		onLoad() {
			// 必须在这初始化
			this._freshing = false
			this.triggered = true
		},
		methods: {
			onRefresh() {
				if(this._freshing == true) return
				if(this.triggered == false) this.triggered = true
				this._freshing = true
				setTimeout(() => {
					this.triggered = false;
					this._freshing = false;
					this.$refs.uToast.show({
						title: '刷新成功',
						type: 'success',
					})
				}, 1000)
			},
			onRestore() {
				this.triggered = false // 需要重置
				this._freshing = false // 需要重置
			},
			reachBottom() {
				this.loadStatus = 'nomore'
				console.log("触底了")
			},
		}
	}
</script>

<style lang="scss">


</style>
