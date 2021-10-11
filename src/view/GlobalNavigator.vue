<template>
  <nav>
    <form @submit="searchDocument">
      <input v-model="documentName" type="text">
      <button type="submit">검색</button>
    </form>
  </nav>
</template>
<script>
export default {
  name: "GlobalNavigator",
  data () {
    return {
      documentName: null
    }
  },
  methods: {
    searchDocument () {
      this.$router.push(`/document/view/${this.documentName}`)
    }
  }
}
</script>