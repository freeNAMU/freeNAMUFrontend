<template>
  <global-navigator/>
  <router-view/>
  <global-footer/>
</template>
<script>
import GlobalNavigator from '@/view/GlobalNavigator'
import GlobalFooter from '@/view/GlobalFooter'

export default {
  components: { GlobalFooter, GlobalNavigator }
}
</script>