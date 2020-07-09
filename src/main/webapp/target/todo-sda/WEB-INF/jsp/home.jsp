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

	<title>ToDo!!｜ホーム</title>
</head>
<body>
	<%--ログイン後ナビバー --%>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	 <a class="navbar-brand" href="/LoggedIn/Home">ToDo!!</a>
	 <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#Navbar" aria-controls="Navbar" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
     </button>
     <div class="collapse navbar-collapse" id="Navbar">
      <ul class="navbar-nav mr-auto">
       <li class="nav-item">
        <a class="nav-link text-light" href="/LoggedIn/HowToUse" >使い方</a>
       </li>
       <li class="nav-item">
        <a class="nav-link text-light" href="/LoggedIn/AddItem" >ToDoの追加</a>
       </li>
      </ul>
      <ul class="navbar-nav">
       <li class="nav-item">
         <a class="nav-link text-light" href="/LoggedIn/Logout">ログアウト</a>
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
	 <h4 class="my-3">ようこそ<c:out value="${userId}"/>さん！ <%= Helper.getToday() %></h4>

	 <c:if test="${empty completedList && empty expiredList && empty todayList && empty otherList}">
	 <h5>登録されているToDoがありません。<a href="/LoggedIn/AddItem">ToDoの追加</a>をしてみましょう。</h5>
	 </c:if>
	</div>


	<div class="container mt-5">
	 <h3 class="bg-warning w-75">今日までのToDo</h3>
	 <div class="janbotron bg-light">
	  <c:choose>
	   <c:when test="${empty todayList}">
	    <h3 class="text-center">今日までのToDoはありません</h3>
	   </c:when>
	   <c:otherwise>
	    <table class="table">
	     <thead>
	      <tr>
	       <th>タイトル</th>
	       <th>期限</th>
	       <th>重要度</th>
	      </tr>
		 </thead>
	     <tbody>
	      <c:forEach var="i" begin="0" end="${todayList.size() -1}" step="1">
	       <tr>
	        <td>
	         <form method="post" name="todayForm" action="/LoggedIn/DetailItem">
			 <input type="hidden" name="itemId" value="${todayList[i].getItemId()}">
			 <c:choose>
			  <c:when test="${todayList.size() == 1}">
			   <a href="javascript:todayForm.submit()"><strong>${todayList[i].getTitle()}</strong></a>
			  </c:when>
			  <c:otherwise>
	           <a href="javascript:todayForm[${i}].submit()"><strong>${todayList[i].getTitle()}</strong></a>
			  </c:otherwise>
			 </c:choose>
			 </form>
	        </td>
	        <td>${todayList[i].getHour()}時${todayList[i].getMinute()}分まで</td>
	        <td>
	         <c:choose>
	          <c:when test="${todayList[i].getImportance() == 3 }">大</c:when>
	          <c:when test="${todayList[i].getImportance() == 2 }">中</c:when>
	          <c:when test="${todayList[i].getImportance() == 1 }">小</c:when>
	         </c:choose>
	        </td>
	       </tr>
	      </c:forEach>
	     </tbody>
	    </table>
	   </c:otherwise>
	  </c:choose>
	 </div>
	</div>

	<div class="container mt-5">
	 <h3 class="bg-primary w-75">ToDo一覧</h3>
	 <div class="janbotron bg-light">
	  <c:choose>
	   <c:when test="${empty otherList}">
	    <h3 class="text-center">ToDoはありません</h3>
	   </c:when>
	   <c:otherwise>
	    <table class="table">
	     <thead>
	      <tr>
	       <th>タイトル</th>
	       <th>期限</th>
	       <th>重要度</th>
	      </tr>
		 </thead>
	     <tbody>
	      <c:forEach var="i" begin="0" end="${otherList.size() -1}" step="1">
	       <tr>
	        <td>
	         <form method="post" name="otherForm" action="/LoggedIn/DetailItem">
			 <input type="hidden" name="itemId" value="${otherList[i].getItemId()}">
			 <c:choose>
			  <c:when test="${otherList.size() == 1}">
			   <a href="javascript:otherForm.submit()"><strong>${otherList[i].getTitle()}</strong></a>
			  </c:when>
			  <c:otherwise>
	           <a href="javascript:otherForm[${i}].submit()"><strong>${otherList[i].getTitle()}</strong></a>
			  </c:otherwise>
			 </c:choose>
			 </form>
	        </td>
	        <td>${otherList[i].getYear()}年${otherList[i].getMonth()}月${otherList[i].getDay()}日まで</td>
	        <td>
	         <c:choose>
	          <c:when test="${otherList[i].getImportance() == 3 }">大</c:when>
	          <c:when test="${otherList[i].getImportance() == 2 }">中</c:when>
	          <c:when test="${otherList[i].getImportance() == 1 }">小</c:when>
	         </c:choose>
	        </td>
	       </tr>
	      </c:forEach>
	     </tbody>
	    </table>
	   </c:otherwise>
	  </c:choose>
	 </div>
	</div>

	<div class="container mt-5">
	 <div class="row">
	  <div class="col-sm-6">
	   <h3 class="bg-success w-75">完了</h3>
	   <div class="janbotron bg-light">
	   <c:choose>
	    <c:when test="${empty completedList}">
	     <h3 class="text-center">ToDoはありません</h3>
	    </c:when>
	    <c:otherwise>
	     <ul class="py-3">
	      <c:forEach var="i" begin="0" end="${completedList.size() -1}" step="1">
	       <form method="post" name="completedForm" action="/LoggedIn/DetailItem">
	       <input type="hidden" name="itemId" value="${completedList[i].getItemId()}">
	       <c:choose>
	        <c:when test="${completedList.size() == 1}">
	         <li class="mb-2"><a href="javascript:completedForm.submit()">${completedList[i].getTitle()}</a></li>
	        </c:when>
	        <c:otherwise>
	         <li class="mb-2"><a href="javascript:completedForm[${i}].submit()">${completedList[i].getTitle()}</a></li>
	        </c:otherwise>
	       </c:choose>
	       </form>
	     </c:forEach>
	     </ul>
	     <a href="/LoggedIn/DeleteAllCompletedItem" class="btn btn-secondary mx-2 mb-2">一括削除</a>
	    </c:otherwise>
	   </c:choose>
	   </div>
	  </div>
	  <div class="col-sm-1">
	  	<%--隙間を開けるための空のcol --%>
	  </div>
	  <div class="col-sm-5">
	   <h3 class="bg-danger w-75">期限切れ</h3>
	   <div class="janbotron bg-light">
	   <c:choose>
	    <c:when test="${empty expiredList}">
	     <h3 class="text-center">ToDoはありません</h3>
	    </c:when>
	    <c:otherwise>
	     <ul class="py-3">
	      <c:forEach var="i" begin="0" end="${expiredList.size() -1}" step="1">
	       <form method="post" name="expiredForm" action="/LoggedIn/DetailItem">
	       <input type="hidden" name="itemId" value="${expiredList[i].getItemId()}">
	       <c:choose>
	         <c:when test="${expiredList.size() == 1}">
	          <li class="mb-2"><a href="javascript:expiredForm.submit()">${expiredList[i].getTitle()}</a></li>
	         </c:when>
	         <c:otherwise>
	           <li class="mb-2"><a href="javascript:expiredForm[${i}].submit()">${expiredList[i].getTitle()}</a></li>
	         </c:otherwise>
	       </c:choose>
	       </form>
	      </c:forEach>
	     </ul>
	     <a href="/LoggedIn/DeleteAllExpiredItem" class="btn btn-secondary mx-2 mb-2">一括削除</a>
	    </c:otherwise>
	   </c:choose>
	   </div>
	  </div>
	 </div>
	</div>

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>