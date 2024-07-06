<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/styles/pages/sticky.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <script src="${pageContext.request.contextPath}/assets/script/pages/sticky.js"></script>
</head>
<body>
    <div class="sticky">
        <header>
            <div class="header-wrapper">
                <div class="header-left">
                    <div class="logo">
                        <a href="${pageContext.request.contextPath}">
                            <img src="${pageContext.request.contextPath}/assets/images/svg/logo.svg" alt="Logo" id="logoSvg">
                        </a>
                    </div>
                </div>
                <div class="header-center">
                    <form class="searchbar" action="Search" method="get">
                        <input type="search" placeholder="      Cerca prodotti..." id="inputSearch" name="ricerca">
                        <button class="unclickable" type="submit" id="buttonSearch">
                            <i class="material-icons">search</i>
                        </button>
                    </form>
                </div>
                <div class="header-right">
                    <a href="#login">Login</a>
                    <a href="#register">Register</a>
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
