<%--
  Created by IntelliJ IDEA.
  User: 刘贤熔
  Date: 2018/9/25
  Time: 10:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<%
    String errorMsg = (String) request.getSession().getAttribute("msg");
    String userIsDeleMsg = (String) request.getSession().getAttribute("userIsDeleMsg");
%>
<jsp:include page="../../frame/Header.jsp"></jsp:include>

<body>

<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="<%=basePath %>index">基于Struts2+EJB+OpenJPA技术构建的博客</a>
        </div>

        <div class="collapse navbar-collapse navbar-ex1-collapse">
            <ul class="nav navbar-nav">
                <li><a href="<%=basePath %>index.html">网站首页</a></li>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li><a href="Login.jsp" target="_blank">登录</a></li>
                <li><a href="Register.jsp" target="_blank">注册</a></li>
            </ul>
        </div>
    </div>
</nav>


    <% if (null != errorMsg) { %> <%-- 登录验证失败提示信息 --%>
<div class="container">
    <div class="alert alert-error">
        <%=errorMsg %>
    </div>
</div>
    <%    request.getSession().removeAttribute("msg");
    } %>


    <% if (null != userIsDeleMsg) { %> <%-- 用户被禁用提示信息 --%>
<div class="container">
    <div class="alert alert-error">
        <%=userIsDeleMsg %>
    </div>
</div>
    <%    request.getSession().removeAttribute("userIsDeleMsg");
    } %>

<div class="container">
    <div class="row col-md-6">
        <form name="login_form" role="form" action="<%=basePath %>login" method="POST"
              onsubmit="return isValidate(login_form)">
            <fieldset>
                <div id="legend">
                    <legend class="caption">登录</legend>
                </div>
                <div class="form-group">
                    <label for="username">用户名</label>
                    <input type="text" class="form-control " name="username" id="username" placeholder="Enter email">
                </div>
                <div class="form-group">
                    <label for="password">密码</label>
                    <input type="password" class="form-control" name="password" id="password" placeholder="Password">
                </div>

                <div class="form-group">
                    <button type="submit" class="btn btn-success">登录</button>
                </div>
            </fieldset>
        </form>
    </div>
</div>

<jsp:include page="../../frame/Footer.jsp" flush="true"/>

<script type="text/javascript">
    function isValidate(login_form) {
        var username = login_form.username.value;
        var password = login_form.password.value;

        if (username == "" || password == "") {
            alert("请填写用户名和密码！");

            return false;
        }
        return true;
    }
</script>
