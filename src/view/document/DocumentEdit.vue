<template>
  <form @submit="postDocument">
    <textarea v-model="contentBody" cols="160" rows="40"/>
    <input type="submit">
  </form>
</template>
<script>
export default {
  name: "DocumentEdit",
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
    },
    postDocument () {
      var headers = new Headers()
      headers.append("Content-Type", "application/x-www-form-urlencoded")

      var body = new URLSearchParams()
      body.append("contentBody", this.contentBody)

      const requestOptions = {
        method: "POST",
        headers,
        body
      }

      fetch(`/document/${this.$props.documentName}`, requestOptions)
          .then(this.$router.push(`/view/document/${this.$props.documentName}/${this.$props.revision}`))
    }
  }
}
</script>