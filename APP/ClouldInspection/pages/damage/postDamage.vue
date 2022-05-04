<template>
	<view class="u-demo">
		<!-- 位置 -->
		<map :latitude="latitude" :longitude="longitude" scale="16" :markers=markers show-location="true">
		</map>
		<u-button @click="getLocation">{{buttonText}}</u-button>
		<u-toast ref="uToast" />
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
				<u-upload 
					:before-remove="onRemove"
					@on-success="success"
					ref="uUpload" 
					:show-upload-list="showUploadList" 
					:action="action" 
					:file-list="fileList"
					:max-count="maxCount">
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
				buttonText: "114.503931 38.083314",
				markers: [{}, {}, {}],
				poisdatas: [{}, {}, {}],
				title: 'map',
				latitude: 38.08362,
				longitude: 114.51161,
				// 用户信息
				user: {},
				// 上传图片的地址
				action: '',
				// 预置上传列表
				fileList: [],
				// 显示上传列表
				showUploadList: true,
				// 最大上传数量
				maxCount: 4,
				// 组件内部的文件列表
				lists: [],
				// 钢轨损伤类型
				damageTypeList: [
					{
						typeId: "1",
						typeName: "混合"
					},
					{
						typeId: "2",
						typeName: "掉块"
					},
					{
						typeId: "3",
						typeName: "裂纹"
					},
					{
						typeId: "4",
						typeName: "擦伤"
					},
				],
				typeIndex: 0,
				levelIndex: 0,
				description: ''
			}
		},
		onShow() {
			this.location = "114.503931,38.083314"
			var that = this
			uni.getLocation({
				type: 'gcj02',
				success: function(res) {
					that.longitude = res.longitude
					that.latitude = res.latitude
					that.buttonText = "位置：" + String(res.longitude) + " " + String(res.latitude)
				}
			});
			// 获取用户id
			let user = uni.getStorageSync('user')
			if(user != null && user != '' && user != undefined){
				this.user = user
			}
			// this.action = 'http://127.0.0.1:8086/damage_image/put/' + user.userId
			this.action = 'http://101.201.64.102:8890/damage_image/put/' + user.userId
		},
		methods: {
			getLocation() {
				var that = this
				uni.getLocation({
					type: 'gcj02',
				 success: function(res) {
						this.latitude = res.latitude
						this.longitude = res.longitude
						that.location = this.longitude+','+this.latitude
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
			},
			success(data) {
				this.lists.push({
					url: data.extend.url,
					filename: data.extend.filename
				})
			},
			error(res, index, lists) {
				// 上传失败触发
				uni.showToast({
					title: '图片上传失败',
					duration: 1500
				})
			},
			onRemove(index, lists) {
				let filename = this.lists[index].filename
				this.$u.api.removeDamagePicture(filename)
				return true;
			},
			// 提交
			formSubmit(){
				// this.latitude = 38.083314
				// this.longitude = 114.503931
				// let location = this.longitude+','+this.latitude
				
				// if(location.length < 10){
				// 	this.$refs.uToast.show({
				// 		title: '位置获取失败',
				// 		type: 'error',
				// 	})
				// 	return
				// }
				
				if(this.description == ""){
					this.description = "用户未填写损伤描述";
				}
				
				this.$u.api.postDamage(
					this.location, 
					this.damageTypeList[this.typeIndex].typeId,
					this.user.userId,
					this.description
				).then(res => {
					this.$refs.uToast.show({
						title: '上报成功',
						type: 'success',
						url: '/pages/index/index',
						duration: 1000
					})
					setTimeout(function(){
						uni.switchTab({
							url: '/pages/index/index'
						})
					}, 1000)
				})
			}
		}
	}
</script>

<style lang="scss" scoped>
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
