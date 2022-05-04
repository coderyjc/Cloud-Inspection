<template>
  <div class="app-container">

    <el-table
      :data="checkList"
      border
      style="width: 100%">


      <el-table-column
        label="损伤类型"
        width="120"
      >
        <template slot-scope="scope">
          <span style="margin-left: 10px">
          {{ typeList[scope.row.type - 1] }}
          </span>
        </template>
      </el-table-column>

      <el-table-column
        label="发布日期"
        sortable
        width="200"
      >
        <template slot-scope="scope">
          <i class="el-icon-time"></i>
          <span style="margin-left: 10px">
          {{ scope.row.postDate | dateFormat }}
          </span>
        </template>
      </el-table-column>
      <el-table-column
        label="截止日期"
        sortable
        width="200"
      >
        <template slot-scope="scope">
          <i class="el-icon-time"></i>
          <span style="margin-left: 10px">
          {{ scope.row.deadline | dateFormat }}
          </span>
        </template>
      </el-table-column>
      <el-table-column
        label="提交日期"
        sortable
        width="200"
      >
        <template slot-scope="scope">
          <i class="el-icon-time"></i>
          <span style="margin-left: 10px">
          {{ scope.row.submitDate | dateFormat }}
          </span>
        </template>
      </el-table-column>
      <el-table-column
        prop="description"
        label="描述"
        width="220">
      </el-table-column>
      <el-table-column
        label="操作"
        width="120">
        <template slot-scope="scope">
          <el-button @click="handleClick(scope.row)" type="text" size="small">查看图片</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div class="pagiation">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pn"
        :page-sizes="[10, 20, 30, 40]"
        :page-size="limit"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
      >
      </el-pagination>
    </div>

    <el-dialog
      title="详情"
      :visible.sync="dialogVisible"
      width="40%">

      <h3>损伤图片</h3>
      <div class="image-list">
        <el-image
          style="width: 100px; height: 100px"
          v-for="item in damageImage"
          :src="item"
          :preview-src-list="damageImage">
        </el-image>
      </div>
      <h3>完成图片</h3>
      <div class="image-list">
        <el-image
          style="width: 100px; height: 100px"
          v-for="item in taskImage"
          :src="item"
          :preview-src-list="taskImage">
        </el-image>
      </div>

      <span slot="footer" class="dialog-footer">
        <el-button type="danger" @click="dialogVisible = false">退回</el-button>
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="submitTask">通过</el-button>
      </span>
    </el-dialog>

  </div>

</template>

<script>

import { getList, getDamageImage, getTaskImage, submit } from "@/api/table"
import { parseTime } from "../../utils/index"


export default {
  data() {
    return {
      checkList: [],
      typeList: ['混合', '掉块', '裂纹', '擦伤'],
      pn: 1,
      limit: 5,
      total: 5,
      dialogVisible: false,
      currentSelect: {},
      damageImage: [],
      taskImage: [],
    }
  },
  mounted(){
    this.get_check_list()
  },
    filters:{
    dateFormat(date){
      return parseTime(new Date(date))
    }
  },
  methods: {
    async get_check_list(){
      await getList(1, 10).then(res =>{
        let pageInfo = res.extend.pageInfo
        this.checkList = pageInfo.records
        // console.log(pageInfo);
      })
    },
    handleClick(row){
      // console.log(row);
      this.dialogVisible = true
      this.currentSelect = row
      this.getDamagePicture(this.currentSelect.damageId);
      this.getTaskPicture(this.currentSelect.taskId);
    },
    // 分页大小
    handleSizeChange(val) {
      this.limit = val
    },

    // 页面跳转
    handleCurrentChange(val) {
    },

    async getDamagePicture(val) {
      let host = 'http://101.201.64.102:8890/static/damage_picture/'
      this.damageImage = []
      await getDamageImage(val).then(res => {

        res.extend.images.forEach(img => {
          this.damageImage.push(host + img.filename)
        })
      })

    },

    async getTaskPicture(val) {
      this.taskImage = []
      let host = 'http://101.201.64.102:8890/static/complete/'
      await getTaskImage(val).then(res => {
        res.extend.images.forEach(img => {
          this.taskImage.push(host + img)
        })
      })
    },

    submitTask(){
      submit(this.currentSelect.taskId).then(res => {
        this.get_check_list()
         this.$message({
          message: '成功',
          type: 'success'
        });
      })
      this.dialogVisible = false
    }
  },

}
</script>

<style scoped>

  .pagiation{

    margin-left: 200px;
    margin-top: 20px;

  }

</style>

