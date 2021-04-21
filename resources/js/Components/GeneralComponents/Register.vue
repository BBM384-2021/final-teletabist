<template>
    <div class="registerPage overflow-hidden justify-content-center align-self-center mt-5" >
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
<div class="container register">
            <div class="row">
                <b-col cols="6">
                    <img src="http://placehold.it/500x400" alt=""/>
                </b-col>

                <b-col cols="6">
                    <ul class="nav nav-tabs nav-pills nav-fill">
                        <li class="nav-item">
                            <a class="nav-link " aria-current="page" href="#" >LogIn</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" href="#">Register</a>
                        </li>
                    </ul>
                    <b-row>
                        <b-col cols ="2">

                        </b-col>
                        <b-col cols="8">
                            <br>
                            <form action="">
                                <div class="form-group input-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                                    </div>
                                    <input name="" class="form-control" placeholder="Name" type="text">
                                </div> 

                                <div class="form-group input-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"> <i class="fa fa-envelope"></i> </span>
                                    </div>
                                    <input name="" class="form-control" placeholder="Email address" type="email">
                                </div> 

                                <div class="form-group input-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
                                    </div>
                                    <input class="form-control" placeholder="Create password" type="password">
                                </div>

                                <div class="form-group input-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
                                    </div>
                                    <input class="form-control" placeholder="Confirm password" type="password">
                                </div>       
   
                            </form>
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" checked>
                                <label class="form-check-label" for="flexCheckChecked">
                                   I Accept
                                   <a href="#" class="link-dark" > Terms and Conditions</a>
                                </label>
                            </div>
                            <p>Already have an account?<a href="#" class="link-dark">  Sign In</a></p>
                            <div class="form-group">
                                <b-button href="#" pill variant="outline-primary" class="btn pull-right mb-1" size="sm" id="registerButton">SIGN UP</b-button>
                            </div>  
                        </b-col>
                        <b-col cols="2">
                        </b-col>
                    </b-row>                        
                </b-col>
             </div> 
        </div>
    </div>
</template>

<script>

export default {
  name: 'Register',
 

}
</script>

<style>
.registerPage{
    height: auto;
    width: 100%;
    border: 1px inset black;
    border-radius: 15px;
    /* position: relative; */
}
.register{
    background: -webkit-linear-gradient(left, #3931af, pink);

}
.register-right{
    background: #f8f9fa;
    border-top-left-radius: 10% 50%;
    border-bottom-left-radius: 10% 50%;
}
#registerButton{
    width: 135px;
}


</style>
