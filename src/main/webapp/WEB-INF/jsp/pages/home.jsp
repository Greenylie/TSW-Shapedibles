<%@ page import="model.bean.ImageBean" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Collection" %>
<%@ page import="model.bean.ProductBean" %>
<%@ page import="model.Utils" %>
<%
    Collection<?> products = (Collection<?>) request.getAttribute("products");
    ProductBean shakerOne = (ProductBean) products.toArray()[2];
    ProductBean shakerTwo = (ProductBean) products.toArray()[17];
%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Shapedibles</title>
    
    <jsp:include page="../procedural/fractalNoise.jsp"/>
    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/styles/base.css">
    <link href="${pageContext.request.contextPath}/assets/styles/pages/home.css" rel="stylesheet" type="text/css">
</head>
<body>
    <jsp:include page="../common/sticky.jsp"/>
    <div class="content">
        <div class="shaker-carousel glassy">
            <a href="${pageContext.request.contextPath}<%="/ProductDetails?product=" + shakerOne.getCodice()%>">
                <img src="<%= Utils.getSquareImage(request,shakerOne,"transparent") %>" alt="Shaker Carousel 1">
            </a>
            <a href="${pageContext.request.contextPath}<%="/ProductDetails?product=" + shakerTwo.getCodice()%>">
                <img src="<%= Utils.getSquareImage(request,shakerTwo,"transparent") %>" alt="Shaker Carousel 2">
            </a>
        </div>
    </div>
    <jsp:include page="../common/footer.jsp"/>
</body>
</html>