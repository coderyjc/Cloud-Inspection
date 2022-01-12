<template>
	<view class="todo">
		<TaskCheckingItem
			v-for="(item,index) in list"
			:time="item.postDate | timeFormat"
			:source="item.source"
			:type="item.type"
			:description="item.description"
			:receive_time="item.receiveDate | timeFormat"
			:submit_date="item.submitDate | timeFormat"
			:deadline="item.deadline | timeFormat">
		</TaskCheckingItem>
	</view>
</template>

<script>
import TaskCheckingItem from '../component/TaskCheckingItem.vue'
import {getTime} from '../../../utils/timeutil.js'

export default {
	components: {
		TaskCheckingItem
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
		this.fetch_data(this.page);
	},
	filters:{
		timeFormat(time){
			return getTime(time)
		}
	},
	methods: {
		async fetch_data(pn){
			await this.$u.api.listTaskChecking(this.user.userId, this.page, this.limit).then(res => {
				this.list = this.list.concat(res.list.records)
				this.max = res.list.pages
				this.page += 1
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
