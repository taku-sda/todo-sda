<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="WEB-INF/jsp/include/common.jsp"%>
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

<title>ToDo!!</title>
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

	<%--サイトタイトル --%>
	<div class="jambotron bg-light">
		<div class="pl-5 py-2">
			<h1 class="display-3">ToDo!!</h1>
			<h4>-やること管理サイト-</h4>
		</div>
	</div>

	<%--ページメイン --%>
	<div class="mx-5 mt-3">
		<%--サイト解説 --%>
		<h1>ToDo!!とは</h1>
		<p class="mt-3">
			やること（ToDo）の管理を行うことができる<strong>ToDoアプリケーションサイト</strong>です。<br> <br>
			初めての方はユーザー登録<br> 登録済みの方はログインからすぐに利用することができます
		</p>
		<%--ユーザー登録、ログインボタン --%>
		<div class="my-5">
			<a href="/Register" class="btn btn-primary">ユーザー登録</a> <a
				href="/Login" class="btn btn-primary ml-4">ログイン</a>
		</div>

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