<template>
	<view>
		<view class="wrap">
			<view class="u-tabs-box">
				<u-tabs-swiper 
					activeColor="#5098ff" 
					ref="tabs"
					:list="typeList" 
					:current="current"
					@change="change" 
					:is-scroll="false" 
					swiperWidth="750"></u-tabs-swiper>
			</view>
			<u-top-tips ref="uTips"></u-top-tips>
			<swiper class="swiper-box" 
				:current="swiperCurrent" 
				@transition="transition" 
				@animationfinish="animationfinish">
				<swiper-item class="swiper-item">
					<TaskList class="task-list"></TaskList>
				</swiper-item>
				<swiper-item class="swiper-item">
					<ProcessList></ProcessList>
				</swiper-item>
				<swiper-item class="swiper-item">
					<ProcessList></ProcessList>
				</swiper-item>
			</swiper>
		</view>
	</view>
</template>

<script>
import TaskList from './component/TaskList/TaskList.vue'
import ProcessList from './component/ProcessList/ProcessList.vue'

export default {
	components:{
		TaskList,
		ProcessList
	},
	data() {
		return {
			typeList: [{name:"现有损伤",}, {name:"正在修复",},{name:"正在审核",}],
			// 一共四个tab，每一个tab在一个orderlist数组中
			commentList: [],
			// 每一个swiper当前的页码
			currentPage: [1, 1],
			// 最大页码
			maxPage: [1, 1],
			// 下拉刷新状况
			// 滑动用的, swipercurrent 和 current 都是当前所在swiper
			current: 0,
			// 显示收藏夹的选择器
			swiperCurrent: 0,
			dx: 0,
			nowTab: 0,
		};
	},
	onLoad() {
		// 必须在这初始化
		this._freshing = false
		this.triggered = true
	},
	onReachBottom() {
		this.reachBottom();
	},
	methods: {
		// tab栏切换
		change(index) {
			this.swiperCurrent = index;
		},
		transition({ detail: { dx } }) {
			this.$refs.tabs.setDx(dx);
		},
		animationfinish({ detail: { current } }) {
			this.$refs.tabs.setFinishCurrent(current);
			this.swiperCurrent = current;
			this.current = current;
		},
		reachBottom(){
			this.loadStatus = ['nomore', 'nomore']
			console.log("触底了")
		}
	}
};
</script>

<style>
/* #ifndef H5 */
page {
	height: 100%;
	background-color: #f2f2f2;
}
/* #endif */
</style>

<style lang="scss" scoped>

	.wrap {
		display: flex;
		flex-direction: column;
		height: calc(100vh - var(--window-top) - var(--window-bottom));
		// height: 750rpx;
		width: 100%;
	}
	.swiper-box {
		flex: 1;
	}
	.swiper-item {
		height: 100%;
		width: 100%;
		display: flex;
		
	}
	
	.task-list{
		width: 100%;
	}
	
</style>