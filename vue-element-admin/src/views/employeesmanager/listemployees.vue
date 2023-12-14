<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input v-model="listQuery.name" placeholder="姓名" style="width: 200px;" class="filter-item" @keyup.enter.native="handleFilter" />
      <el-select v-model="listQuery.deptNameId" placeholder="部门" clearable style="width: 90px" class="filter-item">
        <el-option v-for="item in deptNameList" :key="item.key" :label="item.value" :value="item.key" />
      </el-select>
      <el-select v-model="listQuery.dmpDegreeNameId" placeholder="学历" clearable class="filter-item" style="width: 130px">
        <el-option v-for="item in empDegreeNameList" :key="item.key" :label="item.value" :value="item.key" />
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
            <el-option v-for="item in sexList" :key="item.key" :label="item.value" :value="item.key" />
          </el-select>
        </el-form-item>
        <el-form-item label="年龄" prop="age">
          <el-input v-model="entity.age" style="width: 100%;" placeholder="请输入年龄" @change="checkNum" />
        </el-form-item>
        <el-form-item label="部门名称" prop="deptName">
          <el-select v-model="entity.deptName" class="filter-item" placeholder="请选择部门" style="width: 100%;">
            <el-option v-for="item in deptNameList" :key="item.key" :label="item.value" :value="item.key" />
          </el-select>
        </el-form-item>
        <el-form-item label="学历" prop="empDegreeName">
          <el-select v-model="entity.empDegreeName" class="filter-item" placeholder="请选择学历" style="width: 100%;">
            <el-option v-for="item in empDegreeNameList" :key="item.key" :label="item.value" :value="item.key" />
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
import { listSex, listDepartment, listDegreeList, listEmployees, createEmployees, updateEmployees, deleteEmployees } from '@/api/employees'
import { downloadExcel } from '@/utils/download'
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
        deptNameId: null,
        dmpDegreeNameId: null,
        pageNum: 1,
        pageSize: 10
      },
      downloadLoading: false,
      dialogFormVisible: false,
      dialogStatus: '',
      title: '',
      sexList: [],
      deptNameList: [],
      empDegreeNameList: [],
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
    this.initSexList()
    this.initDepartmentList()
    this.initDegreeList()
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
      this.entity = Object.assign({}, row)
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
      downloadExcel('/employees/exportemployees').then(res => {})
    },
    initSexList() {
      listSex().then(res => {
        this.sexList = res.data
      })
    },
    initDepartmentList() {
      listDepartment().then(res => {
        this.deptNameList = res.data
      })
    },
    initDegreeList() {
      listDegreeList().then(res => {
        this.empDegreeNameList = res.data
      })
    },
    getList() {
      this.listLoading = true
      listEmployees(this.listQuery).then(response => {
        this.list = response.data.items
        this.list.forEach((x) => {
          const sexitem = this.sexList.filter((k) => k.key === x.sex)[0]
          x.sex = sexitem.value
          const deptitem = this.deptNameList.filter((k) => k.key === x.deptName)[0]
          x.deptName = deptitem.value
          const degreeitem = this.empDegreeNameList.filter((k) => k.key === x.empDegreeName)[0]
          x.empDegreeName = degreeitem.value
        })
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
          if (typeof data.sex === 'string') {
            data.sex = this.sexList.filter((k) => k.value === data.sex)[0].key
          }
          if (typeof data.deptName === 'string') {
            data.deptName = this.deptNameList.filter((k) => k.value === data.deptName)[0].key
          }
          if (typeof data.empDegreeName === 'string') {
            data.empDegreeName = this.empDegreeNameList.filter((k) => k.value === data.empDegreeName)[0].key
          }
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
            this.handleFilter()
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
