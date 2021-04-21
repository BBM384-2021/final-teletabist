<template>
  <div class="footer">
    <div class="container position-relative h-100">
       <div class="row align-items-center h-100"  >
          <div class="col col-3 link-dark">
              <a class="text-dark" href="#">EXAMPLE</a>
          </div>
          <div class="col col-3">
              <a class="text-dark" href="#">EXAMPLE</a>
          </div>
          <div class="col col-3">
              <a class="text-dark" href="#">EXAMPLE</a>
          </div>
          <div class="col col-3">
              <a class="text-dark" href="#">EXAMPLE</a>
          </div>
        </div>
    </div>
  </div>
</template>
<script>

export default {
  name: 'FooterBar',
  props: { 
  }
  
}
</script>

<style>
.footer {
  height: 120px;
  width: 100%;
  background-color: #C4C4C4;
  text-align: center; 
  position: relative;
}
.links{
    position: absolute;
    bottom: 0;
    width: 100%;
}
</style>
