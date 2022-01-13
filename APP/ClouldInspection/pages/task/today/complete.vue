<template>
	<view class="complete">
		<TaskCompleteItem
			v-for="(item,index) in list"
			:time="item.postDate | timeFormat"
			:source="item.source"
			:type="item.damageType"
			:description="item.damageDescription"
			:receive_time="item.receiveDate | timeFormat"
			:submit_date="item.submitDate | timeFormat"
			:deadline="item.deadline | timeFormat"
			:complete_date="item.completeDate | timeFormat">
		</TaskCompleteItem>
	</view>
</template>

<script>
import TaskCompleteItem from '../component/TaskCompleteItem.vue'
import {getTime} from '../../../utils/timeutil.js'

export default {
	components: {
		TaskCompleteItem
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
			await this.$u.api.listCompleteTaskToday(this.user.userId, this.page, this.limit).then(res => {
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
