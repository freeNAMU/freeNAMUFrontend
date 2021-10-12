<template>
  <nav>
    <form @submit.stop.prevent="searchDocument">
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
      window.location.href = `/document/view/${this.documentName}`
    }
  }
}
</script>