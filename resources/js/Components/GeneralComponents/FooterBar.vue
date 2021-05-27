<template>
  <div class="footer">
    <div class="container position-relative h-100">
       <div class="row align-items-center h-100"  >
          <div class="col col-6 link-dark">
              <p class="text-dark" >© 2021 Clubby</p>
              <a class="text-dark" href="#">Terms of Service</a> 
              <span> | </span> 
              <a class="text-dark" href="#">Privacy Policy</a>
          </div>
          
          <div class="col col-6">
              <p class="text-dark">Contact Us : info@clubby.com.tr</p>
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
  height: 80px;
  width: 100%;
  background-color:#f8f9fa;
  text-align: center; 
  position: relative;
}
.links{
    position: absolute;
    bottom: 0;
    width: 100%;
}
</style>
