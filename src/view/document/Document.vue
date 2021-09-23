<template>
  <article>
    <document-navigator v-bind:document-name="$props.documentName"/>
    <document-header v-bind:document-name="$props.documentName"/>
    <router-view v-bind:document-name="$props.documentName"/>
  </article>
</template>
<script>
import DocumentNavigator from "./component/DocumentNavigator"
import DocumentHeader from "./component/DocumentHeader"

export default {
  name: "Document",
  props: ["documentName"],
  components: {DocumentHeader, DocumentNavigator}
}
</script>
<style scoped>
</style>