<template>
	<view class="alert">

		<!-- 显示暂时没有报警 -->
		<view class="no-alert" v-if="alert_is_exist">
			暂时没有报警
		</view>
		
	</view>
</template>

<script>
	export default {
		components: {

		},
		data() {
			return {
				alert_is_exist: true,
			}
		},
		onLoad() {

		},
		onReachBottom() {

		},
		methods: {

		}
	};
</script>

<style lang="scss" scoped>
	.alert {
		.no-alert {
			padding: 20px;
			text-align: center;
			color: #747d8c;
			background-color: #f1f2f6;
		}

	}
</style>
