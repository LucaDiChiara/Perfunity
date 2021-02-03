<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.Perfunity.Model.*"%>
<html>
    <head>
       <%@include file="headPanel.jsp" %>
    </head>
    <body>
        <%@include file="navbarPanel.jsp" %>
        <%
        	ProdottoBean pb = (ProdottoBean) request.getAttribute("pb");
        %>

        <div id="container">
            <h1 class="title"><i class="fas fa-cubes"></i> Prodotto</h1>
            
            <hr>
            <br><br>
            <%
            if(pb!=null)
            {
        		if(pb.getProdottoID()==null)
        		{
            %>
            <form accept-charset="UTF-8" enctype="multipart/form-data" method="POST" action="nuovo-prodotto" name="ServletNew">
                <label for="title-product">Nome: </label>
                <input type="text" class="col-12" name="nome" value="" placeholder="Nome" />
                <br><br>
                <hr>
                <br>
                <label for="title-product">Prezzo: EUR</label>
                <input type="text" name="prezzo" value="" placeholder="Prezzo" />
                <br><br>
                <label for="">Categoria: </label>
                <select name="categoria">
                    <option value="Uomo">Uomo</option>
                    <option value="Donna">Donna</option>
                    <option value="Kids">Kids</option>
                    <option value="Gift">Gift box</option>
                </select>
                <hr>
                <br>
                <label for="title-product">Descrizione: </label><br>
                <textarea name="descrizione" id="" class="col-12" rows="10" placeholder="Breve descrizione prodotto" style="resize:none;"></textarea>
                <br><br><hr><br>
                <label for="image-product">Foto prodotto:</label>
                <input type="file" name="file" multiple="true" accept="image/png, image/jpeg" />
                <br><br>
                <button type="submit" name="action" value="insertP" class="btn btn-primary col-12">Invia</button>
            </form>
            <%
        		}
        		else
        		{
            %>
            <form accept-charset="UTF-8" enctype="multipart/form-data" method="POST" action="nuovo-prodotto" name="ServletNew">
                <label for="title-product">Nome: </label>
                <input type="text" class="col-12" name="nome" value="<%=pb.getNome() %>" placeholder="Nome" />
                <br><br>
                <hr>
                <br>
                <label for="title-product">Prezzo: EUR</label>
                <input type="text" name="prezzo" value="<%=pb.getPrezzo() %>" placeholder="Prezzo" />
                <br><br>
                <label for="">Categoria: </label>
                <select name="categoria">
                    <option value="Uomo">Uomo</option>
                    <option value="Donna">Donna</option>
                    <option value="Kids">Kids</option>
                    <option value="Gift">Gift box</option>
                </select>
                <hr>
                <br>
                <label for="title-product">Descrizione: </label><br>
                <textarea name="descrizione" id="" class="col-12" rows="10" placeholder="Breve descrizione prodotto" style="resize:none;"><%=pb.getDescrizione() %></textarea>
                <br><br><hr><br>
                <label for="image-product">Foto prodotto:</label>
                <input type="file" name="file" multiple="true" accept="image/png, image/jpeg" />
                <br><br>
                <button type="submit" name="action" value="insertP" class="btn btn-primary col-12">Invia</button>
            </form>
            <%
        		}
            }
            %>
        </div>
        <%@include file="footerPanel.jsp" %>
    </body>
</html>