<template>
	<view class="u-demo">
		<view class="u-demo-wrap">
			<form>
				<view class="uni-list">
					<!-- 损伤类型 -->
						<view class="uni-list-cell">
						<view class="uni-list-cell-left">
							时长
						</view>
						<view class="uni-list-cell-db">
							<picker @change="bindTimePickerChange" :value="timeIndex" :range="timeList" range-key="duration">
								<label class="uni-input">{{timeList[timeIndex].duration}}</label>
							</picker>
						</view>
					</view>
			<!-- 损伤类型 -->
					<view class="uni-list-cell">
						<view class="uni-list-cell-left">
							原因
						</view>
						<view class="uni-list-cell-db">
							<picker @change="bindReasonPickerChange" :value="reasonIndex" :range="typeList" range-key="typeName">
								<label class="uni-input">{{typeList[reasonIndex].typeName}}</label>
							</picker>
						</view>
					</view>
			<!-- 描述 -->
					<u-input 
					type="textarea" 
					:border="true"
					height="inputHeight" 
					v-model="description" 
					class="u-border-bottom uni-form-item textarea" placeholder="备注" />
				</view>
				<button type="primary">提交</button>
			</form>
				
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				typeList: [
					{
						typeId: "0",
						typeName: "任性"
					},
					{
						typeId: "1",
						typeName: "超出能力范围"
					},
					{
						typeId: "2",
						typeName: "路遇突发情况"
					},
					{
						typeId: "3",
						typeName: "时间不够"
					},
					{
						typeId: "4",
						typeName: "工具不够"
					},
				],
				reasonIndex: 0,
				timeList: [
				{
					duration:'1小时'
				},{
					duration:'2小时'
				},{
					duration:'5小时'
				},{
					duration:'1天'
				}],
				timeIndex: 0,
				description: ''
			}
		},
		onShow() {
		},
		methods: {
			bindReasonPickerChange: function(e) {
				this.reasonIndex = e.detail.value
			},
			bindTimePickerChange: function(e) {
				this.timeIndex = e.detail.value
			},
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
