<template>
  <header>
    <h1>{{ documentName }}</h1>
  </header>
</template>
<script>
export default {
  name: "DocumentHeader",
  props: ["documentName"]
}
</script>