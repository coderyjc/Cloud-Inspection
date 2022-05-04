<template>
	<view class="progress">
		<view class="progress-wrap">
			<view class="progress-info">
				<u-steps :current="task.status - 1" 
						:list="steps" 
						mode="dot"></u-steps>
				<!-- 损伤图片 -->
				<view class="card-left">
					<!-- 图片 -->
					<image :src="'http://101.201.64.102:8890/static/damage_picture/36a6351212054795948494bf5f5a48b7.jpg'" 
					mode="aspectFill"></image>
				</view>
				<!-- 类型 -->
				<view class="damage-type">
					损伤类型：{{damageType[task.type-1]}}
				</view>
				<!-- 截止日期 -->
				<view class="damage-deadline">
					截止日期：{{ task.deadline | timeFormat }}
				</view>
				<!-- 损伤位置 -->
				<view class="damage-location">
					<!-- 这里用地图组件显示出来 -->
					位置:
					<map :latitude="latitude" :longitude="longitude" scale="17" :markers="markers">
					</map>
				</view>
				<!-- 描述 -->
				<view class="damage-desc">{{task.description}}</view>
			</view>
		</view>
	</view>
</template>

<script>
import {getTime} from '../../../utils/timeutil.js'

export default {
	data() {
		return {
			markers: [{
				id:'1',
				latitude: 38.08362,
				longitude: 114.51161,
			}],
			poisdatas: [{}],
			title: 'map',
			latitude: 38.08362,
			longitude: 114.51161,
			steps: [{
				name: '任务发布'
			}, {
				name: '接受任务'
			}, {
				name: '提交结果'
			}, {
				name: '验收成功'
			}, ],
			task: {},
			damageType: ['混合','掉块','裂纹','擦伤'],
			picture_list:[]
		}
	},
	onLoad(options) {
		this.task_id = options.id
	},
	mounted() {
		this.fetch_data()
	},
	filters:{
		timeFormat(time){
			return getTime(time)
		}
	},
	methods: {
		async fetch_data(){
			await this.$u.api.getTaskProcess(this.task_id).then(res => {
				this.task = res.taskProcess
				this.$u.api.getDamagePicture(this.task.damageId).then(res => {
					res.images.forEach(res =>{
						this.picture_list.push(this.$u.api.getPicture(res.filename))
					})
				})
			})
		},
	}
}
</script>

<style lang="scss" scoped>
	.progress{
		min-height: 100vh;
		background-color: #F3F3F3;
		
		padding: 1px 20px 20px 10px;
		
		.level-5{
			color: red;
		}
		
		.progress-info{
			margin-top: 10px;
			image{
				height: 200rpx;
				width: 300rpx;
				margin: 10rpx;
			}
						
			view{
				font-size: 15px;
				margin-top: 10px;
				background-color: #fff;
				border-radius: 10px;
				padding: 10px;
			}
					
			.damage-location{
				map{
					width: 100%;
					height: 400rpx;
					margin-top: 10px;
				}
			}
		}
		
		.btn-group{
			margin-top: 10px;
			button{
				margin-top: 10px;
			}
		}
		
		.segment-line{
			height: 1rpx;
			width: 80%;
			background-color: #c3c3c3;
			margin-left: 10%;
		}
		
	}
</style>
