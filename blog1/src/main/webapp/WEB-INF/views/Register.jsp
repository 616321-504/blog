<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/9/26
  Time: 14:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
    String succMsg = (String)request.getSession().getAttribute("succMsg");
    String errorMsg = (String)request.getSession().getAttribute("errorMsg");
    String existMsg = (String)request.getSession().getAttribute("existMsg");
%>
1
<jsp:include page="../../frame/Header.jsp"></jsp:include>

<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="index.html">JavaEE 博客</a>
        </div>
        <div class="collapse navbar-collapse navbar-ex1-collapse">
            <ul class="nav navbar-nav">
                <a class="navbar-brand" href="<%=basePath %>index">基于Struts2+EJB+OpenJPA技术构建的博客</a>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li><a href="login" target="_blank">登录</a></li>
                <li><a href="register" target="_blank">注册</a></li>
            </ul>
        </div>
    </div>
</nav>

    <% if (null != succMsg) { %>
<div class="container">
    <div class="alert alert-success">
        <%=succMsg %>，点击<a href="<%=basePath %>Login.jsp">这里</a>进行登录。
    </div>
</div>
    <%    request.getSession().removeAttribute("succMsg");
       } %>

    <% if (null != errorMsg) { %>
<div class="container">
    <div class="alert alert-error">
        <%=errorMsg %>
    </div>
</div>
    <%    request.getSession().removeAttribute("errorMsg");
        } %>

    <% if (null != existMsg) { %>
<div class="container">
    <div class="alert alert-error">
        <%=existMsg %>
    </div>
</div>
    <%    request.getSession().removeAttribute("existMsg");
        } %>

<div class="container ">
    <div class="row col-md-6">
        <form class="form-horizontal" name="register_form"
              action="<%=basePath%>user/register" method="POST"
              onsubmit="return isValidate(register_form)">
            <fieldset>
                <div id="legend form-group">
                    <legend class="caption">注册</legend>
                </div>
                <div class="form-group">
                    <!-- Username -->
                    <label for="username">用户名</label>
                    <input type="text" id="username" name="username" placeholder="请输入用户名" class="form-control">
                </div>
                <div class="form-group">
                    <!-- E-mail -->
                    <label  for="email">E-mail</label>
                    <input type="text" id="email" name="email" placeholder="请输入邮箱地址" class="form-control">
                </div>
                <div class="form-group">
                    <!-- Password-->
                    <label class="control-label" for="password">密码</label>
                    <input type="password" id="password" name="password" placeholder="请输入密码" class="form-control">
                </div>
                <div class="form-group">
                    <!-- Password -->
                    <label class="control-label" for="password_confirm">密码（确认）</label>
                    <input type="password" id="password_confirm" name="password_confirm" placeholder="请再次输入密码" class="form-control">
                </div>

                <div class="form-group">
                    <!-- Button -->
                    <button class="btn btn-success">注册</button>
                </div>
            </fieldset>
        </form>
    </div>
</div>

<jsp:include page="../../frame/Footer.jsp"></jsp:include>

<script type="text/javascript">
    function isValidate(form) {
        var username = form.username.value;
        var email = form.email.value;
        var password = form.password.value;
        var password_confirm = form.password_confirm.value;

        if (username == "" || email == "" || password == "" || password_confirm == "") {
            alert("用户名、邮箱、密码、确认密码为必填项！");
            return false;

        } else if (password != password_confirm){
            alert("密码和密码（确认）必须相同！");
            return false;

        } else {
            return true;
        }

    }
</script>
