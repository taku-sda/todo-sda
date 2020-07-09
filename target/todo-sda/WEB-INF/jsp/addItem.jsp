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

<title>ToDo!!｜ToDoの追加</title>
</head>
<body>
	<%--ログイン後ナビバー --%>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" href="/LoggedIn/Home">ToDo!!</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#Navbar" aria-controls="Navbar" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="Navbar">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item"><a class="nav-link text-light"
					href="/LoggedIn/HowToUse">使い方</a></li>
				<li class="nav-item"><a class="nav-link text-light"
					href="/LoggedIn/AddItem">ToDoの追加</a></li>
			</ul>
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link text-light"
					href="/LoggedIn/Logout">ログアウト</a></li>
			</ul>
		</div>
	</nav>

	<%--ホーム画面へのリンク --%>
	<div class="mt-2 mr-2">
		<div class="float-right"><%=JSPHelper.getHome()%></div>
	</div>

	<%--ユーザー情報、日時表示 --%>
	<div class="container mt-5 mb-3">
		<h6>
			ようこそ
			<c:out value="${userId}" />
			さん！<%=JSPHelper.getLogout()%></h6>
		<h4><%=JSPHelper.getToday()%></h4>
	</div>

	<%--ページメイン --%>
	<div class="container">
		<h1 class="display-4">ToDoの追加</h1>

		<div class="bg-light p-2">
			<%--ToDoの追加フォーム --%>
			<form action="/LoggedIn/AddItem" method="post">

				<%--タイトル --%>
				<div class="form-group w-75">
					<label for="title">タイトル</label><br> <input type="text"
						class="form-control" name="title" id="title" maxlength="20"
						required>
				</div>

				<%--期限 --%>
				<div class="form-group">
					<fieldset>
						<legend>期限</legend>
						<label> <select name="year">
								<c:forEach var="i" begin="2020" end="2099" step="1">
									<option value=<c:out value="${i}"></c:out>
										<c:if test="${i == now.getYear()}">selected</c:if>><c:out
											value="${i}"></c:out></option>
								</c:forEach>
						</select>年
						</label> <label> <select name="month">
								<c:forEach var="i" begin="1" end="12" step="1">
									<option value=<c:out value="${i}"></c:out>
										<c:if test="${i == now.getMonthValue()}">selected</c:if>><c:out
											value="${i}"></c:out></option>
								</c:forEach>
						</select>月
						</label> <label> <select name="day">
								<c:forEach var="i" begin="1" end="31" step="1">
									<option value=<c:out value="${i}"></c:out>
										<c:if test="${i == now.getDayOfMonth()}">selected</c:if>><c:out
											value="${i}"></c:out></option>
								</c:forEach>
						</select>日
						</label> <label> <select name="hour">
								<c:forEach var="i" begin="0" end="23" step="1">
									<option value=<c:out value="${i}"></c:out>><c:out
											value="${i}"></c:out></option>
								</c:forEach>
						</select>時
						</label> <label> <select name="minute">
								<c:forEach var="i" begin="0" end="59" step="1">
									<option value=<c:out value="${i}"></c:out>><c:out
											value="${i}"></c:out></option>
								</c:forEach>
						</select>分
						</label>
					</fieldset>
				</div>

				<%--重要度 --%>
				<div>重要度</div>
				<div class="form-check form-check-inline">
					<input class="form-check-input" type="radio" name="importance"
						id="importance3" value="3"> <label
						class="form-check-label" for="importance3"> 大 </label>
				</div>
				<div class="form-check form-check-inline">
					<input class="form-check-input" type="radio" name="importance"
						id="importance2" value="2" checked> <label
						class="form-check-label" for="importance2"> 中 </label>
				</div>
				<div class="form-check form-check-inline">
					<input class="form-check-input" type="radio" name="importance"
						id="importance1" value="1"> <label
						class="form-check-label" for="exampleRadios3"> 小 </label>
				</div>

				<%--メモ --%>
				<div class="form-group mt-4">
					<label for="memo">メモ</label><br>
					<textarea name="memo" class="form-control w-100" id="memo"
						cols="40" rows="4" maxlength="400"></textarea>
				</div>

				<%--送信ボタン --%>
				<button type="submit" class="btn btn-primary mb-4">ToDoを追加</button>
			</form>
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