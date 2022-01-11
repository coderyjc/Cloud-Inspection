<template>
	<view class="todo">
		<TaskToDoItem
			v-for="(item,index) in list"
			:time="item.postDate"
			:source="item.source"
			:type="item.type"
			:description="item.description"
			:receive_time="item.receiveDate"
			:deadline="item.deadline"
			@body-click="nav_process(item.taskId)">
		</TaskToDoItem>
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
			user : {},
			page: 0,
			limit: 5,
			list: [],
			max: 0
		}
	},
	onLoad() {
		this.user = this.getGlobalUser()
		if(this.user == null){
			// 跳到登录页面
		}
		this.fetch_data();
	},
	onReachBottom() {
		if(this.page > this.max) return
		this.fetch_data(this.page + 1);
	},
	methods: {
		async fetch_data(pn){
			await this.$u.api.taskOnGoing(this.user.userId, this.page, this.limit).then(res => {
				this.list = this.list.concat(res.list.records)
				this.max = res.list.pages
				this.page += 1
			})
		},
		nav_process(task_id){
			console.log(task_id);
			this.$u.route('/pages/task/process', {
				id: task_id
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
