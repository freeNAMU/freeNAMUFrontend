<template>
  <nav>
    <a @click="toEdit">편집</a>
    |
    <a @click="toHistory">역사</a>
  </nav>
</template>
<script>
export default {
  name: "DocumentNavigator",
  props: ["documentName", "revision"],
  methods: {
    toEdit () {
      let url = `/document/edit/${this.$props.documentName}`
      if (this.$props.revision !== undefined) {
        url += `?rev=${this.$props.revision}`
      }
      this.$router.push(url)
    },
    toHistory () {
      this.$router.push(`/document/history/${this.$props.documentName}`)
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