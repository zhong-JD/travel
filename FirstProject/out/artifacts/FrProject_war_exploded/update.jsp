<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/10/12
  Time: 13:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>修改用户</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery-2.1.0.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

</head>
<body>
<div class="container" style="width: 400px;">
    <h3 style="text-align: center;">修改联系人</h3>
    <form action="${pageContext.servletContext.contextPath}/updateU?currentPage=${pageBean.currentPage}&rows=6&selectname=${param.selectname}&selectaddress=${param.selectaddress}&selectgender=${param.selectgender}" method="post">
        <div class="form-group">
            <label for="name">姓名：</label>
            <input type="text" class="form-control" id="name" name="name"  placeholder="请输入姓名"
                   value="${changeUser.name}"
            />
        </div>

        <div class="form-group">
            <label>性别：</label>
            <c:if test="${changeUser.gender == '男'}">
                <input type="radio" name="gender" value="男"  checked="checked" />男
                <input type="radio" name="gender" value="女"  />女
            </c:if>
            <c:if test="${changeUser.gender != '男'}">
                <input type="radio" name="gender" value="男"   />男
                <input type="radio" name="gender" value="女"  checked="checked"/>女
            </c:if>
        </div>

        <div class="form-group">
            <label for="age">年龄：</label>
            <input type="text" class="form-control" id="age"  name="age" placeholder="请输入年龄"
            value="${changeUser.age}"/>
        </div>

        <div class="form-group">
            <label for="address">籍贯：</label>
            <select name="address" class="form-control" id="address" >
                <c:forEach items="${addressSet}" varStatus="s" var="ads">
                    <option value="${ads}">${ads}</option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <label for="birthday">生日：</label>
            <input type="date" class="form-control" name="birthday" placeholder="请输入QQ号码" id="birthday"
            value="${changeUser.birthday}"
            />
        </div>

     <%--   <div class="form-group">
            <label for="password">密码：</label>
            <input type="text" class="form-control" name="password" placeholder="请输入密码" id="password"
            value="${changeUser.password}"/>
        </div>--%>

        <div class="form-group" style="text-align: center">
            <input class="btn btn-primary" type="submit" value="提交" />
            <input class="btn btn-default" type="reset" value="重置" />
            <input class="btn btn-default" type="button" value="返回"  id="goback"/>
        </div>
    </form>
</div>
<script>
    window.onload =  function (){
        /*var back = document.getElementById("goback");
        back.onclick = function (){
            window.history.back();
        }*/
        $("#goback").click(function () {
            window.history.back();
        });
       /* var options = document.getElementsByTagName("option");
        for (var i = 0 ; i < options.length ; i++){
            if ("${changeUser.address}" == options[i].value){
                options[i].selected ='selected';
                return ;
            }
        }*/
        $("#address option").each(function () {
            if ($(this).val() == "${changeUser.address}")
                $(this).prop("selected",true);
        })


    }

    //获取url参数
   /* function getQueryVariable(variable)
    {
        var query = window.location.search.substring(1);
        var vars = query.split("&");
        for (var i=0;i<vars.length;i++) {
            var pair = vars[i].split("=");
            if(pair[0] == variable){return pair[1];}
        }
        return(false);
    }*/


</script>

</body>
</html>
