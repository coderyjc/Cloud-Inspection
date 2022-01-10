<template>
	<view class="progress">
		<view class="progress-wrap">
			<view class="progress-info">
				<u-toast ref="uToast" isTab/>
				<!-- 损伤位置 -->
				<view class="damage-location">
					<!-- 这里用地图组件显示出来 -->
					<map :latitude="latitude" :longitude="longitude" scale="17" :markers="markers">
					</map>
				</view>
				<!-- 损伤图片 -->
				<view class="image-set">
					<image 
					 v-for="(item,index) in picture_list"
					 :src="item" mode="aspectFill">
					 </image>
					 <view v-if="picture_list.length === 0">暂无图片</view>
				</view>
				<view class="damage-type"> 检修时间：{{damage_info.postDate | timeFormat}}</view>
				<!-- 类型 -->
				<view class="damage-type"> 损伤类型：擦伤 </view>
				<!-- 描述 -->
				<view class="damage-desc"> {{damage_info.description}} </view>
			</view>
			<view class="btn-group">
				<!-- 提交任务 -->
				<button class="receive-button" @click="acquire_task">接单</button>
			</view>
		</view>
	</view>
</template>

<script>
import {getTime} from '../../utils/timeutil.js'

export default {
	data() {
		return {
			// 地图坐标相关
			markers: [{
				id:'1',
				latitude: 37.99296,
				longitude: 114.484118
			}],
			poisdatas: [{}],
			title: 'map',
			latitude: 37.99296,
			longitude: 114.484118,
			// 损伤相关
			damage_id: 0,
			damage_info: {},
			picture_list: []
		}
	},
	onLoad(options) {
		this.damage_id = options.id
	},
	mounted() {
		this.get_damage_detail()
		this.get_damage_picture()
	},
	filters:{
		timeFormat(time){
			return getTime(time)
		}
	},
	methods: {
		async get_damage_detail(){
			await this.$u.api.getDamageDetail(this.damage_id).then(res => {
				this.damage_info = res.info
			})
		},
		get_damage_picture(){
			this.$u.api.getDamagePicture(this.damage_id).then(res => {
				res.images.forEach(res =>{
					this.picture_list.push(this.$u.api.getPicture(res.filename))
				})
			})
		},
		acquire_task(){
			let user = this.getGlobalUser()
			this.$u.api.acquireTask(user.userId, this.damage_id).then(res => {
				this.$refs.uToast.show({
					title: '接单成功',
					type: 'success',
				})
				setTimeout(function(){
					uni.switchTab({
						url:'../task/task'
					})
				}, 2000)
			})
		}
	}
}
</script>

<style lang="scss" scoped>
.progress{
	background-color: #F6F6F6;
	padding: 1px 5px;
	.progress-info{
		
		view{
			font-size: 15px;
			margin-top: 10px;
			background-color: #fff;
			border-radius: 10px;
			padding: 10px;
		}
		
		.image-set{
			display: flex;
			justify-content: space-around;
			flex-wrap: wrap;
			align-items: center;
			
			image{
				height: 200rpx;
				width: 250rpx;
				margin: 5px;
			}
		}
		

		
		.damage-location{
			map{
				width: 100%;
				height: 500rpx;
				margin-top: 10px;
			}
		}
	}
	
	.btn-group{
		margin-top: 10px;
		button{
			margin-top: 10px;
		}
		.receive-button{
			background-color: #3DB0FC;
			color: white;
			border-radius: 0;
			width: 100%;
			
		}
	}
}
</style>
