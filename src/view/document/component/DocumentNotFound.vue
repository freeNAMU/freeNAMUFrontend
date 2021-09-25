<template>
  <section>
    문서를 찾을 수 없습니다. 문서를 생성하려면
    <router-link :to="{name: 'DocumentEdit', params: {documentName}}">여기</router-link>
    를 클릭하세요.
  </section>
</template>
<script>
export default {
  name: "DocumentNotFound",
  props: ["documentName"]
}
</script>