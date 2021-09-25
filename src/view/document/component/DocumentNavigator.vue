<template>
  <nav>
    <router-link :to="{name: 'DocumentEdit', params: {documentName, revision}}">편집</router-link>
    |
    <router-link :to="{name: 'DocumentHistory', params: {documentName, revision: 'latest'}}">역사</router-link>
  </nav>
</template>
<script>
export default {
  name: "DocumentNavigator",
  props: ["documentName", "revision"]
}
</script>