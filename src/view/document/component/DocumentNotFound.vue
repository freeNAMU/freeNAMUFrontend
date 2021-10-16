<template>
  <div>
    문서를 찾을 수 없습니다. 문서를 생성하려면
    <a @click="toEdit">여기</a>
    를 클릭하세요.
  </div>
</template>
<script>
export default {
  name: 'DocumentNotFound',
  props: ['documentName'],
  methods: {
    toEdit () {
      this.$router.push(`/document/edit/${this.$props.documentName}`)
    }
  }
}
</script>
<style scoped>
a {
  cursor: pointer;
}

a:hover {
  text-decoration: underline;
}
</style>