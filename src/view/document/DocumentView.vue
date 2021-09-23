<template>
  <section v-text="contentBody"/>
</template>
<script>
export default {
  name: "DocumentView",
  props: ["documentName", "revision"],
  data () {
    return {
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
      const requestOptions = {
        method: "get"
      }

      fetch(`/document/${this.$props.documentName}/${this.$props.revision}/raw`, requestOptions)
          .then(response => response.json())
          .then(result => this.contentBody = result.contentBody)
    }
  }
}
</script>