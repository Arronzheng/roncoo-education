<template>
  <div class="container">
    <div class="text">千名营销学院</div>
    <div>
      <el-divider></el-divider>
      <div>

      </div>
    </div>
  </div>
</template>
<script>
import { mapGetters } from 'vuex'
export default {
  name: 'Dashboard',
  methods: {
    jumpUrl(type) {
      switch (type) {
        case 'home':
          window.open('https://localhost:3000/')
          break;
        case 'demo':
          window.open('https://localhost:3000/')
          break;
        case 'fineUI':
          window.open('https://localhost:3000/')
          break;
      }
    }
  },
  computed: {
    ...mapGetters([
      'name'
    ])
  }
}
</script>
<style scoped>
.container {
  margin: 30px;
}
.bg-purple {
  background: #d3dce6;
}
.bg-purple-light {
    background: #e5e9f2;
  }
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
.text {
  font-size: 20px;
  line-height: 46px;
}
</style>
