<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<div class="responsive-content">
      <div class="hamburger" onclick="hamburgerChange(this)">
        <div class="bar1"></div>
        <div class="bar2"></div>
        <div class="bar3"></div>
      </div>
    </div>
    <nav id="navbar" class="">
      <h1 class="logo">P<span>ERFUNITY</span></h1>
      	<form method="POST" name="ServletDashboard" action="dashboard">
            <ul id="nav-menu">
                <button class="navbar-btn" type="submit" name="action" value="index"><li><i class="fas fa-tachometer-alt"></i> Dashboard</li></button>
                <button class="navbar-btn" type="submit" name="action" value="orders"><li><i class="fas fa-shopping-cart"></i> Ordini</li></button>
                <button class="navbar-btn" type="submit" name="action" value="products"><li><i class="fas fa-cubes"></i> Prodotti</li></button>
                <button class="navbar-btn" type="submit" name="action" value="clients"><li><i class="fas fa-users"></i> Clienti</li></button>
                <button class="navbar-btn" type="submit" name="action" value="reviews"><li><i class="fas fa-star"></i> Feedback</li></button>
            	<button class="navbar-btn" type="submit" name="action" value="richieste"><li><i class="fas fa-comments"></i> Richieste</li></button>
            	<button class="navbar-btn" type="submit" name="action" value="logout"><li><i class="fas fa-power-off"></i> Logout</li></button>
            </ul>
       </form>
    </nav>
</body>
</html>