<template>
  <section v-if="loaded && contentBody" v-text="contentBody"/>
  <document-not-found v-if="loaded && contentBody === null" :document-name="documentName"/>
</template>
<script>
import DocumentNotFound from "@/view/document/component/DocumentNotFound"

export default {
  name: "DocumentView",
  components: {DocumentNotFound},
  props: ["documentName", "revision"],
  data () {
    return {
      loaded: false,
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
      this.loaded = false
      fetch(`/document/${this.$props.documentName}/${this.$props.revision}/raw`, {method: "get"})
          .then(response => {
            this.loaded = true
            if (response.ok) {
              return response.json()
            } else if (response.status === 404) {
              return {contentBody: null}
            }
          })
          .then(result => this.contentBody = result.contentBody)
          .catch(alert)
    }
  }
}
</script>