<template>
  <header>
    <router-link :to="{name: 'DocumentView', params: {documentName: this.$props.documentName}}">
      <h1>
        {{ documentName }}
      </h1>
    </router-link>
  </header>
</template>
<script>
export default {
  name: "DocumentHeader",
  props: ["documentName"]
}
</script>