<template>
	<view class="progress">
		<view class="progress-wrap">
			<view class="progress-info">
				<!-- 损伤位置 -->
				<view class="damage-location">
					<!-- 这里用地图组件显示出来 -->
					<map :latitude="latitude" :longitude="longitude" scale="17" :markers="markers">
					</map>
				</view>
				<!-- 损伤图片 -->
				<view class="image-set">
					<image src="../../static/rails/001.png" mode="aspectFill"></image>
					<image src="../../static/rails/002.png" mode="aspectFill"></image>
					<image src="../../static/rails/003.png" mode="aspectFill"></image>
					<image src="../../static/rails/004.png" mode="aspectFill"></image>
				</view>
				<!-- 类型 -->
				<view class="damage-type">
					损伤类型：擦伤
				</view>
				<!-- 描述 -->
				<view class="damage-desc">
					钢管接口处发现略大划痕，枕木下沉距离过大，可能引起列车颠簸出轨，建议派人修理，更换枕木，有些枕木老化严重，有断裂迹象，建议更换......
				</view>
			</view>
			<view class="btn-group">
				<!-- 提交任务 -->
				<button class="receive-button">接单</button>
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
					latitude: 37.99296,
					longitude: 114.484118
				}],
				poisdatas: [{}],
				title: 'map',
				latitude: 37.99296,
				longitude: 114.484118
			}
		},
		methods: {

		}
	}
</script>

<style lang="scss" scoped>
	.progress{
		
		background-color: rgba($color: #F3F3F3, $alpha: 1.0);

		.progress-info{
			
			.image-set{
				display: flex;
				justify-content: center;
				flex-wrap: wrap;
				align-items: center;
				
				image{
					height: 200rpx;
					width: 250rpx;
					margin: 5px;
				}
			}
			
			view{
				font-size: 15px;
				// margin-top: 10px;
				background-color: #fff;
				border-radius: 10px;
				padding: 10px;
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
