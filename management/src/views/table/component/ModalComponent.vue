<template>

</template>

<script>
   export default {
    name: "ModalComponent",
    props: {
      dialogVisible: Boolean,
    },
    data() {
      return {
      };
    },
    methods: {
      handleClose(done) {
        this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {});
      }
    }
  };
</script>
