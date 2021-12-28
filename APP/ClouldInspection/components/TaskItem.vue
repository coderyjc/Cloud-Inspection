<template>
	<view>
		<u-card @click="nav_detail">
			<view class="card-head" slot="head">
				<text>2021-09-01 12:34</text>
				<view class="tag-group">
					<u-tag text="擦伤" mode="light" />
					<u-tag text="人工巡检" mode="light" />
				</view>
			</view>
			<view class="card-body" slot="body">
				<view class="card-left">
					<!-- 图片 -->
					<image src="../static/rails/002.png" mode="aspectFill"></image>
				</view>
				<view class="card-right">
					<!-- 备注 -->
					<view class="damage-description">
						钢管接口处发现略大划痕，枕木下沉距离过大，可能引起列车颠簸出轨，建议派人修理，更换枕木，有些枕木老化严重，有断裂迹象，建议更换......
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
			
		},
		data() {
			return {

			};
		},
		methods:{
			nav_detail(){
				this.$emit('body-click');
			}
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
