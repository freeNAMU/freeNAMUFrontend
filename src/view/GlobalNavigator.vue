<template>
  <nav>
    <form @submit="searchDocument">
      <input type="text" v-model="documentName">
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
      this.$router.push(`/view/document/${this.documentName}/latest`)
    }
  }
}
</script>