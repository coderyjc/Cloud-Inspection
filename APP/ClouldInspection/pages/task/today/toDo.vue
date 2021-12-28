<template>
	<view class="todo">
		<TaskToDoItem  @body-click="nav_progress"  v-for="i  in [1, 2, 3, 4, 5, 6]"></TaskToDoItem>
	</view>
</template>

<script>
	import TaskToDoItem from '../component/TaskToDoItem.vue'
	export default {
		components: {
			TaskToDoItem
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
			nav_progress(){
				uni.navigateTo({
					url: '/pages/task/progress'
				})
			}
		}
	};
</script>

<style lang="scss" scoped>
	.todo{
		padding: 0;
	}
</style>
