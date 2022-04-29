<template>
	<view>
		<u-card @click="nav_detail">
			<view class="card-head" slot="head">
				<text>{{time | getTime}}</text>
				<view class="tag-group">
					<u-tag :text="typeList[type - 1]" mode="light" />
					<u-tag :text="sourceList[source]" mode="light" />
				</view>
			</view>
			<view class="card-body" slot="body">
				<view class="card-left">
					<!-- 图片 -->
					<image :src="picture" mode="aspectFill"></image>
				</view>
				<view class="card-right">
					<!-- 备注 -->
					<view class="damage-description">
						{{description}}
					</view>
				</view>
			</view>
		</u-card>
	</view>
</template>

<script>
export default {
	name: "TaskItem",
	props:{
		time: String,
		type: {
			type: Number,
			default: 0
		},
		source: {
			type:Number,
			default:0
		},
		picture: {
			type: String,
			default: '../../static/empty/data_empty.png'
		},
		description: {
			type: String,
			default: '暂无描述'
		}
	},
	filters:{
		getTime(timestamp){
			var date = null;
			if(timestamp == null){
				date = new Date();
			} else {
				date = new Date(timestamp)
			}
			var Y = date.getFullYear()
			var M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1)
			var D = date.getDate() < 10 ? '0' + date.getDate() : date.getDate()
			var hh = date.getHours() < 10 ? '0' + date.getHours() : date.getHours()
			var mm = date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes()
			var ss = date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds()
			return Y + '-' +  M + '-' + D + ' ' + hh + ':' + mm + ':' + ss
		},
	},
	data() {
		return {
			// 常量
			typeList: ['未知', '掉块','裂纹', '擦伤'],
			sourceList: ['未知', '人工巡检', '巡检车'],
			
		};
	},
	methods:{
		nav_detail(){
			this.$emit('body-click');
		},
	}
}
</script>

<style lang="scss">
	.card-head {
		display: flex;
		justify-content: space-between;
		align-items: center;
		
		.u-tag{
			margin-left: 2px;
		}
		
	}

	.card-body {
		display: flex;

		.card-left {
			margin-right: 20rpx;

			image {
				width: 180rpx;
				height: 180rpx;
				border-radius: 10rpx;
			}
		}

		.card-right {

			.damage-description {
				color: #636e72;
			}
		}
	}
</style>
