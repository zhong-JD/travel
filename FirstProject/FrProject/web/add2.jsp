<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/10/12
  Time: 9:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>添加用户</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery-1.8.3.js"></script>
</head>
<body>
<div class="container">
    <center><h3>添加联系人页面</h3></center>
    <form>
        <div class="form-group">
            <label for="name">姓名：</label>
            <input type="text" class="form-control" id="name" name="name" placeholder="请输入姓名">
        </div>

        <div class="form-group">
            <label>性别：</label>
            <input type="radio" name="gender" value="男"/>男
            <input type="radio" name="gender" value="女"/>女
        </div>

        <div class="form-group">
            <label for="nianling">年龄：</label>
            <input type="text" class="form-control" id="nianling" name="age" placeholder="请输入年龄">
        </div>

        <div class="form-group">
            <label for="jiguan">籍贯：</label>
            <select name="address" class="form-control" id="jiguan">
                <option value="">--请选择--</option>
            </select>
        </div>

        <div class="form-group">
            <label for="brthday">生日：</label>
            <input type="date" class="form-control" name="birthday" placeholder="请输入QQ号码" id="brthday"/>
        </div>


        <%--<div class="form-group">
            <label for="password">Password：</label>
            <input type="text" class="form-control" name="password" placeholder="请输入密码" id="password"/>
        </div>--%>
        <div class="form-group" style="text-align: center">
            <input id="submitbtn" class="btn btn-primary"  value="提交" />
            <input class="btn btn-default" type="reset" value="重置" />
            <input class="btn btn-default" type="button" value="返回" id="back"/>
        </div>
    </form>
</div>
</body>
<script>

    $(function () {
        $.get(
            "${pageContext.servletContext.contextPath}/getAddress",
            function (data) {
                $(data).each(function (index,ele) {
                        var option = "<option value='"+ele+"'>"+ele+"</option>";
                        $("#jiguan").append(option);
                    }
                );
            },
            "json"
        );
    });
    $(function () {
        $("#submitbtn").on("click",function () {
            //将要添加的数据封装成一个json对象   var n = {}  用花括号封装起来 里面是 键值对。
            var user = {"name":$("#name").val(),"gender":$("input[type='radio']:checked").val(),"age":parseInt($("#nianling").val()),"address":$("#jiguan").val(),"birthday":$("#brthday").val()};

            $.post(
                "${pageContext.servletContext.contextPath}/add",
                {"user":JSON.stringify(user)},
                function () {
                    window.location.href="${pageContext.servletContext.contextPath}/pageServlet?currentPage=${pageBean.totalPage}"
                }
            );



        });
    })
   /* window.onload =function (){
        var back = document.getElementById("back");
        back.onclick =
            function (){
             window.history.back();
            }


    }*/
    $(function () {
        $("#back").on("click",function () {
            window.history.back();
        })
    })
</script>
</html>
