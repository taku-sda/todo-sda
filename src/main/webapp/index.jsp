<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="WEB-INF/jsp/include/common.jsp"%>
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
<title>ToDo!!</title>
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

		<%--サイトタイトル --%>
		<div class="jambotron bg-light">
			<div class="pl-5 py-2">
				<h1 class="display-3">ToDo!!</h1>
				<h4>-やること管理サイト-</h4>
			</div>
		</div>
	</header>

	<div class="mt-3 mx-5">
		<%--サイト解説 --%>
		<h1>ToDo!!とは</h1>
		<p class="mt-3">
			やること（ToDo）の管理を行うことができる<strong>ToDoアプリケーションサイト</strong>です。<br> <br>
			初めての方はユーザー登録<br> 登録済みの方はログインからすぐに利用することができます
		</p>

		<%--ユーザー登録、ログインボタン --%>
		<div class="my-5">
			<a href="/Register" class="btn btn-primary">ユーザー登録</a>
			<a href="/Login" class="btn btn-primary ml-4">ログイン</a>
		</div>
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