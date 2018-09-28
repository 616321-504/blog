<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2018/9/27
  Time: 9:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" import="cap.model.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
    Admin admin = (Admin)request.getSession().getAttribute("admin");
%>

<jsp:include page="frame/Header.jsp"></jsp:include>

<% if (null != admin) { %>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">

    <div class="navbar-header">

        <a class="navbar-brand" href="<%=basePath%>admin/index.jsp">JavaEE博客管理系统</a>
    </div>

    <div class="collapse navbar-collapse navbar-ex1-collapse">
        <ul class="nav navbar-nav side-nav">
            <li><a href="<%=basePath%>admin/index.jsp"><i class="glyphicon glyphicon-dashboard"></i> 控制面板</a></li>
            <li class="active"><a href="<%=basePath %>admin/useradmin"><i class="glyphicon glyphicon-cog"></i> 用户管理</a></li>
            <li><a href="<%=basePath %>admin/sysArticalAdmin"><i class="glyphicon glyphicon-cog"></i> 文章管理</a></li>
            <li><a href="<%=basePath %>admin/sysCategoryAdmin"><i class="glyphicon glyphicon-cog"></i> 分类管理</a></li>

        </ul>

        <ul class="nav navbar-nav navbar-right navbar-user">
            <li class="dropdown user-dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="glyphicon glyphicon-user"></i> <%=admin.getUsername() %> <b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li><a href="#"><i class="icon-gear"></i> 设置</a></li>
                    <li class="divider"></li>
                    <li><a href="<%=basePath%>admin/logout"><i class="glyphicon glyphicon-off"></i> 登出</a></li>
                </ul>
            </li>
        </ul>
    </div>
</nav>


<div id="page-wrapper">
    <div class="row">
        <div class="col-lg-12">
            <h1>J2EE博客系统 <small>基于Struts2+EJB+OpenJPA技术构建</small></h1>
            <ol class="breadcrumb">
                <li><a href="<%=basePath%>admin/index.jsp"><i class="icon-dashboard"></i> 控制面板</a></li>
                <li><a href="<%=basePath%>admin/useradmin"><i class="icon-bar-chart"></i> 用户管理</a></li>
                <li class="active"> 用户详细信息</li>
            </ol>
        </div>
    </div>

    <div class="row">
        <div class="col-lg-12">
            <div class="table-responsive">
                <table class="table table-hover tablesorter">
                    <thead>
                    <tr>
                        <th>姓</th>
                        <th>名</th>
                        <th>性别</th>
                        <th>联系电话</th>
                    </tr>

                    </thead>
                    <tbody>

                    <tr>
                        <th>
                            <s:property value="pf.firstName"/>

                        </th>
                        <th>
                            <s:property value="pf.lastName"/>

                        </th>
                        <th>
                            <s:if test="pf.gender==true">
                                男
                            </s:if>
                            <s:else>
                                女
                            </s:else>
                        </th>
                        <th>
                            <s:property value="pf.telephone"/>
                        </th>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<% } %>
<jsp:include page="frame/Footer.jsp"></jsp:include>

