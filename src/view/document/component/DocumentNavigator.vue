<template>
  <nav>
    <router-link to>편집</router-link>
    |
    <router-link to>역사</router-link>
  </nav>
</template>
<script>
export default {
  name: "DocumentNavigator",
  props: ["documentName"]
}
</script>