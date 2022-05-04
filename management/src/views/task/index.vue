<template>
  <div class="app-container">

    <el-table
      :data="task_list"
      border
      style="width: 100%">

      <el-table-column
        label="损伤类型"
        width="80"
      >
        <template slot-scope="scope">
          <span style="margin-left: 10px">
          {{ typeList[scope.row.type - 1] }}
          </span>
        </template>
      </el-table-column>
      <el-table-column
        label="状态"
        width="80"
      >
        <template slot-scope="scope">
          <span style="margin-left: 10px">
          {{ status[scope.row.status] }}
          </span>
        </template>
      </el-table-column>

      <el-table-column
        label="发布日期"
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
        label="接受任务日期"
        width="200"
      >
        <template slot-scope="scope">
          <i class="el-icon-time"></i>
          <span style="margin-left: 10px">
          {{ scope.row.receiveDate | dateFormat }}
          </span>
        </template>
      </el-table-column>

      <el-table-column
        label="截止日期"
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
        width="320">
      </el-table-column>
      <el-table-column
        prop="location"
        label="位置"
        width="250">
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
        <h2 v-if='damageImage.length == 0' style="color: #F92368">暂无图片</h2>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">确定</el-button>
      </span>
    </el-dialog>


  </div>

</template>

<script>

import { getTaskAll, getDamageImage, getTaskImage} from "@/api/table"
import { parseTime } from "../../utils/index"


export default {
  data() {
    return {
      task_list: [],
      typeList: ['混合', '掉块', '裂纹', '擦伤'],
      status: ['', '', '已接受', '已提交', '已完成'],
      pn: 1,
      limit: 10,
      total: 5,
      maxPages: 1,
      dialogVisible: false,
      currentSelect: {},
      damageImage: [],
      taskImage: [],
    }
  },
  mounted(){
    this.get_task_list_all()
  },
    filters:{
    dateFormat(date){
      if (date == null){
        return '-'
      }
      return parseTime(new Date(date))
    }
  },
  methods: {
    async get_task_list_all(){
      if(this.pn > this.pages) return
      await getTaskAll(this.pn, this.limit).then(res =>{
        let pageInfo = res.extend.pageInfo
        this.task_list = pageInfo.records
        this.total = pageInfo.total
        this.maxPages = pageInfo.pages
      })
    },

    handleClick(row){
      this.dialogVisible = true
      this.currentSelect = row
      this.getDamagePicture(this.currentSelect.damageId);
    },

    // 分页大小
    handleSizeChange(val) {
      this.limit = val
    },

    // 页面跳转
    handleCurrentChange(val) {
      this.pn = val
      this.get_task_list_all(this.pn, this.limit)
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

  },

}
</script>

<style scoped>

  .pagiation{

    margin-left: 200px;
    margin-top: 20px;

  }

</style>

