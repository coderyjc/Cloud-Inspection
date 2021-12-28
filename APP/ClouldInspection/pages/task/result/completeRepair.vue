<template>
	<view class="progress">
		<view class="progress-wrap">
			<view class="progress-area">
				<text><u-icon name="checkbox-mark"></u-icon>任务已完成</text>
			</view>
			<view class="progress-info">
				<!-- 损伤图片 -->
				<view>
					<image src="../../../static/rails/001.png" mode="aspectFill"></image>
					<image src="../../../static/rails/002.png" mode="aspectFill"></image>
					<image src="../../../static/rails/003.png" mode="aspectFill"></image>
					<image src="../../../static/rails/004.png" mode="aspectFill"></image>
				</view>
				<!-- 类型 -->
				<view class="damage-type">
					损伤类型：钢轨接头伤损
				</view>
				<!-- 截止日期 -->
				<view class="damage-deadline">
					截止日期：2021-08-31
				</view>
				<!-- 损伤位置 -->
				<view class="damage-location">
					<!-- 这里用地图组件显示出来 -->
					位置:
					<map :latitude="latitude" :longitude="longitude" scale="17" :markers="markers">
					</map>
				</view>
				<!-- 描述 -->
				<view class="damage-desc">
					描述：钢管接口处发现略大划痕，枕木下沉距离过大，可能引起列车颠簸出轨，建议派人修理，更换枕木，有些枕木老化严重，有断裂迹象，建议更换......
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				markers: [{
					id:'1',
					latitude: 38.02551808661723,
					longitude: 114.48723424885557
				}],
				poisdatas: [{}],
				title: 'map',
				latitude: 38.02551808661723,
				longitude: 114.48723424885557,
				steps: [{
					name: '任务发布'
				}, {
					name: '接受任务'
				}, {
					name: '提交结果'
				}, {
					name: '验收成功'
				}, ],
				// 当前进行到的步骤
				current: 1
			}
		},
		methods: {

		}
	}
</script>

<style lang="scss" scoped>
	.progress{
		background-color: #F3F3F3;
		
		padding: 20px 20px 20px 10px;
		
		.level-5{
			color: red;
		}
		
		.progress-area{
			color: #18B566;
			font-size: 30px;
			text-align: center;
		}
		
		.progress-info{
			margin-top: 20px;
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
