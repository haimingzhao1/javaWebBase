<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-default" role="navigation" style="background-color:#fff">
    <div class="container-fluid">
        <div class="navbar-header" style="margin-left: 8%;margin-right: 1%">
            <a class="navbar-brand " href="${pageContext.request.contextPath}/jsp/reader/reader_main.jsp"><p class="text-primary" style="font-family: 华文行楷; font-size: 200%; ">我的图书馆</p></a>
        </div>
        <div class="collapse navbar-collapse" id="example-navbar-collapse">
            <ul class="nav navbar-nav navbar-left">
                <li>
                    <a href="${pageContext.request.contextPath}/reader?methodName=allBooks&readerId=${readercard.readerid}">
                        图书查询
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/reader?methodName=readerInfo&readerId=${readercard.readerid}" >
                        个人信息
                    </a>
                </li>
                <li >
                    <a href="${pageContext.request.contextPath}/reader?methodName=myLend&readerId=${readercard.readerid}" >
                        我的借还
                    </a>
                </li>
                <li >
                    <a href="${pageContext.request.contextPath}/reader?methodName=toReaderRepass&readerId=${readercard.readerid}" >
                        密码修改
                    </a>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#">${readercard.username}, 已登录</a></li>
                <li><a href="${pageContext.request.contextPath}/logout">退出</a></li>
            </ul>
        </div>
    </div>
</nav>
