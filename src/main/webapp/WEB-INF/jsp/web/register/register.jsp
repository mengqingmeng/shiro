<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<c:set var="ctx" value="${pageContext.request.contextPath}"/> 
	<link href="${ctx}/static/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
	<link href="${ctx}/static/bootstrap/dist/css/normalize.css" rel="stylesheet">
	<link href="${ctx}/static/bootstrap/dist/css/signin.css" rel="stylesheet">
	<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
    <div class="container">
      <form class="form-signin" action="registerPost" method="post">
        <h2 class="form-signin-heading">请注册</h2>
        <label for="inputEmail" class="sr-only">用户名</label>
        <input type="text" id="inputEmail" name="username" class="form-control" placeholder="用户名" required autofocus>
        <label for="inputPassword" class="sr-only">密码</label>
        <input type="password" id="inputPassword" name="password" class="form-control" placeholder="密 码" required>
        <div class="checkbox">
          <label>
            <input type="checkbox" value="remember-me"> 记住我
          </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">注册</button>
      </form>

    </div> <!-- /container -->
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${ctx}/static/bootstrap/dist/js/bootstrap.min.js"></script>
</body>
</html>