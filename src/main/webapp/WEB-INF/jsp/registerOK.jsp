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

<title>ToDo!!｜ユーザー登録完了</title>
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
		<h1 class="mb-5">以下の内容でユーザー登録しました</h1>
		<div class="mb-3">
			ユーザーID：
			<c:out value="${userId}" />
		</div>
		<div class="mb-5">
			パスワード：
			<c:out value="${pass}" />
		</div>
		<a class="btn btn-primary mb-4" href="/Home">ToDo!!ホーム画面へ</a>
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