$('#navbar-search').on('click', function(){
    $('#search-box').toggleClass('showBox');
});

$('#navbar-profile').on('click', function(){
    $('#navbar #profile-menu').toggleClass('showMenu');
});

$('.hamburger').on('click', function(){
    $('#navbar #nav-menu').toggleClass('showMenu');
});
