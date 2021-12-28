<template>
	<view>
		<u-card v-for="(item) in [1, 2]">
			<view class="card-head" slot="head">
				<text>2021-09-01</text>
			</view>
			<view class="card-body" slot="body">
				<view class="card-left">
					<!-- 图片 -->
					<image src="../../static/rails/001.png" mode="aspectFill"></image>
				</view>
				<view class="card-right">
					<!-- 备注 -->
					<view class="damage-description">
						备注备注备注备注备注备注备注备注备注备注备注备注备注备注备注备注备注备注备注备注备注
						备注备注备注备注备注备注备注备注备注备注备注备注备注......
					</view>
				</view>
			</view>
<!-- 			<view class="btn-group" slot="foot">
				<u-button @click="gotoProgress" size="mini" plain>查看详情</u-button>
			</view> -->
		</u-card>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				
			}
		},
		methods: {
			gotoProgress(){
				uni.navigateTo({
					url:'../demo/completeRepair'
				})
			}
		}
	}
</script>

<style lang="scss">
	.card-body{
		display: flex;
		.card-left{
			margin-right: 20rpx;
			image {
				width: 200rpx;
				height: 200rpx;
				border-radius: 10rpx;
			}
		}
	}
</style>
