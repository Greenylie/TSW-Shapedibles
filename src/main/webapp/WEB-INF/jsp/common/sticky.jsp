<%--suppress ELValidationInspection --%>
<%--@elvariable id="cart" type="model.Cart"--%>
<%--suppress HtmlFormInputWithoutLabel --%>
<%@ page import="model.Cart" %>
<%
    Cart cart = (Cart) request.getSession().getAttribute("cart");
    if(cart == null)
    {
        cart = new Cart();
        request.getSession().setAttribute("cart", cart);
    }
%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/styles/base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/styles/pages/sticky.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded:opsz,wght,FILL,GRAD@24,400,0,0" />
    <script>
        var contextPath = "${pageContext.request.contextPath}/";
    </script>
    <script src="${pageContext.request.contextPath}/assets/script/pages/sticky.js"></script>
    <title></title>
</head>
<body>
    <div class="sticky">
        <header>
            <div class="header-wrapper glassy">
                <div class="header-left">
                    <div class="logo">
                        <a href="${pageContext.request.contextPath}">
                            <img src="${pageContext.request.contextPath}/assets/images/svg/logo.svg" alt="Logo" id="logoSvg">
                        </a>
                    </div>
                </div>
                <div class="header-center">
                    <div class="glassy" id="searchResultsContainer">
                        
                    </div>
                    <div id="errorSearch">
                        <label>Riempi questo campo</label>
                    </div>
                    <form class="searchbar" action="Search" method="get">
                        <input type="search" spellcheck="false" placeholder="      Cerca prodotti..." id="inputSearch" name="ricerca">
                        <button class="unclickable" type="submit" id="buttonSearch">
                            <span class="material-symbols-rounded">search</span>
                        </button>
                    </form>
                </div>
                <div class="header-right">
                    <a class="primary-round-btn nodeco" id="loginButton" href="${pageContext.request.contextPath}/">
                        <span class="material-symbols-rounded">login</span>
                    </a>
                    <a class="primary-round-btn nodeco" id="cartButton" href="${pageContext.request.contextPath}/Login">
                        <span class="material-symbols-rounded">shopping_cart</span>
                        <div id="cartCounter">
                            <span>${cart.getCartSize()}</span>
                        </div>
                    </a>
                </div>
            </div>
        </header>
    
        <div class="ruler">
            <div class="line"></div>
            <a class="handle" href="#main">
                <img src="${pageContext.request.contextPath}/assets/images/svg/handle.svg" alt="Logo">
            </a>
        </div>
    </div>
</body>
</html>
