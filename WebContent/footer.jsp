 <footer class="footer">
            <div class="col-4">
                <h3 class="title-box">Chi siamo</h3>
                <p class="about-us">Perfunity nasce nel 2019 dalla collaborazione di 3 giovani universitari per il progetto di Tecnologie Software per il Web.</p>
                <p class="about-us">L'idea di creare uno shop online che proponesse solo profumi d'ispirazione ha da subito convinto il team. Tutti i prodotti sono una perfetta coniugazione tra qualità e prezzo.</p>
            </div>

            <div class="col-4 sitemaps">
                <div class="col-6">
                    <h3 class="title-box">Categorie</h3>
                    <ul id="nav-menu" class="sitemap">
                        <a href="Categoria?type=uomo"><li>Uomo</li></a>
                        <a href="Categoria?type=donna"><li>Donna</li></a>
                        <a href="Categoria?type=kids"><li>Kids</li></a>
                        <a href="Categoria?type=gift"><li>Confezioni regalo</li></a>
                    </ul>
                </div>
                <div class="col-4">
                    <h3 class="title-box">Pagine</h3>
                    <ul id="nav-menu" class="sitemap">
                        <a href="index.jsp"><li>Home</li></a>
                        <a href="cart.jsp"><li>Carrello</li></a>
                        <!--  <a href="profile.jsp"><li>Profilo</li></a> -->
                    </ul>
                </div>
            </div>
            <div class="col-4">
                <h3 class="title-box">Cerca</h3>
                <br>
               <form method="GET" action="cerca" name="ServletRicerca">
                    <input class="col-6" style="height:35px; font-size:20px;" type="text" name="parametro" placeholder="Cerca" />
                    <button class="col-2 bg-primary" style="height: 35px;"><i id="navbar-search"  class="fas fa-search"></i></button>
                </form>
                <br><br>
                <h3 class="title-box">Seguici</h3>
                <div class="follow-us">
                    <div class="follow-icon"><i class="fab fa-facebook-f"></i></div>
                    <div class="follow-icon"><i class="fab fa-twitter"></i></div>
                    <div class="follow-icon"><i class="fab fa-pinterest"></i></div>
                    <div class="follow-icon"><i class="fab fa-snapchat"></i></div>
                </div>
            </div>
        </footer>
        <script>
            function hamburgerChange(x) {
                x.classList.toggle("hamburger-change-icon");
            }
        </script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="assets/js/navbar.js"></script>