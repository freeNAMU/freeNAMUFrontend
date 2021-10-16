<template>
  <article>
    <document-navigator :document-name="documentName" :revision="revision"/>
    <document-header :document-name="documentName" :revision="revision"/>
    <router-view :document-name="documentName" :revision="revision"/>
  </article>
</template>
<script>
import DocumentNavigator from './component/DocumentNavigator'
import DocumentHeader from './component/DocumentHeader'

export default {
  name: 'Document',
  components: { DocumentHeader, DocumentNavigator },
  data () {
    return {
      documentName: null,
      revision: null
    }
  },
  beforeMount () {
    this.getDocumentNameAndRevision()
  },
  watch: {
    '$route': 'getDocumentNameAndRevision'
  },
  methods: {
    getDocumentNameAndRevision () {
      this.documentName = this.$route.path
      for (let i = 0; i < 3; i++) {
        this.documentName = this.documentName.substr(this.documentName.indexOf('/') + 1)
      }
      this.revision = this.$route.query.rev
    }
  }
}
</script>