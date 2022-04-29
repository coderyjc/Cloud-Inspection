<template>
	<view class="read_more">
		<u-read-more :toggle="true" :show-height="350" open-text="收起" close-text="展开查看更多">
			<u-card :border-radius="40" style="margin: 0;" :foot-border-top="false" @click="nav_progress">
				<!-- 头部信息 -->
				<view class="card-head" slot="head">
					<text>2021-09-01 12:34</text>
					<view class="tag-group">
						<u-tag text="擦伤" mode="light" />
						<u-tag text="人工巡检" mode="light" />
					</view>
				</view>
				<!-- 中部任务简介 -->
				<view class="card-body" slot="body">
					<view class="card-left">
						<!-- 图片 -->
						<image src="../../../static/empty/data_empty.png" mode="aspectFill"></image>
					</view>
					<view class="card-right">
						<!-- 备注 -->
						<view class="damage-description">
							钢管接口处发现略大划痕，枕木下沉距离过大，可能引起列车颠簸出轨，建议派人修理，更换枕木......
						</view>
					</view>
				</view>
				<!-- 底部时间线 -->
				<view class="card-foot" slot="foot">
					<u-time-line class="timeline">
						<u-time-line-item nodeTop="2">
							<!-- 此处自定义了左边内容，用一个图标替代 -->
							<template v-slot:node>
								<view class="u-node" style="background: #19be6b;">
									<!-- 此处为uView的icon组件 -->
									<u-icon name="pushpin-fill" color="#fff" :size="24"></u-icon>
								</view>
							</template>
							<template v-slot:content>
								<view>
									<view class="u-order-title">已接单</view>
									<view class="u-order-desc">
										见一红灯笼停下，叩门三下，喊“芝麻开门”即可。</view>
									<view class="u-order-time">2019-05-08 12:12</view>
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
		name: "ProcessItem",
		data() {
			return {

			};
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
