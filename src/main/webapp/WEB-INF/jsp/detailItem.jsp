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

<title>ToDo!!｜ToDoの詳細</title>
</head>
<body>
	<%--ログイン後ナビバー --%>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" href="/Home">ToDo!!</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#Navbar" aria-controls="Navbar" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="Navbar">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item"><a class="nav-link text-light"
					href="/HowToUse">使い方</a></li>
				<li class="nav-item"><a class="nav-link text-light"
					href="/AddItem">ToDoの追加</a></li>
			</ul>
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link text-light"
					href="/Logout">ログアウト</a></li>
			</ul>
		</div>
	</nav>

	<%--ホーム画面へのリンク --%>
	<div class="mt-2 mr-2">
		<div class="float-right"><%=JSPHelper.getHome()%></div>
	</div>

	<%--ユーザー情報、日時表示 --%>
	<div class="container my-3">
		<h6>
			ようこそ
			<c:out value="${userId}" />
			さん！<%=JSPHelper.getLogout()%></h6>
		<h3><%=JSPHelper.getNow()%></h3>
	</div>

	<%--ページメイン --%>
	<div class="container">
		<h1 class="display-4">ToDoの詳細</h1>

		<div class="bg-light p-2">
			<%--ToDoの詳細フォーム --%>
			<form action="/UpdateItem" method="post">

				<%--タイトル --%>
				<div class="from-group w-50">
					<label for="title">タイトル</label><br> <input type="text"
						class="form-control" name="title" id="title" maxlength="20"
						value="${detailItem.getTitle()}" required>
				</div>

				<%--期限 --%>
				<div class="from-group">
					<fieldset>
						<legend>期限</legend>
						<label> <select name="year">
								<c:forEach var="i" begin="2020" end="2099" step="1">
									<option value=<c:out value="${i}"> </c:out>
										<c:if test="${i == detailItem.getYear()}">selected</c:if>><c:out
											value="${i}"></c:out></option>
								</c:forEach>
						</select>年
						</label> <label> <select name="month">
								<c:forEach var="i" begin="1" end="12" step="1">
									<option value=<c:out value="${i}"></c:out>
										<c:if test="${i == detailItem.getMonth()}">selected</c:if>><c:out
											value="${i}"></c:out></option>
								</c:forEach>
						</select>月
						</label> <label> <select name="day">
								<c:forEach var="i" begin="1" end="31" step="1">
									<option value=<c:out value="${i}"></c:out>
										<c:if test="${i == detailItem.getDay()}">selected</c:if>><c:out
											value="${i}"></c:out></option>
								</c:forEach>
						</select>日
						</label> <label> <select name="hour">
								<c:forEach var="i" begin="0" end="23" step="1">
									<option value=<c:out value="${i}"></c:out>
										<c:if test="${i == detailItem.getHour()}">selected</c:if>><c:out
											value="${i}"></c:out></option>
								</c:forEach>
						</select>時
						</label> <label> <select name="minute">
								<c:forEach var="i" begin="0" end="59" step="1">
									<option value=<c:out value="${i}"></c:out>
										<c:if test="${i == detailItem.getMinute()}">selected</c:if>><c:out
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
						id="importance3" value="3"
						<c:if test="${detailItem.getImportance() == 3}">checked</c:if>>
					<label class="form-check-label" for="importance3"> 大 </label>
				</div>
				<div class="form-check form-check-inline">
					<input class="form-check-input" type="radio" name="importance"
						id="importance2" value="2"
						<c:if test="${detailItem.getImportance() == 2}">checked</c:if>>
					<label class="form-check-label" for="importance2"> 中 </label>
				</div>
				<div class="form-check form-check-inline">
					<input class="form-check-input" type="radio" name="importance"
						id="importance1" value="1"
						<c:if test="${detailItem.getImportance() == 1}">checked</c:if>>
					<label class="form-check-label" for="exampleRadios3"> 小 </label>
				</div>

				<%--完了 --%>
				<div class="form-check mt-4">
					<input class="form-check-input" type="checkbox" name="completed"
						value="true" id="completed"
						<c:if test="${detailItem.isCompleted() == true}">checked</c:if>>
					<label class="form-check-label" for="completed"> 完了 </label>
				</div>

				<%--メモ --%>
				<div class="from-group mt-4">
					<label for="memo">メモ</label>
					<textarea name="memo" class="form-control w-100" id="memo"
						cols="40" rows="4" maxlength="400"><c:out
							value="${detailItem.getMemo()}" /></textarea>
					<br> <br>
				</div>

				<%--更新対象を指定するためのitemId --%>
				<input type="hidden" name="itemId" value="${detailItem.getItemId()}">

				<%--送信ボタン --%>
				<button type="submit" class="btn btn-primary">ToDoを更新</button>

				<%--削除ボタン --%>
				<a href="DeleteItem?itemId=${detailItem.getItemId()}"
					class="btn btn-secondary ml-3">ToDoを削除</a>
			</form>


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