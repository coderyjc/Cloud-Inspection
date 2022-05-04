<template>
	<view class="todo">
		<TaskToDoItem
			v-for="(item,index) in taskList"
			:time="item.postDate  | timeFormat"
			:source="item.source"
			:type="item.type"
			:description="item.description"
			:receive_time="item.receiveDate | timeFormat"
			:deadline="item.deadline | timeFormat"
			:picture="picture_list[index]"
			@body-click="nav_process(item.taskId)">
		</TaskToDoItem>
	</view>
</template>

<script>
import TaskToDoItem from '../component/TaskToDoItem.vue'
import {getTime} from '../../../utils/timeutil.js'
export default {
	components: {
		TaskToDoItem
	},
	data() {
		return {
			user : {},
			page: 0,
			limit: 5,
			taskList: [],
			picture_list: [],
			max: 0,
			
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
		if(this.page >= this.max) return
		this.page += 1
		this.fetch_data(this.page);
	},
	filters:{
		timeFormat(time){
			return getTime(time)
		}
	},
	methods: {
		async fetch_data(pn){
			await this.$u.api.taskOnGoing(this.user.userId, this.page, this.limit).then(res => {
				this.taskList = this.taskList.concat(res.list.records)
				this.max = res.list.pages
				this.page = res.list.current
			})
			this.get_damage_picture()
		},
		async get_damage_picture(){
			let that = this
			this.picture_list = []
			for(let i = 0; i < this.taskList.length; i++){
			await this.$u.api.getDamagePicture(this.taskList[i].damageId).then(res => {
					if(res.images.length >= 1){
						let filename = this.$u.api.getPicture(res.images[0].filename)
						if (filename != "" || filename != null || filename != undefined){
							that.picture_list.push(filename)
						} else {
							that.picture_list.push(null)
						}
					}
				})
			}
		},
		nav_process(task_id){
			this.$u.route('/pages/task/process', {
				id: task_id
			})
		},
	}
};
</script>

<style lang="scss" scoped>
	.todo{
		padding: 0;
	}
</style>
