<template>
	<view class="u-demo">
		<u-toast ref="uToast" isTab/>
		<view class="u-demo-wrap">
			<form>
				<view class="uni-list">
			<!-- 描述 -->
					<u-input 
					type="textarea" 
					:border="true"
					height="inputHeight" 
					v-model="description" 
					class="u-border-bottom uni-form-item textarea" placeholder="说明原因" />
				</view>
				<button type="primary" @click="cancelTask()">提交</button>
			</form>
				
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				task_id: 0,
				description: ''
			}
		},
		onLoad(options) {
			console.log(options);
			this.task_id = options.taskid
		},
		methods: {
			cancelTask(){
				console.log(this.task_id);
				if(this.description < 10){
					this.$refs.uToast.show({
						title: '字数不得少于10',
						type: 'error',
					})
					return
				}
				this.$u.api.cancelTask(this.task_id, this.description).then(res => {
					this.$refs.uToast.show({
						title: '取消成功',
						type: 'success',
					})
					setTimeout(() => {
						this.$u.route('/pages/task/today/ongoing')
					}, 2000)
				})
			}
		}
	}
</script>

<style lang="scss">
	map{
		width: 750rpx;
		height: 400rpx;
	}	
	
	.u-demo-wrap {
		background-color: #FFFFFF;
		padding: 40rpx;
	}
	
	.u-add-wrap {
		flex-direction: column;
		color: $u-content-color;
		font-size: 28rpx;
	}
	
	/deep/ .slot-btn {
		width: 329rpx;
		height: 140rpx;
		display: flex;
		justify-content: center;
		align-items: center;
		background: rgb(244, 245, 246);
		border-radius: 10rpx;
	}

	.slot-btn__hover {
		background-color: rgb(235, 236, 238);
	}

	.pre-box {
		display: flex;
		align-items: center;
		justify-content: space-between;
		flex-wrap: wrap;
	}

	.pre-item {
		flex: 0 0 48.5%;
		border-radius: 10rpx;
		height: 140rpx;
		overflow: hidden;
		position: relative;
		margin-bottom: 20rpx;
	}

	.u-progress {
		position: absolute;
		bottom: 10rpx;
		left: 8rpx;
		right: 8rpx;
		z-index: 9;
		width: auto;
	}

	.pre-item-image {
		width: 100%;
		height: 140rpx;
	}

	.u-delete-icon {
		position: absolute;
		top: 10rpx;
		right: 10rpx;
		z-index: 10;
		background-color: $u-type-error;
		border-radius: 100rpx;
		width: 44rpx;
		height: 44rpx;
		display: flex;
		align-items: center;
		justify-content: center;
	}
	
	.uni-list-cell{
		margin-top: 20px;
		display: flex;
		font-size: 15px;
		border-top: 1px solid #bdbdbd;
		border-bottom: 1px solid #bdbdbd;
		view{
			padding: 10rpx;
		}
		
		.uni-list-cell-left{
			color: #8a8a8a;
			width: 150rpx;
		}
		.uni-list-cell-db{
			width: 500rpx;
			// margin-left: 20rpx;
			picker{
				display: block;
			}
		}
	}
	
	.textarea{
		margin-top: 40rpx;
		margin-bottom: 40rpx;
		height: 200rpx;
	}
</style>
