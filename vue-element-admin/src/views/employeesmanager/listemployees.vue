<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input v-model="listQuery.name" placeholder="姓名" style="width: 200px;" class="filter-item" @keyup.enter.native="handleFilter" />
      <el-select v-model="listQuery.deptName" placeholder="部门" clearable style="width: 90px" class="filter-item">
        <!-- <el-option v-for="item in importanceOptions" :key="item" :label="item" :value="item" /> -->
      </el-select>
      <el-select v-model="listQuery.dmpDegreeName" placeholder="学历" clearable class="filter-item" style="width: 130px">
        <!-- <el-option v-for="item in calendarTypeOptions" :key="item.key" :label="item.display_name+'('+item.key+')'" :value="item.key" /> -->
      </el-select>
      <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">
        查询
      </el-button>
      <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit" @click="handleCreate">
        添加
      </el-button>
      <el-button v-waves :loading="downloadLoading" class="filter-item" type="primary" icon="el-icon-download" @click="handleDownload">
        导出
      </el-button>
    </div>

    <el-table :key="tableKey" v-loading="listLoading" :data="list" border fit highlight-current-row style="width: 100%;">
      <el-table-column prop="index" label="序号" width="150" />
      <el-table-column prop="empName" label="职工姓名" width="300" />
      <el-table-column label="性别" width="120" prop="sex" />
      <el-table-column label="年龄" width="120" prop="age" />
      <el-table-column label="部门名称" width="300" prop="deptName" />
      <el-table-column label="学历" width="300" prop="empDegreeNamw" />
      <el-table-column label="操作">
        <template>
          <el-button :icon="Edit" circle plain type="primary" />
          <el-button :icon="Delete" circle plain type="danger" />
        </template>
      </el-table-column>
    </el-table>
    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />
  </div>
</template>
<script>
import { listEmployees } from '@/api/employees'
import Pagination from '@/components/Pagination'

export default {
  name: 'ListEmployees',
  components: { Pagination },
  data() {
    return {
      tableKey: 0,
      list: null,
      total: 0,
      listLoading: true,
      listQuery: {
        name: '',
        deptName: '',
        dmpDegreeName: '',
        pageNum: 1,
        pageSize: 10
      },
      downloadLoading: false,
      tableData: [
        {
          date: '2016-05-02',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        }, {
          date: '2016-05-04',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1517 弄'
        }, {
          date: '2016-05-01',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1519 弄'
        }, {
          date: '2016-05-03',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1516 弄'
        }
      ]
    }
  },
  created() {
    this.getList()
  },
  methods: {
    handleFilter() {
      this.listQuery.pageNum = 1
      this.getList()
    },
    handleCreate() {

    },
    handleDownload() {

    },
    getList() {
      this.listLoading = true
      listEmployees(this.listQuery).then(response => {
        this.list = response.data.items
        this.total = response.data.total
        this.listLoading = false
      })
    }
  }
}
</script>
<style lang="css">
.el-table__header{
    width: 100%
}
.el-table__body {
    width: 100%
}
</style>
