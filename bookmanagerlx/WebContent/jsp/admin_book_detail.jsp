<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>《 ${map.bookinfo.name}》</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.2.1.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
    <script>
        $(function () {
            $('#header').load('${pageContext.request.contextPath}/jsp/admin_header.jsp');
        })
    </script>
</head>
<body background="${pageContext.request.contextPath}/static/img/book2.jpg" style=" background-repeat:no-repeat ;
background-size:100% 100%;
background-attachment: fixed;">
<div id="header" style="padding-bottom: 80px"></div>

<div class="col-xs-6 col-md-offset-3">
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3 class="panel-title">《 ${map.bookinfo.name}》</h3>
        </div>
        <div class="panel-body">
            <table class="table table-hover">
                <tbody>
                <tr>
                    <th width="15%">书名</th>
                    <td>${map.bookinfo.name}</td>
                </tr>
                <tr>
                    <th>作者</th>
                    <td>${map.bookinfo.author}</td>
                </tr>
                <tr>
                    <th>出版社</th>
                    <td>${map.bookinfo.publish}</td>
                </tr>
                <tr>
                    <th>ISBN</th>
                    <td>${map.bookinfo.isbn}</td>
                </tr>
                <tr>
                    <th>简介</th>
                    <td>${map.bookinfo.introduction}</td>
                </tr>
                <tr>
                    <th>语言</th>
                    <td>${map.bookinfo.language}</td>
                </tr>
                <tr>
                    <th>价格</th>
                    <td>${map.bookinfo.price}</td>
                </tr>
                <tr>
                    <th>出版日期</th>
                    <td><fmt:formatDate value="${map.bookinfo.pubdate}"/></td>
                </tr>
                <tr>
                    <th>分类名</th>
                    <td>${map.classinfo.classname}</td>
                </tr>
                <tr>
                    <th>数量</th>
                    <td>${map.bookinfo.number}</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>

</div>

</body>
</html>
