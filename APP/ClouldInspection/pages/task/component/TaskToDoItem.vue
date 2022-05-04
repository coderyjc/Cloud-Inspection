<template>
	<view class="read_more">
		<u-read-more :toggle="true" :show-height="350" open-text="收起" close-text="展开查看更多">
			<u-card :border-radius="40" style="margin: 0;" :foot-border-top="false" @click="nav_progress">
				<!-- 头部信息 -->
				<view class="card-head" slot="head">
					<text class="card-head-text" v-if="submit_date != '' ">{{deadline}} 截止</text>
					<view class="tag-group">
						<u-tag :text="typeList[type-1]" mode="light" />
						<u-tag :text="sourceList[source]" mode="light" />
					</view>
				</view>
				<!-- 中部任务简介 -->
				<view class="card-body" slot="body">
					<view class="card-left">
						<!-- 图片 -->
						<image :src="picture" mode="aspectFill"></image>
					</view>
					<view class="card-right">
						<!-- 备注 -->
						<view class="damage-description">
							{{ description }}
						</view>
					</view>
				</view>
				<!-- 底部时间线 -->
				<view class="card-foot" slot="foot">
					<u-time-line class="timeline">
						<u-time-line-item nodeTop="2">
							<template v-slot:content>
								<view>
									<view class="u-order-title">上报损伤</view>
									<view class="u-order-time">{{ time }}</view>
								</view>
							</template>
						</u-time-line-item>
						<u-time-line-item nodeTop="2">
							<template v-slot:content>
								<view>
									<view class="u-order-title">开始检修</view>
									<view class="u-order-time">{{ receive_time}}</view>
								</view>
							</template>
						</u-time-line-item>
					</u-time-line>
				</view>
			</u-card>
		</u-read-more>
	</view>
</template>

<script>
export default {
	name: "TaskToDoItem",
	data() {
		return {
			typeList: ['混合', '掉块','裂纹', '擦伤'],
			sourceList: ['人工巡检', '人工巡检', '巡检车'],
		};
	},
	props:{
		time: String,
		receive_time: String,
		submit_date: String,
		deadline: String,
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
			default: '../../../static/empty/data_empty.png'
		},
		description: {
			type: String,
			default: '暂无描述'
		},
	},
	onLoad() {


	},
	methods: {
		nav_progress() {
			this.$emit('body-click');
		}
	}
}
</script>

<style lang="scss">
	view {
		text-indent: 0em;
	}

	.read_more {
		margin: 10px;

		.card-head {
			display: flex;
			justify-content: space-between;
			align-items: center;

			.card-head-text{
				color: #626262;
			}

			.u-tag {
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
					line-height: 34rpx;
				}
			}
		}

		.card-foot {
			.timeline {
				.u-node {
					width: 30rpx;
					height: 30rpx;
					border-radius: 50rpx;
					display: flex;
					justify-content: center;
					align-items: center;
					background: #d0d0d0;
				}

				.u-order-title {
					color: #333333;
					font-weight: bold;
					font-size: 30rpx;
				}

				.u-order-desc {
					color: rgb(150, 150, 150);
					font-size: 28rpx;
					margin-bottom: 6rpx;
				}

				.u-order-time {
					color: rgb(200, 200, 200);
					font-size: 26rpx;
				}
			}

		}
	}
</style>
