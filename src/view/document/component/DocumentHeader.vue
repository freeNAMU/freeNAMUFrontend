<template>
  <header>
    <a @click=toLatest>
      <h1>
        {{ decodeURI(documentName) }}
        <sub v-if="revision !== undefined">(r{{ revision }})</sub>
      </h1>
    </a>
  </header>
</template>
<script>
export default {
  name: "DocumentHeader",
  props: ["documentName", "revision"],
  methods: {
    toLatest () {
      window.location.href = `/document/view/${this.documentName}`
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