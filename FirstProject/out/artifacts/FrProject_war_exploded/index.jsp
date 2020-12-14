<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/10/12
  Time: 9:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>$Title$</title>
    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <script type="text/javascript">
    </script>
  </head>
  <body>

  <div class="container" style="width: 500px; margin-left: 30%">

    <div style="background-color:#E0FFFF;text-align: center">
     <h3 style="font-size:33px;font-family: Consolas"> 欢迎你！${sessionScope.loginUser.name},
      <c:if test="${sessionScope.loginUser.shenfenma == 1}">
      超级管理员
    </c:if>
      <c:if test="${sessionScope.loginUser.shenfenma == 0}">
      VIP会员
    </c:if>
     </h3>
    </div>
    <div style=" text-align: center">
      <a  class="btn btn btn-warning" href="${pageContext.servletContext.contextPath}/pageServlet" style="text-decoration: none;font-size:25px">查询所有用户信息</a>
      <a  class="btn btn btn-warning" href="javascript:logout()" style="text-decoration: none;font-size:25px;">注销</a>
    </div>
  </div>

  </body>
<script>
  function logout() {
    var b = window.confirm("您确定要注销吗?");
    if (b) window.location.href="${pageContext.servletContext.contextPath}/logout";
    else return ;
  }
</script>
</html>
