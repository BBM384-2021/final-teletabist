<template>
    <div class="registerPage overflow-hidden" >
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
<div class="container register">
            <div class="row">
                <b-col cols="6" class="p-0 m-0">
                    <img src="http://placehold.it/550x500" alt=""/>
                </b-col>

                <b-col cols="6">
                    <ul class="nav nav-tabs nav-pills nav-fill">
                        <li class="nav-item">
                            <a class="nav-link " aria-current="page" href="#" >LogIn</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link btn selectedButton" href="#">Register</a>
                        </li>
                    </ul>
                    <b-row>
                        <b-col cols ="2">

                        </b-col>
                        <b-col cols="8">
                            <br>
                                <div class="form-group input-group mt-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                                    </div>
                                    <input id="name" name="name" class="form-control" placeholder="Name" type="text">
                                </div> 
                                <div class="form-group input-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                                    </div>
                                    <input id="username" name="username" class="form-control" placeholder="UserName" type="text">
                                </div> 

                                <div class="form-group input-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"> <i class="fa fa-envelope"></i> </span>
                                    </div>
                                    <input name="email" id="email" class="form-control" placeholder="Email address" type="email">
                                </div> 

                                <div class="form-group input-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
                                    </div>
                                    <input name="password" id="password" class="form-control" placeholder="Create password" type="password">
                                </div>

                                <div class="form-group input-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
                                    </div>
                                    <input name="password_validate" id="password_validate"  class="form-control" placeholder="Confirm password" type="password">
                                </div>                               
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" checked>
                                <label class="form-check-label" for="flexCheckChecked">
                                   I Accept
                                   <a href="#" class="link-dark" > Terms and Conditions</a>
                                </label>
                            </div>
                            <p>Already have an account?<a href="#" class="link-dark">  Sign In</a></p>
                            <div class="form-group">
                                 <b-button pill variant="outline-primary" class="btn pull-right mb-1 float-right" size="sm" id="registerButton" type="submit">SIGN UP</b-button>
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
    height: 500px;
}
.register-right{
    background: #f8f9fa;
    border-top-left-radius: 10% 50%;
    border-bottom-left-radius: 10% 50%;
}
#registerButton{
    width: 135px;
}
.selectedButton{
    background-image: linear-gradient(to right, #fbc2eb 0%, #a6c1ee 51%, #fbc2eb 100%);
    background-position: right center;
    transition: 0.5s;
    background-size: 200% auto;
} 
.selectedButton:hover{
    background-position: center center;
}


</style>
