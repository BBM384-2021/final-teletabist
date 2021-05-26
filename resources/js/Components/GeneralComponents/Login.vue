<template>
    <div class="loginPage overflow-hidden" >
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
<div class="container register">
            <div class="row">
                <b-col cols="6"  class="p-0 m-0">
                     <div class="image_class_login">
                        

                    </div>
                </b-col>
                <b-col cols="6">
                    <ul class="nav nav-tabs nav-pills nav-fill">
                        <li class="nav-item">
                            <a class="nav-link btn selectedButton disabled">Login</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link " aria-current="page" href="/account/register">Register</a>
                        </li>
                    </ul>
                    <b-row>
                        <b-col cols ="2">

                        </b-col>
                        <b-col cols="8">
                            <br><br>
                            <!-- <form action=""> -->
                                <div class="form-group input-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                                    </div>
                                    <input name="username" class="form-control" placeholder="User Name" type="text">
                                </div> 

                                <div class="form-group input-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
                                    </div>
                                    <input name="password" class="form-control" placeholder="Create password" type="password">
                                </div>
                              
                            <!-- </form> -->
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
                                    <label class="form-check-label" for="flexCheckDefault">Remember Me</label>
                            </div>
                            <br>
                            <p>Don’t have an account? <a href="#" class="link-dark">Sign Up</a></p>
                            <a href="#" class="link-dark">I forgat my password</a>
                            <br><br>
                            <div class="form-group">
                                 <b-button pill variant="outline-primary" class="btn pull-right mb-1 float-right" size="sm" id="registerButton" type="submit">SIGN IN</b-button>
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
  name: 'Login',
}
</script>

<style>
.loginPage{
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
#loginButton{
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
.image_class_login{
    /* background: url('https://media3.giphy.com/media/U1oKFZmnqtobu/200.gif') no-repeat;  */
    background: url('https://media1.tenor.com/images/367cdcf4d45f8a1aad6ee5e8e92d0411/tenor.gif?itemid=15186749') no-repeat;
    background-size: 100%;
    width : 100%;
    height: 500px;
    background-size: cover;
    background-position: center center;
}
</style>
