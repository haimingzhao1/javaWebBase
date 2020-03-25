<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${readercard.username}的主页</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.2.1.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js" ></script>
    <style type="text/css">
    	#dv{
    		height: 300px;
    		width: 300px;
    	}
    </style>
    <script>
        $(function () {
            $('#header').load('${pageContext.request.contextPath}/jsp/reader/reader_header.jsp');
        })
    </script>
</head>
<body background="${pageContext.request.contextPath}/static/img/wolf.jpg" style=" background-repeat:no-repeat ;
background-size:100% 100%;
background-attachment: fixed;">
<div id="header"></div>
<center>
<div style="position: relative;top: 30%" >
    <c:if test="${!empty succ}">
        <div class="alert alert-success alert-dismissable" id="dv">
            <button type="button" class="close" data-dismiss="alert"
                    aria-hidden="true">
                &times;
            </button>
               <h1 style="color: blue;"> ${succ}</h1>
               <h4> <a href="${pageContext.request.contextPath}/reader?methodName=allBooks&readerId=${readercard.readerid}">点击返回图书列表</a></h4>
        </div>
    </c:if>
    <c:if test="${!empty error}">
        <div class="alert alert-danger alert-dismissable" id="dv">
            <button type="button" class="close" data-dismiss="alert"
                    aria-hidden="true">
                &times;
            </button>
            <h1 style="color: red;"> ${error}</h1>  
            <h4> <a href="${pageContext.request.contextPath}/reader?methodName=allBooks&readerId=${readercard.readerid}">点击返回图书列表</a></h4>        
        </div>
    </c:if>
</div>
</center>
</body>
</html>
