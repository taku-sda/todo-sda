<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="include/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

	<title>ToDo!!｜使い方</title>
</head>
<body>
	<%--ログイン後ナビバー --%>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	 <a class="navbar-brand" href="/Home">ToDo!!</a>
	 <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#Navbar" aria-controls="Navbar" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
     </button>
     <div class="collapse navbar-collapse" id="Navbar">
      <ul class="navbar-nav mr-auto">
       <li class="nav-item">
        <a class="nav-link text-light" href="/HowToUse" >使い方</a>
       </li>
       <li class="nav-item">
        <a class="nav-link text-light" href="/AddItem" >ToDoの追加</a>
       </li>
      </ul>
      <ul class="navbar-nav">
       <li class="nav-item">
         <a class="nav-link text-light" href="/Logout">ログアウト</a>
       </li>
      </ul>
     </div>
	</nav>

	<%--サイトタイトル --%>
	<div class="container">
	<div class="janbotron bg-info mt-1">
	  <h1 class="display-3 mb-4 pt-4 pb-4 text-center">ToDo!!<small>～やること管理サイト～</small></h1>
	</div>
	</div>

	<div class="container">

	<h4 class="my-3">ようこそ<c:out value="${userId}"/>さん！ <%= Helper.getNow() %></h4>

	<h1 class="display-3 mb-4">ToDo!!の使い方</h1>

	<h2 class="mt-5 mb-2"><span class="bg-light">ホーム画面</span></h2>
	<p>ホーム画面では登録されているToDoを確認することができます。<br>
	ToDoは<span class="bg-warning mx-2">今日まで</span><span class="bg-primary mx-2">ToDo一覧</span>
	<span class="bg-success">完了</span><span class="bg-danger mx-2">期限切れ</span>の4種類で表示されます。</p>

	<h2 class="mt-5 mb-2"><span class="bg-light">ToDoの追加</span></h2>
	<p>画面上部のメニューバーから<a href="/AddItem" >ToDoの追加</a>を選び、ToDoの追加画面を開きます。<br>
	タイトル、期限、重要度、メモ（任意）を入力して<button type="button" class="btn btn-primary mx-2">ToDoの追加</button>を選びます。</p>

	<h2 class="mt-5 mb-2"><span class="bg-light">ToDoの詳細</span></h2>
	<p>ホーム画面で<a href="#" rel="nofollow">ToDoのタイトル</a>を選ぶことでToDoの詳細を確認できます。<br>
	詳細画面では内容の修正、ToDoの削除などを行うことができます。</p>

	<h2 class="mt-5 mb-2"><span class="bg-light">ToDoの完了</span></h2>
	<p>ホーム画面で<a href="#" rel="nofollow">ToDoのタイトル</a>を選び、ToDoの詳細画面を開きます。<br>
	完了チェックボックスにチェックを入れて<button type="button" class="btn btn-primary mx-2">ToDoの更新</button>を選びます</p>

	<h2 class="mt-5 mb-2"><span class="bg-light">ToDoの削除</span></h2>
	<p>ホーム画面で<a href="#" rel="nofollow">ToDoのタイトル</a>を選び、ToDoの詳細画面を開きます。<br>
	<button type="button" class="btn btn-secondary mx-2">ToDoを削除</button>を選びます。<br>
	また、完了、期限切れのToDoについてはホーム画面の<button type="button" class="btn btn-secondary mx-2">一括削除</button>でも削除することができます。</p>

	</div>
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>