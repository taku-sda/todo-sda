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

	<title>ToDo!!｜ToDoの追加</title>
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

	 <h1 class="display-4">ToDoの追加</h1>

	 <%--
	 エラーメッセージをToDo追加画面に表示する場合の処理
	 <c:if test="${not empty addItemErrMsg}">
	  <span class="bg-warning"><c:out value="${addItemErrMsg}"></c:out></span>
	 </c:if>
	  --%>

	 <div class="bg-light p-2">
	 <form action="/AddItem" method="post">
	 <div class="form-group w-50">
	  <label for="title">タイトル</label><br>
	  <input type="text"  class="form-control" name="title" id="title" maxlength="20"  required>
	 </div>
	 <div class="form-group">
	 <fieldset>
	 <legend>期限</legend>
	 <label>
	  <select name="year">
	   <c:forEach var="i" begin="2020" end="2099" step="1">
	    <option value = <c:out value="${i}"></c:out> <c:if test="${i == now.getYear()}">selected</c:if>><c:out value="${i}"></c:out></option>
	   </c:forEach>
	  </select>年
	 </label>
	 <label>
	  <select name="month">
	   <c:forEach var="i" begin="1" end="12" step="1">
	    <option value = <c:out value="${i}"></c:out> <c:if test="${i == now.getMonthValue()}">selected</c:if>><c:out value="${i}"></c:out></option>
	   </c:forEach>
	  </select>月
	 </label>
	 <label>
	  <select name="day">
	   <c:forEach var="i" begin="1" end="31" step="1">
	    <option value = <c:out value="${i}"></c:out> <c:if test="${i == now.getDayOfMonth()}">selected</c:if>><c:out value="${i}"></c:out></option>
	   </c:forEach>
	  </select>日
	 </label>
	 <label>
	  <select name="hour">
	   <c:forEach var="i" begin="0" end="23" step="1">
	    <option value = <c:out value="${i}"></c:out>><c:out value="${i}"></c:out></option>
	   </c:forEach>
	  </select>時
	 </label>
	 <label>
	  <select name="minute">
	   <c:forEach var="i" begin="0" end="59" step="1">
	    <option value = <c:out value="${i}"></c:out>><c:out value="${i}"></c:out></option>
	   </c:forEach>
	  </select>分
	 </label>
	 </fieldset>
	 </div>

	 <div>重要度</div>
	 <div class="form-check form-check-inline">
      <input class="form-check-input" type="radio" name="importance" id="importance3" value="3">
      <label class="form-check-label" for="importance3">
       大
      </label>
     </div>
     <div class="form-check form-check-inline">
      <input class="form-check-input" type="radio" name="importance" id="importance2" value="2" checked>
      <label class="form-check-label" for="importance2">
       中
      </label>
     </div>
     <div class="form-check form-check-inline">
      <input class="form-check-input" type="radio" name="importance" id="importance1" value="1">
      <label class="form-check-label" for="exampleRadios3">
       小
      </label>
     </div>

	 <div class="form-group mt-4">
	  <label for="memo">メモ</label><br>
	  <textarea name="memo" class="form-control w-100" id="memo" cols="40" rows="4" maxlength="400"></textarea>
	 </div>
	  <button type="submit" class="btn btn-primary mb-4">ToDoを追加</button>
	 </form>
	 </div>

	</div>


	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>