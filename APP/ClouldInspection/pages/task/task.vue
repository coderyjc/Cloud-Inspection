<template>
	<view class="task-list">
		<u-back-top :scroll-top="scrollTop"></u-back-top>
		<scroll-view 
			scroll-y style="height: calc(100vh - var(--window-top) - var(--window-bottom))"
			refresher-enabled="true" 
			refresher-background="#5098ff" 
			:refresher-triggered="triggered"
			@refresherrefresh="onRefresh" 
			@refresherrestore="onRestore" 
			@scrolltolower="reachBottom">
			
			<u-toast ref="uToast" />
			
			<TaskItem 
				v-for="(item,index) in taskList" 
				margin="8px"
				padding="10"
				:time="item.postDate"
				:source="item.postSource"
				:type="item.damageTypeId"
				:description="item.description"
				@body-click="nav_details(item.id)"></TaskItem>
			
			<u-loadmore 
				:status="loadStatus" 
				bgColor="#f2f2f2" 
				:load-text="loadText"></u-loadmore>
		</scroll-view>
	</view>
</template>

<script>
	import TaskItem from './component/TaskItem.vue'

	export default {
		name: "TaskList",
		components: {
			TaskItem
		},
		data() {
			return {
				// 页面相关
				scrollTop: 0,
				// 数据相关
				taskList: [],
				pn: 1,
				limit: 5,
				// 刷新相关
				triggered: true,
				_freshing: false,
				loadStatus: 'loading',
				loadText: {
					loadmore: '轻轻上拉加载更多',
					loading: '努力加载中',
					nomore: '我是有底线的'
				}
			}
		},
		onPageScroll(e) {
			this.scrollTop = e.scrollTop;
		},
		onLoad() {
			// 必须在这初始化
			this._freshing = false
			this.triggered = true
		},
		mounted() {
			// 网络请求等
			this.get_data(this.pn, this.limit)
		},
		methods: {
			nav_details(id) {
				this.$u.route('/pages/damage/damageDetail', {
					id: id
				})
			},
			async get_data(pn, limit){
				await this.$u.api.getDamageList(pn, limit).then(res => {
					this.taskList = this.taskList.concat(res.list.records)
				})
			}, 
			async get_latest_data(){
				await this.$u.api.getDamageList(1, 5).then(res => {
					this.taskList = res.list.records
				})
			}, 
			onRefresh() {
				if(this._freshing == true) return
				if(this.triggered == false) this.triggered = true
				this._freshing = true
				// 清空数据
				this.get_latest_data()
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
				this.loadStatus = 'loading'
				// 拉取数据
				this.pn += 1
				this.get_data(this.pn, this.limit)
				this.loadStatus = 'loadmore'
			},
		}
	}
</script>

<style lang="scss">
	.task-list {
		background-color: #f6f6f6;
	}
</style>
