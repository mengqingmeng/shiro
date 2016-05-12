<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="/image/thumbnail" id="upload-form" method="post" enctype="multipart/form-data">
	<h2>请选择上传图片</h2>
	<div>
		<input type="file" name="image" id="image"/>
		<input type="submit" value="上传"/> 
	</div>
</form>
</body>
</html>