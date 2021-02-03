<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.Perfunity.Model.*" import="java.util.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Perfunity</title>
        <link rel="stylesheet" type="text/css" href="assets/css/screen.css" media="screen" /> 
        <link rel="stylesheet" type="text/css" href="assets/css/reset.css" /> 
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
    </head>

    <body>
        <header id="header-bar">
            <div class="header-left">
                <div class="responsive-content">
                    <div class="hamburger" onclick="hamburgerChange(this)">
                        <div class="bar1"></div>
                        <div class="bar2"></div>
                        <div class="bar3"></div>
                    </div>
                </div>
                <div class="navbar-icon left-icon">
                    <i id="navbar-search" class="fas fa-search"></i>
                </div>
            </div>
            
            <h1 class="navbar-logo">P<span>ERFUNITY</span></h1>

            <div class="header-right">
                <div class="navbar-icon right-icon">
                    <i id="navbar-profile" class="fas fa-user right-icon"></i>
                    <a href="cart.jsp"><i class="fas fa-shopping-cart right-icon"></i></a>
                </div>
            </div>
            
        </header>
        <nav id="navbar">
            <ul id="profile-menu">
            	<form method="POST" name="ServletProfile" action="profilo">
                	<li><button class="navbar-button" type="submit" name="button" value="profile">Il mio account</button></li>
                	<li><button class="navbar-button" type="submit" name="button" value="logout">Logout</button></li>
                </form>
            </ul>
			<form method="GET" action="cerca" name="ServletRicerca">
            	<div id="search-box">
                	<input type="text" name="parametro" placeholder="Cerca" />
            	</div>
			</form>
            <ul id="nav-menu">
               <form method="GET" name="ServletNav" action="Categoria">
               <!--  <ul class="nav left-nav"> -->
               		<li><button class="navbar-button" type="submit" name="type" value="home"><i class="fas fa-home"></i> Home</button></li>
                	<li><button class="navbar-button" type="submit" name="type" value="uomo"><i class="fas fa-mars"></i> Uomo</button></li>
                  	<li><button class="navbar-button" type="submit" name="type" value="donna"><i class="fas fa-venus"></i> Donna</button></li>
                    <li><button class="navbar-button" type="submit" name="type" value="kids"><i class="fas fa-child"></i> Kids</button></li>
                    <li><button class="navbar-button" type="submit" name="type" value="gift"><i class="fas fa-gift"></i> Confezioni</button></li>
                <!--</ul>  --> 
                </form>
            </ul>
            
        </nav>
    </body>
</html>