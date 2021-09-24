<template>
  <form @submit.stop.prevent="postDocument">
    <textarea v-model="contentBody"/>
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
      fetch(`/document/${this.$props.documentName}/${this.$props.revision}/raw`, {method: "get"})
          .then(response => {
            if (response.ok) {
              return response.json()
            } else {
              throw new Error(response.status)
            }
          })
          .then(result => this.contentBody = result.contentBody)
          .catch(alert)
    },
    postDocument () {
      const body = new FormData()
      body.append("contentBody", this.contentBody)

      fetch(`/document/${this.$props.documentName}`, {method: "POST", body})
          .then(response => {
            if (response.ok) {
              this.$router.push(`/view/document/${this.$props.documentName}/latest`)
            } else {
              throw new Error(response.status)
            }
          })
          .catch(alert)
    }
  }
}
</script>

<style scoped>
textarea {
  box-sizing: border-box;
  width: 100%;
}
</style>
