<template>
  <ul v-if="loaded && rows">
    <li v-for="row in rows">
      {{ new Date(row["createDate"]).toLocaleString() }}
      (
      <router-link :to="{name: 'DocumentView', params: {documentName, revision: row['revisionIndex']}}">보기</router-link>
      )
      <strong>r{{ row["revisionIndex"] }}</strong>
      ({{ row["length"] }})
      {{ row["contributor"] }}
      ({{ row["comment"] }})
    </li>
  </ul>
  <document-not-found v-if="loaded && rows === null" :document-name="documentName"/>
</template>
<script>
import DocumentNotFound from "@/view/document/component/DocumentNotFound"

export default {
  name: "DocumentHistory",
  props: ["documentName"],
  components: {DocumentNotFound},
  data () {
    return {
      loaded: false,
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
      this.loaded = false
      fetch(`/api/document/${this.$props.documentName}/history`, {method: "get"})
          .then(response => {
            if (response.ok) {
              return response.json()
            } else if (!response.ok) {
              return {rows: null}
            }
          })
          .then(result => {
            if (result.rows) {
              result.rows = result.rows.reverse()
              for (let i = 0; i < result.rows.length - 1; i++) {
                result.rows[i]["length"] = result.rows[i]["length"] - result.rows[i + 1]["length"]
                if (result.rows[i]["length"] > 0)
                  result.rows[i]["length"] = "+" + result.rows[i]["length"]
              }
            }
            this.rows = result.rows
          })
          .catch(alert)
          .finally(() => {
            this.loaded = true
          })
    }
  }
}
</script>