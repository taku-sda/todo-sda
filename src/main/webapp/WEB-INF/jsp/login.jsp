<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="include/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- viewport meta -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<title>ToDo!!｜ログイン</title>
</head>
<body>
	<header>
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
	</header>

	<div class="container">
		<h1 class="mb-3">ログイン</h1>
		<%--ログインフォーム --%>
		<form action="/Login" method="post">
			<div class="form-group">
				<label for="userIdForm">ユーザーID(英数字20文字以内)</label>
				<input type="text" class="form-control w-50" id="userIdForm" name="userId"
					value="${userId}" required>
			</div>
			<div class="form-group">
				<label for="passwordFrom">パスワード(英数字4～8文字)</label>
				<input type="password" class=" form-control w-50" id="passwordForm"
					name="pass" value="${pass}" required>
			</div>
			<button type="submit" class="btn btn-primary mb-4">ログイン</button>
		</form>
	</div>

	<!-- jQuery、Popper.js、Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
		integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
		integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
		crossorigin="anonymous"></script>
</body>
</html>