<template>
  <ul v-if="loaded && rows.length">
    <li v-for="row in rows">
      {{ new Date(row['createDateTime']).toLocaleString() }}
      (
      <a @click="toViewBy(row['revisionIndex'])">보기</a>
      )
      <strong>r{{ row['revisionIndex'] }}</strong>
      ({{ row['lengthDiffer'] }})
      {{ row['contributor'] }}
      ({{ row['comment'] }})
    </li>
  </ul>
  <document-not-found v-if="loaded && rows.length === 0" :document-name="documentName"/>
</template>
<script>
import DocumentNotFound from '@/view/document/component/DocumentNotFound'

export default {
  name: 'DocumentHistory',
  props: ['documentName'],
  components: { DocumentNotFound },
  data () {
    return {
      loaded: false,
      rows: null
    }
  },
  created () {
    this.getHistoryOfDocument()
  },
  methods: {
    toViewBy (revisionIndex) {
      this.$router.push(`/document/view/${this.$props.documentName}?rev=${revisionIndex}`)
    },
    getHistoryOfDocument () {
      this.loaded = false
      fetch(`/api/document/history/${this.$props.documentName}`, { method: 'get' })
          .then(response => {
            if (response.ok) {
              return response.json()
            } else if (!response.ok) {
              return []
            }
          })
          .then(result => {
            this.rows = result
          })
          .catch(alert)
          .finally(() => {
            this.loaded = true
          })
    }
  }
}
</script>