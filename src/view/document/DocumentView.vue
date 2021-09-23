<template>
  <section v-if="loaded" v-text="contentBody"/>
  <div v-if="loading">loading...</div>
  <div v-if="error">not found...</div>
</template>
<script>
export default {
  name: "DocumentView",
  props: ["documentName", "revision"],
  data () {
    return {
      loading: true,
      loaded: null,
      error: null,
      contentBody: null
    }
  },
  created () {
    this.getDocument()
  },
  watch: {
    "$route": "getDocument"
  },
  methods: {
    getDocument () {
      this.loading = true
      this.loaded = false
      this.error = false
      fetch(`/document/${this.$props.documentName}/${this.$props.revision}/raw`, {method: "get"})
          .then(response => response.json())
          .then(result => {
            this.contentBody = result.contentBody
            this.loaded = true
            this.loading = false
          })
          .catch(() => {
            this.error = true
            this.loading = false
          })
    }
  }
}
</script>