<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
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
    <title>用户信息管理系统</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="${pageContext.servletContext.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="${pageContext.servletContext.contextPath}/js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="${pageContext.servletContext.contextPath}/js/bootstrap.min.js"></script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>
</head>
<body>
<div class="container">
	<h3 style="text-align: center">用户信息列表</h3>
    <div style="float: left;">

        <form class="form-inline" action="${pageContext.servletContext.contextPath}/pageServlet?">
            <div class="form-group">
                <label for="exampleInputName2">姓名</label>
                <input type="text" class="form-control" id="exampleInputName2" name="selectname" value="${selectHelp.selectname}" >
            </div>
            <div class="form-group">
                <label for="exampleInputName3">籍贯</label>
                <select name="selectaddress" id="exampleInputName3"  class="form-control" >
                    <option value="">--请选择--</option>
                    <c:forEach items="${addressSet}" varStatus="s" var="ads">
                        <option value="${ads}">${ads}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <label for="exampleInputEmail2">性别</label>
                <select name="selectgender" id="exampleInputEmail2"  class="form-control" >
                    <option value="">--请选择--</option>
                    <option value="男">男</option>
                    <option value="女">女</option>
                </select>
            </div>
            <button type="submit" class="btn btn-info">查询</button>
            <a href="${pageContext.request.contextPath}/pageServlet?currentPage=1&rows=6&selectname=${param.selectname}&selectaddress=${param.selectaddress}&selectgender=${param.selectgender}"
            class="btn btn-info">查询首页</a>
        </form>

    </div>

    <div style="float: right;margin: 5px;">

        <c:if test="${sessionScope.loginUser.shenfenma == 1}">
          <span disabled="disabled"> <a class="btn btn-primary active" href="javascript:restore()">恢复删除</a>
            <a class="btn btn-primary active" href="${pageContext.servletContext.contextPath}/add2.jsp">添加联系人</a>
            <a class="btn btn-primary active" href="javascript:checkEles()">删除选中</a></span>
        </c:if>
        <c:if test="${sessionScope.loginUser.shenfenma == 0}">
          <span disabled="disabled"> <a class="btn btn-lg active" href="#">恢复删除</a>
            <a class="btn btn-lg active" href="#">添加联系人</a>
            <a class="btn btn-lg active" href="#">删除选中</a>
        </c:if>
<a href="javascript:logout()" class="btn btn-danger">注销</a>
    </div>

    <table border="1" class="table table-bordered table-hover">
        <tr class="warning">
<%--            onclick="seAll(this)"--%>
            <th><input type="checkbox" id="seAll"/></th>
            <th>编号</th>
            <th>姓名</th>
            <th>性别</th>
            <th>年龄</th>
            <th>籍贯</th>
            <th>生日</th>
            <th>操作</th>
        </tr>

        <c:forEach items="${pageBean.list}" var="user" varStatus="s">
            <tr class="table-hover">
                <td><input type="checkbox" name="checkEles" value="${user.id}" class="itemSelect"/></td>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.gender}<span class="glyphicon glyphicon-user"></span></td>
                <td>${user.age}</td>
                <td>${user.address}</td>
                <td>${user.birthday}</td>
                <td>
                    <c:if test="${sessionScope.loginUser.shenfenma == 1}">

                        <a class="btn btn-success btn-sm" href="${pageContext.servletContext.contextPath}/changeU?id=${user.id}&currentPage=${pageBean.currentPage}&rows=6&selectname=${selectHelp.selectname}&selectaddress=${param.selectaddress}&selectgender=${param.selectgender}">修改</a>
                        &nbsp;<button class="btn btn-success btn-sm" onclick="confirmD('${user.id}')" id="deleteU">删除</button>
                    </c:if>
                    <c:if test="${sessionScope.loginUser.shenfenma == 0}">

                            <span class="glyphicon glyphicon-exclamation-sign   alert-danger" aria-hidden="true"></span>
                            <span class="sr-only">Error:</span>

                        <a class="btn btn-default btn-sm" href="#">修改</a>
                        &nbsp;<button class="btn btn-default btn-sm"  id="deleteU">删除</button>
                    </c:if>

                </td>
            </tr>
        </c:forEach>


    </table>
    <div style="margin-left: 30%">

        <nav>
            <ul class="pagination">
                <%-- 判断是否是第一页--%>
                <c:if test="${pageBean.currentPage==1}">
                <li class="disabled">
                    </c:if>
                    <c:if test="${pageBean.currentPage!=1}">
                    <li>
                    </c:if>

                    <a href="${pageContext.request.contextPath}/pageServlet?currentPage=${pageBean.currentPage-1}&rows=6&selectname=${param.selectname}&selectaddress=${param.selectaddress}&selectgender=${param.selectgender}"
                       aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                <c:forEach var="i" varStatus="s" step="1" begin="1" end="${pageBean.totalPage}">
                    <c:if test="${pageBean.currentPage == i}">
                        <li class="active">

                            <a href="${pageContext.request.contextPath}/pageServlet?currentPage=${i}&rows=6&selectname=${param.selectname}&selectaddress=${param.selectaddress}&selectgender=${param.selectname}"

                               name="li">${i}</a></li>
                    </c:if>
                    <c:if test="${pageBean.currentPage != i}">
                        <li>
                            <a href="${pageContext.request.contextPath}/pageServlet?currentPage=${i}&rows=6&selectname=${param.selectname}&selectaddress=${param.selectaddress}&selectgender=${param.selectgender}"
                               name="li">${i}</a></li>
                    </c:if>
                </c:forEach>
                    <%-- 判断是否是最后页--%>
                    <c:if test="${pageBean.currentPage >= pageBean.totalPage}">
                    <li class="disabled">
                        </c:if>
                        <c:if test="${pageBean.currentPage!=pageBean.totalPage}">
                    <li>
                        </c:if>
                        <a href="${pageContext.request.contextPath}/pageServlet?currentPage=${pageBean.currentPage+1}&rows=6&selectname=${param.selectname}&selectaddress=${param.selectaddress}&selectgender=${param.selectgender}"
                           aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                <span style="font-size: 25px ;margin-left: 5px"><a href="${pageContext.servletContext.contextPath}/pageServlet" class="btn btn-success">首页</a>

                    共${pageBean.totalCount}条数据，共${pageBean.totalPage}页</span>
            </ul>
        </nav>
    </div>
</div>


</div>



</body>
<script>
    window.onload = function () {


   /* var selectAddress = document.getElementById("exampleInputName3");
    var options = selectAddress.getElementsByTagName("option");
    var address = "${selectHelp.selectaddress}".toString();
    for (var i = 0 ; i < options.length ; i++){
        if (address == options[i].value){
            options[i].selected ='selected';
            break;
        }
    }*/
 $("#exampleInputName3 option").each(function () {
       if ($(this).val() == "${selectHelp.selectaddress}"){
           $(this).prop("selected",true);
       return false;
       }
   });


  $("#exampleInputEmail2 option").each(function () {
      if ($(this).val() == "${selectHelp.selectgender}"){
          $(this).prop("selected",true);
          return false;
      }
  })



      /*  var selectSex = document.getElementById("exampleInputEmail2");
        var optionSex = selectSex.getElementsByTagName("option");
        var sex = "${selectHelp.selectgender}".toString();
        for (var i = 0 ; i < optionSex.length ; i++){
            console.log(sex + "=-=" + i +"=-="+ optionSex[i].value)
            if (sex == optionSex[i].value){
                optionSex[i].selected ='selected';
                return ;
            }
        }*/

    }



    function logout() {
        var b = window.confirm("你确定要注销吗?");
        if (b)window.location.href ="${pageContext.servletContext.contextPath}/logout";
        else return ;
    }
function confirmD(id) {
    var b = window.confirm("你确定要删除这条数据吗?");
    console.log(b);
    if (b) window.location.href="${pageContext.servletContext.contextPath}/deleteU?id="+id +"&currentPage=${pageBean.currentPage}&rows=6&name=${param.name}&address=${param.address}&gender=${param.gender}";
    else return ;
    }

function restore() {
    window.location.href="${pageContext.servletContext.contextPath}/restore.jsp";
   /* $.ajax(
        {
            type : 'POST',
            url: "${pageContext.servletContext.contextPath}/deletearr?DeleteArr=0",
            contentType : "application/x-www-form-urlencoded",
            success:function (){
                window.alert("恢复成功拉！");
                window.location.reload();
            },
        }
    );*/

}

    function checkEles() {
        var delArrs = new Array();
        var j = 0;
       /* for (var i = 0 ; i < checkEles.length ; i ++){
            if (checkEles[i].checked){
                delArrs[j++] = checkEles[i].value;
            }
        }*/
     $(".itemSelect").each(function () {
         if ($(this).prop("checked"))
             delArrs[j++] = $(this).val();

     });




        if (delArrs.length ==0){
            window.alert("删除失败");
            return ;
        }
        if (!window.confirm("你确定要删除这条数据吗?")){
            return ;
        }
   $.ajax(
            {
                type : 'POST',
                url: "${pageContext.servletContext.contextPath}/deletearr?DeleteArr=1",
                contentType : "application/x-www-form-urlencoded",
                data: {"array" : delArrs},
                success:function (){
                    window.location.reload();
                },
            }
        );
    }

</script>
<script>
 /* function seAll(obj) {
      $(".itemSelect").prop("checked",obj.checked);
  }*/
 $(function () {
     $("#seAll").click(function () {
         $(".itemSelect").prop("checked",$("#seAll").prop("checked"));
     });
 })
</script>
</html>
