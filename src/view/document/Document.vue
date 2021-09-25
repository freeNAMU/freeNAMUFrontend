<template>
  <article>
    <document-navigator :document-name="documentName" :revision="revision"/>
    <document-header :document-name="documentName" :revision="revision"/>
    <router-view :document-name="documentName" :revision="revision"/>
  </article>
</template>
<script>
import DocumentNavigator from "./component/DocumentNavigator"
import DocumentHeader from "./component/DocumentHeader"

export default {
  name: "Document",
  props: ["documentName", "revision"],
  components: {DocumentHeader, DocumentNavigator}
}
</script>
<style scoped>
</style>