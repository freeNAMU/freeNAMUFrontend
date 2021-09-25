<template>
  <header>
    <a @click=toLatest>
      <h1>
        {{ documentName }}
      </h1>
    </a>
  </header>
</template>
<script>
export default {
  name: "DocumentHeader",
  props: ["documentName"],
  methods: {
    toLatest () {
      window.location.href = `/view/document/${this.documentName}/latest`
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