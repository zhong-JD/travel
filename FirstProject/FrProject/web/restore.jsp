<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/10/14
  Time: 19:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>

</head>
<body>
<%--<div class="container-fluid">
    <div class="row"><h1>请选择要恢复的id</h1></div>
    <c:forEach items="${delIdsArr}" var="i" varStatus="s">
        <div class="row"><input type="checkbox" value="${i}"  name="restoreIds">${i}</div>
    </c:forEach>
    <div class="row"><a class="btn btn btn-primary" href="javascript:restore()">恢复删除</a></div>
</div>--%>
<div class="container" >
    <table border="1" class="table table-bordered table-hover" style="width: 350px;margin: auto">
        <tr class="bg-primary">
            <th><input type="checkbox" value="-1"/></th>
            <th>编号</th>
            <th>姓名</th>
            <th>籍贯</th>
        </tr>
        <c:forEach items="${getDeledUsers}" var="user" varStatus="s">
           <tr>
               <td><label for="idd"><input type="checkbox" value="${user.id}"  name="restoreIds" ></label></td>
               <td id="idd">${user.id}</td>
               <td>${user.name}</td>
               <td>${user.address}</td>
           </tr>
        </c:forEach>
        <tr>
            <td colspan="4" style="text-align: center"><a class="btn btn btn-warning" href="javascript:restore()" style="width: 80%">恢复删除</a></td>
        </tr>
    </table>

</div>

</body>
<script>

    function restore(){
        // var restoreIds = document.getElementsByName("restoreIds");
        var rds = new Array();
        var j = 0;
       /* for (var i = 0 ; i < restoreIds.length ; i ++){
            if (restoreIds[i].checked){
                rds[j++] = restoreIds[i].value;
            }
        }*/
        var restoreIds = $("input[type='checkbox']").length - 1 ;
        $("input[type='checkbox']").each(function () {
            if ($(this).prop("checked") && $(this).val() != -1 ){
                rds[j++] = $(this).val();
            }

        });

        if (j == restoreIds.length) {
            j = 0 ;
        }else if ( j > 0 && j < restoreIds){
            console.log("change");
            j = 2;
        }


      $.ajax(
                  {
                      type : 'POST',
                      url: "${pageContext.servletContext.contextPath}/deletearr?DeleteArr="+j,
                contentType : "application/x-www-form-urlencoded",
                data: {"array" : rds},
                success:function (){
                    window.location.href="${pageContext.servletContext.contextPath}/pageServlet";
                },
            }
        );


    }

</script>
</html>
