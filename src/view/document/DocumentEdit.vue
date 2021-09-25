<template>
  <form @submit.stop.prevent="postDocument">
    <textarea v-model="contentBody"/>
    <input v-model="comment" maxlength="255" type="text">
    <button type="submit">제출</button>
  </form>
</template>
<script>
export default {
  name: "DocumentEdit",
  props: ["documentName", "revision"],
  data () {
    return {
      contentBody: null,
      comment: null
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
            } else if (response.status === 404) {
              return {contentBody: null}
            }
          })
          .then(result => this.contentBody = result.contentBody)
          .catch(alert)
    },
    postDocument () {
      const body = new FormData()
      body.append("contentBody", this.contentBody)
      body.append("comment", this.comment)

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

input {
  width: 100%;
}
</style>
