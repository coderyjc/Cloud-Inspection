<template>
	<view class="task-process">
		<u-grid :col="3">
			<u-grid-item @click="nav_task_list()">
				<u-icon name="list" color="#0984e3" size="58"></u-icon>
				<view class="grid-text">现有损伤</view>
			</u-grid-item>
			<u-grid-item @click="nav_task_ongoing()">
				<u-icon name="play-right" color="#0984e3" size="58"></u-icon>
				<view class="grid-text">正在进行</view>
			</u-grid-item>
			<u-grid-item @click="nav_task_checking()">
				<u-icon name="hourglass-half-fill" color="#00b894" size="58"></u-icon>
				<view class="grid-text">审核中</view>
			</u-grid-item>
		</u-grid>
		<u-grid :col="3">
			<u-grid-item @click="nav_outOfTime()">
				<u-icon name="clock" color="#d63031" size="58"></u-icon>
				<u-badge :count="7" :offset="[20, 30]"></u-badge>
				<view class="grid-text">超时</view>
			</u-grid-item>
			<u-grid-item @click="nav_toDo()">
				<u-icon name="arrow-upward" color="#0984e3" size="58"></u-icon>
				<u-badge :count="6" :offset="[20, 30]"></u-badge>
				<view class="grid-text">待提交</view>
			</u-grid-item>
			<u-grid-item @click="nav_hasComplete()">
				<u-icon name="checkmark" color="#00b894" size="58"></u-icon>
				<view class="grid-text">维修完成</view>
			</u-grid-item>
		</u-grid>
	</view>
</template>

<script>	
export default {
		name: "IndexDashboard",
		data() {
			return {
				
			};
		},
		onLoad() {

		},
		methods: {
			nav_task_list(){
				
			},
			nav_task_ongoing(){
				
			},
			nav_task_checking(){
				
			},
			nav_outOfTime(){
				this.$u.route('/pages/task/today/outOfTime');
			},
			nav_toDo(){
				this.$u.route('/pages/task/today/toDo');
			},
			nav_hasComplete(){
				this.$u.route('/pages/task/today/complete');
			},
			nav_taskAll(){
				this.$u.route('/pages/task/record');
			},
		}
	}
</script>

<style lang="scss">

.task-process {
	margin: 20px 0;
	background-color: $uni-bg-color;
	padding: 14px;
	border-radius: 17px;
}

</style>
