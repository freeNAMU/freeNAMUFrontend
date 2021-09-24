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
    }
  }
}
</script>