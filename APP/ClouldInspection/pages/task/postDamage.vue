<template>
	<view class="u-demo">
		<!-- 位置 -->
		<map :latitude="latitude" :longitude="longitude" scale="16" :markers=markers show-location="true">
		</map>
		<u-button @click="getLocation">{{buttonText}}</u-button>

		<view class="u-demo-wrap">
			<!-- 上传图片 -->
			<view class="u-demo-area">
				<view class="pre-box" v-if="!showUploadList">
					<!-- 图片列表 -->
					<view class="pre-item" v-for="(item, index) in lists" :key="index">
						<image class="pre-item-image" :src="item.url" mode="aspectFill"></image>
						<!-- 图片上的删除按钮 -->
						<view class="u-delete-icon" @tap.stop="deleteItem(index)">
							<u-icon name="close" size="20" color="#ffffff"></u-icon>
						</view>
						<!-- 图片上传的进度 -->
						<u-line-progress v-if="item.progress > 0 && !item.error" :show-percent="false" height="16"
							class="u-progress" :percent="item.progress"></u-line-progress>
					</view>
				</view>
				<u-upload @on-choose-fail="onChooseFail" @on-success="success" :before-remove="beforeRemove"
					ref="uUpload" :show-upload-list="showUploadList" :action="action" :file-list="fileList"
					:max-count="maxCount" @on-list-change="onListChange">
				</u-upload>
				<u-button :custom-style="{marginTop: '20rpx'}" @click="clear">清空列表</u-button>
				<!-- <u-button :custom-style="{marginTop: '20rpx'}" @click="reUpload">重新上传</u-button> -->
			</view>

			<form>
				<view class="uni-list">
					<!-- 损伤类型 -->
					<view class="uni-list-cell">
						<view class="uni-list-cell-left">
							类型
						</view>
						<view class="uni-list-cell-db">
							<picker @change="bindTypePickerChange" :value="typeIndex" :range="damageTypeList"
								range-key="typeName">
								<label class="uni-input">{{damageTypeList[typeIndex].typeName}}</label>
							</picker>
						</view>
					</view>
					<!-- 描述 -->
					<u-input type="textarea" :border="true" height="inputHeight" v-model="description"
						class="u-border-bottom uni-form-item textarea" placeholder="描述" />
				</view>
				<button style="background-color: #3DB0FC;color: white;" @click="formSubmit">提交</button>
			</form>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				buttonText: "点击获取位置",
				markers: [{}, {}, {}],
				poisdatas: [{}, {}, {}],
				title: 'map',
				latitude: 37.99296,
				longitude: 114.484118,
				// 上传图片的地址
				action: 'http://127.0.0.1:8081/upload/damage',
				// 预置上传列表
				fileList: [],
				// 显示上传列表
				showUploadList: true,
				// 最大上传数量
				maxCount: 4,
				// 组件内部的文件列表
				lists: [],
				// 钢轨损伤类型
				damageTypeList: [{
						typeId: "0",
						typeName: "无法判断"
					},
					{
						typeId: "1",
						typeName: "掉块"
					},
					{
						typeId: "2",
						typeName: "裂纹"
					},
					{
						typeId: "3",
						typeName: "擦伤"
					},
				],
				typeIndex: 0,
				levelIndex: 0,
				description: ''
			}
		},
		onShow() {
			var that = this
			uni.getLocation({
				type: 'gcj02',
				success: function(res) {
					that.longitude = res.longitude
					that.latitude = res.latitude
					that.buttonText = "位置：" + String(res.longitude) + " " + String(res.latitude)
				}
			});
		},
		methods: {
			getLocation() {
				var that = this
				uni.getLocation({
					type: 'gcj02',
				 success: function(res) {
						that.buttonText = "114.484118 37.99296"
					}
				});
			},
			bindTypePickerChange: function(e) {
				this.typeIndex = e.detail.value
			},
			bindLevelPickerChange: function(e) {
				this.levelIndex = e.detail.value
			},
			reUpload() {
				this.$refs.uUpload.reUpload();
			},
			clear() {
				this.$refs.uUpload.clear();
			},
			upload() {
				this.$refs.uUpload.upload();
			},
			deleteItem(index) {
				this.$refs.uUpload.remove(index);
			},
			success(data) {
				// 图片上传成功触发, data是服务器返回的参数
				console.log(data);
			},
			error(res, index, lists) {
				// 上传失败触发
				uni.showToast({
					title: '图片上传失败',
					duration: 1500
				})
			},
			onListChange(lists) {
				// 当内部文件列表被加入文件、移除文件，或手动调用clear方法时触发
				// console.log('onListChange', lists);
				this.lists = lists;
			},
			beforeRemove(index, lists) {
				return true;
			}
		}
	}
</script>

<style lang="scss">
	map {
		width: 750rpx;
		height: 500rpx;
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

	.uni-list-cell {
		margin-top: 20px;
		display: flex;
		font-size: 15px;
		border-top: 1px solid #bdbdbd;
		border-bottom: 1px solid #bdbdbd;

		view {
			padding: 10rpx;
		}

		.uni-list-cell-left {
			color: #8a8a8a;
			width: 150rpx;
		}

		.uni-list-cell-db {
			width: 500rpx;

			// margin-left: 20rpx;
			picker {
				display: block;
			}
		}
	}

	.textarea {
		margin-top: 40rpx;
		margin-bottom: 40rpx;
		height: 200rpx;
	}
</style>
