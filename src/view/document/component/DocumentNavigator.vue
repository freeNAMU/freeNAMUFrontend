<template>
  <nav>
    <router-link :to="{name: 'DocumentEdit', params: {documentName: this.$props.documentName}}">편집</router-link>
    |
    <router-link :to="{name: 'DocumentHistory', params: {documentName: this.$props.documentName}}">역사</router-link>
  </nav>
</template>
<script>
export default {
  name: "DocumentNavigator",
  props: ["documentName"]
}
</script>