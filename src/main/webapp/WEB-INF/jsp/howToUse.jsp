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

<title>ToDo!!｜使い方</title>
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
	<div class="container mt-5 mb-3">
		<h6>
			ようこそ
			<c:out value="${userId}" />
			さん！<%=JSPHelper.getLogout()%></h6>
		<h4><%=JSPHelper.getNow()%></h4>
	</div>

	<%--ページメイン --%>
	<div class="container">
		<h1 class="display-4 mb-4">使い方</h1>

		<%--サイトの説明 --%>
		<div class="jambotron bg-light p-2">
			<p>
				<strong>当サイトはToDoアプリケーションサイトです。</strong>
			</p>
			<ul>
				<li>まずは、やらなければならないと思っていることをToDoとして追加してみましょう。</li>
				<li>出揃ったら、期限や重要度から優先するものを決め、取り組んでみましょう。</li>
				<li>終わったら、ToDoを完了させて、次のToDoにどんどん取り組んでいきましょう！</li>
			</ul>
			<p>
				目の前のことを一つずつこなしていくことで、確実に目標へ近づいていけるはずです！<br> 焦らず着実に頑張りましょう！！
			</p>
		</div>

		<%--機能の説明 --%>
		<%--ホーム画面について --%>
		<a data-toggle="collapse" href="#home" role="button"
			aria-expanded="false" aria-controls="home">
			<h2 class="mt-4">ホーム画面</h2>
		</a>
		<div class="collapse" id="home">
			<div class="card card-body">
				<p>
					ホーム画面では登録されているToDoを確認することができます。<br> ToDoは</span><span
						class="bg-primary mx-2">ToDo一覧</span><span class="bg-warning mx-2">今日まで</span>
					<span class="bg-success">完了</span><span class="bg-danger mx-2">期限切れ</span>の4種類で表示されます。<br>
					<br> ToDo一覧以外は、存在する場合のみ表示されます。
				</p>
			</div>
		</div>

		<%--ToDoの追加について --%>
		<a data-toggle="collapse" href="#add" role="button"
			aria-expanded="false" aria-controls="add">
			<h2 class="mt-3">ToDoの追加</h2>
		</a>
		<div class="collapse" id="add">
			<div class="card card-body">
				<p>
					<span class="bg-primary mr-2">ToDo一覧</span>そばの<strong>ToDoの追加</strong>ボタンを選び、ToDo追加画面を開きます。<br>
					タイトル、期限、重要度、メモ（任意）を入力して<strong>ToDoを追加</strong>ボタンを選ぶとToDoが追加されます。<br><br>
					また、画面上部のメニューバーの<a href="#">ToDoの追加</a>からも行うことができます。
				</p>
			</div>
		</div>

		<%--ToDoの詳細・修正について --%>
		<a data-toggle="collapse" href="#detail" role="button"
			aria-expanded="false" aria-controls="detail">
			<h2 class="mt-3">ToDoの詳細・修正</h2>
		</a>
		<div class="collapse" id="detail">
			<div class="card card-body">
				<p>
					ホーム画面で<a href="#" rel="nofollow">ToDoのタイトル</a>を選ぶことでToDoの詳細を確認できます。<br><br>
					詳細画面では内容の修正、ToDoの削除などを行うことができます。
				</p>
			</div>
		</div>

		<%--ToDoの完了について --%>
		<a data-toggle="collapse" href="#complete" role="button"
			aria-expanded="false" aria-controls="complete">
			<h2 class="mt-3">ToDoの完了</h2>
		</a>
		<div class="collapse" id="complete">
			<div class="card card-body">
				<p>
					<span class="bg-primary mr-2">ToDo一覧</span>の<a href="#">済</a>を選ぶことで、そのToDoを完了させることができます。<br><br>
					完了したToDoは<span class="bg-success mx-2">完了</span>に移動します。
				</p>
			</div>
		</div>

		<%--ToDoの期限切れについて --%>
		<a data-toggle="collapse" href="#expired" role="button"
			aria-expanded="false" aria-controls="expired">
			<h2 class="mt-3">ToDoの期限切れ</h2>
		</a>
		<div class="collapse" id="expired">
			<div class="card card-body">
				<p>
					設定した期限を過ぎたToDoは<span class="bg-danger mx-2">期限切れ</span>に移動します。<br><br>
					ToDoの詳細画面から期限を再設定することができます。
				</p>
			</div>
		</div>

		<%--ToDoの削除について --%>
		<a data-toggle="collapse" href="#delete" role="button"
			aria-expanded="false" aria-controls="delete">
			<h2 class="mt-3">ToDoの削除</h2>
		</a>
		<div class="collapse" id="delete">
			<div class="card card-body">
				<p>
					ホーム画面で<a href="#" rel="nofollow">ToDoのタイトル</a>を選び、ToDoの詳細画面を開きます。<br>
					<strong>ToDoを削除</strong>ボタンを選ぶと削除することができます。<br>
					<br> また、完了、期限切れのToDoについてはホーム画面の<a href="#">削除</a>または<strong>一括削除</strong>ボタンでも削除することができます。
				</p>
			</div>
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