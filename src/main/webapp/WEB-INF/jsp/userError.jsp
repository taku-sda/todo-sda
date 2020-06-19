<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="include/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">

<title>ToDo!!｜入力内容エラー</title>
</head>
<body>
	<%--ログイン前ナビバー --%>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" href="/">ToDo!!</a>
		<button type="button" class="navbar-toggler" data-toggle="collapse"
			data-target="#Navbar" aria-controls="Navbar" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse justify-content-end" id="Navbar">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link text-light"
					href="/Register">ユーザー登録</a></li>
				<li class="nav-item"><a class="nav-link text-light"
					href="/Login">ログイン</a></li>
			</ul>
		</div>
	</nav>

	<%--トップ画面へのリンク --%>
	<div class="mt-2 mb-5 mr-2">
		<div class="float-right"><%=JSPHelper.getTop()%></div>
	</div>

	<%--ページメイン --%>
	<div class="container">
		<%--エラー内容表示 --%>
		<h1 class="mb-4">入力内容にエラーがあります</h1>
		<c:if test="${not empty errMsg}">
			<pre><span class="bg-warning"><c:out value="${errMsg }" /></span></pre>
		</c:if>
		<%--遷移前のページに戻るボタン --%>
		<c:choose>
			<c:when test="${errType == 'register'}">
				<a href="/Register" class="btn btn-primary mt-5">ユーザー登録画面に戻る</a>
			</c:when>
		</c:choose>
		<c:choose>
			<c:when test="${errType == 'login'}">
				<a href="/Login" class="btn btn-primary mt-5">ログイン画面に戻る</a>
			</c:when>
		</c:choose>
	</div>

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>
</body>
</html>