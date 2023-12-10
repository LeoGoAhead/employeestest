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
      <el-table-column type="index" label="序号" width="150" />
      <el-table-column prop="empName" label="职工姓名" width="300" />
      <el-table-column label="性别" width="120" prop="sex" />
      <el-table-column label="年龄" width="120" prop="age" />
      <el-table-column label="部门名称" width="300" prop="deptName" />
      <el-table-column label="学历" width="300" prop="empDegreeName" />
      <el-table-column label="操作">
        <template #default="{ row }">
          <el-button type="primary" size="small" @click="handleUpdate(row)">编辑</el-button>
          <el-button type="danger" size="small" @click="handleDelete(row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />
    <el-dialog :title="title" :visible.sync="dialogFormVisible" center>
      <el-form ref="dataForm" :rules="rules" :model="entity" label-position="left" label-width="80px" style="width: 90%; margin-left:50px;">
        <el-form-item label="姓名" prop="empName">
          <el-input v-model="entity.empName" placeholder="请输入职工姓名" style="width: 100%;" />
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-select v-model="entity.sex" class="filter-item" placeholder="请选择性别" style="width: 100%;">
            <el-option v-for="item in sexList" :key="item.key" :label="item.name" :value="item.key" />
          </el-select>
        </el-form-item>
        <el-form-item label="年龄" prop="age">
          <el-input v-model="entity.age" style="width: 100%;" @change="checkNum" />
        </el-form-item>
        <el-form-item label="部门名称" prop="deptName">
          <el-select v-model="entity.deptName" class="filter-item" placeholder="请选择部门" style="width: 100%;">
            <el-option v-for="item in deptNameList" :key="item.value" :label="item.name" :value="item.value" />
          </el-select>
        </el-form-item>
        <el-form-item label="学历" prop="empDegreeName">
          <el-select v-model="entity.empDegreeName" class="filter-item" placeholder="请选择学历" style="width: 100%;">
            <el-option v-for="item in empDegreeNameList" :key="item.value" :label="item.name" :value="item.value" />
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">
          取消
        </el-button>
        <el-button type="primary" @click="dialogStatus==='create'?createData():updateData()">
          保存
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import { listEmployees, createEmployees, updateEmployees, deleteEmployees } from '@/api/employees'
import Pagination from '@/components/Pagination'

export default {
  name: 'ListEmployees',
  components: { Pagination },
  data() {
    return {
      entity: {},
      tableKey: 0,
      list: null,
      total: 0,
      listLoading: true,
      listQuery: {
        empName: '',
        deptName: '',
        dmpDegreeName: '',
        pageNum: 1,
        pageSize: 10
      },
      downloadLoading: false,
      dialogFormVisible: false,
      dialogStatus: '',
      title: '',
      sexList: [
        { key: '男' },
        { key: '女' }
      ],
      deptNameList: [
        { name: '业务部', value: '业务部' },
        { name: '后勤部', value: '后勤部' },
        { name: '人事部', value: '人事部' }
      ],
      empDegreeNameList: [
        { name: '大专', value: '大专' },
        { name: '本科', value: '本科' },
        { name: '研究生', value: '研究生' }
      ],
      rules: {
        empName: [{ required: true, message: '请输入员工姓名', trigger: 'change' }],
        sex: [{ required: true, message: '请选择员工性别', trigger: 'change' }],
        age: [{ required: true, message: '请输入员工年龄', trigger: 'change' }],
        deptName: [{ required: true, message: '请选择员工部门', trigger: 'change' }],
        empDegreeName: [{ required: true, message: '请选择员工学历', trigger: 'change' }]
      }
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
      this.title = '添加职工'
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    handleUpdate(row) {
      this.title = '修改职工'
      this.entity = Object.assign({}, row) // copy obj
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    handleDelete(row) {
      this.$confirm('确定要删除吗？', '删除提示', {
        iconClass: 'el-icon-question',
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        showClose: true,
        type: 'warning'
      }).then((res) => {
        deleteEmployees(row.id).then(() => {
          this.getList()
        })
      }).catch(() => {})
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
    },
    checkNum() {
      const val = parseInt(this.entity.age)
      if (isNaN(val) || val <= 0) {
        this.$message.error('请输入正整数')
        this.entity.age = ''
      }
    },
    createData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          this.entity.username = 'www'
          this.entity.password = 'www'
          createEmployees(this.entity).then(() => {
            this.list.unshift(this.temp)
            this.dialogFormVisible = false
            this.$notify({
              title: '添加成功',
              message: '添加成功',
              type: 'success',
              duration: 2000
            })
            this.getList()
          })
        }
      })
    },
    updateData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          const data = Object.assign({}, this.entity)
          updateEmployees(data).then(() => {
            const index = this.list.findIndex(v => v.id === this.entity.id)
            this.list.splice(index, 1, this.entity)
            this.dialogFormVisible = false
            this.$notify({
              title: 'Success',
              message: 'Update Successfully',
              type: 'success',
              duration: 2000
            })
          })
        }
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
