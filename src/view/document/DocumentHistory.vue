<template>
  <ul v-if="rows">
    <li v-for="row in rows">
      {{ new Date(row["createDate"]).toLocaleString() }}
      (
      <router-link :to="{name: 'DocumentView', params: {documentName, revision: row['revisionIndex']}}">보기</router-link>
      )
      <strong>r{{ row["revisionIndex"] }}</strong>
      ({{ row["length"] }}) {{ row["contributor"] }} ({{ row["comment"] }})
    </li>
  </ul>
  <document-not-found v-if="rows === null" :document-name="documentName"/>
</template>
<script>
import DocumentNotFound from "@/view/document/component/DocumentNotFound"

export default {
  name: "DocumentHistory",
  props: ["documentName"],
  components: {DocumentNotFound},
  data () {
    return {
      rows: null
    }
  },
  created () {
    this.getHistoryOfDocument()
  },
  watch: {
    "$route": "getHistoryOfDocument"
  },
  methods: {
    getHistoryOfDocument () {
      fetch(`/document/${this.$props.documentName}/history`, {method: "get"})
          .then(response => {
            if (response.ok) {
              return response.json()
            } else if (response.status === 404) {
              return {rows: null}
            }
          })
          .then(result => this.rows = result.rows.reverse())
          .catch(alert)
    }
  }
}
</script>