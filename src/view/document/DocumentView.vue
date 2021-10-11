<template>
  <div v-if="loaded && contentBody" v-html="contentBody"/>
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
    this.getRenderedDocument()
  },
  methods: {
    getRenderedDocument () {
      this.loaded = false
      let url = `/api/document/render/${this.$props.documentName}`
      if (this.$props.revision !== undefined) {
        url += `?rev=${this.$props.revision}`
      }
      fetch(url, {method: "get"})
          .then(response => {
            if (response.ok) {
              return response.json()
            } else if (!response.ok) {
              return {contentBody: null}
            }
          })
          .then(result => this.contentBody = result.contentBody)
          .catch(alert)
          .finally(() => {
            this.loaded = true
          })
    }
  }
}
</script>