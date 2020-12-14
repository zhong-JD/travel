<%--
  Created by IntelliJ IDEA.
  User: 28693
  Date: 2020/10/14
  Time: 14:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">5
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>添加用户</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="${pageContext.servletContext.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="${pageContext.servletContext.contextPath}/js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="${pageContext.servletContext.contextPath}/js/bootstrap.min.js"></script>

    <script>
        window.onload=function () {
           /* //给返回按钮绑定事件
            document.getElementById("backBtn").onclick=function () {
                //发送请求
                location.href="${pageContext.servletContext.contextPath}/login.jsp";
            }*/
            $("#backBtn").on("click",function () {
                location.href = "${pageContext.servletContext.contextPath}/login.jsp";
            })
        }
    </script>
</head>
<body>
<div class="container">
    <center><h3>注册用户页面</h3></center>
    <form action="${pageContext.servletContext.contextPath}/rigister" method="post">
        <div class="form-group">
            <label for="name">姓名：</label>
            <input type="text" class="form-control" id="name" name="name" placeholder="请输入姓名">
        </div>


        <div class="form-group">
            <label for="name">密码：</label>
            <input type="text" class="form-control" id="password" name="password" placeholder="请输入密码">
        </div>



        <div class="form-group" style="text-align: center">
            <input class="btn btn-primary" type="submit" value="提交" />
            <input class="btn btn-default" type="reset" value="重置" />
            <input class="btn btn-default" id="backBtn" type="button" value="返回" />
        </div>
    </form>
</div>
</body>
</html>
