<template>
	<!-- 功能项目 -->
	<view class="index-function">
		<!-- 巡检 -->
		<view class="function-item" @click="nav_postDamage()">
			<view class="item-icon">
				<u-icon name="plus" color="#2979ff" size="58"></u-icon>
			</view>
			<view class="item-name">
				上报
			</view>
		</view>
		<!-- 巡检 -->
		<view class="function-item" @click="nav_inspect()">
			<view class="item-icon">
				<u-icon name="search" color="#2979ff" size="58"></u-icon>
			</view>
			<view class="item-name">
				巡检
			</view>
		</view>		
		<!-- 报警 -->
		<view class="function-item" @click="nav_alart()">
			<view class="item-icon">
				<u-icon name="bell" color="#d63031" size="58"></u-icon>
			</view>
			<view class="item-name">
				报警
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		name: "IndexFunction",
		data() {
			return {

			};
		},
		onLoad() {


		},
		methods: {
			nav_postDamage(){
				this.$u.route('/pages/damage/postDamage');
			},
			nav_inspect(){
				this.$u.route('/pages/damage/inspect');
			},
			nav_alart(){
				this.$u.route('/pages/damage/alert');
			}
		}
	}
</script>

<style lang="scss">
	.index-function {
		background-color: $uni-bg-color;
		border-radius: 17px;
		
		// 巡检的数据情况
		display: flex;
		justify-content: space-between;
		flex-wrap: wrap;
		padding: 5px;

		.function-item {
			display: block;
			width: 180rpx;
			text-align: center;
			padding: 15px;

			.item-icon {}

			.item-name {}
		}

		.function-item:active {
			background-color: #f4f4f4;
		}
	}
</style>
