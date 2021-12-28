<template>
	<view>
		<view class="wrap">
			<view class="task-list">
				<TaskList></TaskList>
			</view>
		</view>
	</view>
</template>

<script>
	import TaskList from '../../components/TaskList/TaskList.vue'

	export default {
		components: {
			TaskList
		},
		data() {
			return {

			}
		},
		onLoad() {

		},
		onReachBottom() {

		},
		methods: {


		}
	}
</script>

<style lang="scss" scoped>
	.wrap {
		height: calc(100vh - var(--window-top) - var(--window-bottom));
		// height: 600rpx;
		width: 100%;
	}
</style>
