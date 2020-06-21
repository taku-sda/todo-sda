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

<title>ToDo!!｜ホーム</title>
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

	<%--ユーザー情報、日時表示 --%>
	<div class="container mt-3">
		<h6>
			ようこそ
			<c:out value="${userId}" />
			さん！<%=JSPHelper.getLogout()%></h6>
		<h4><%=JSPHelper.getNow()%></h4>
	</div>

	<%--ページメイン --%>
	<%--今日までのToDoの表示 --%>
	<div class="container mt-5">
		<%--ToDoが存在する場合のみ表示する --%>
		<c:if test="${not empty todayList}">
			<h3 class="bg-warning w-50"><div class="p-1">今日まで!!</div></h3>
			<div class="janbotron bg-light">
				<%--一覧をテーブルで表示 --%>
				<table class="table">
					<thead>
						<tr>
							<th>タイトル</th>
							<th>期限</th>
							<th>重要度</th>
							<th></th>	<%--完了ボタン用の見出し --%>
						</tr>
					</thead>
					<tbody>
						<%--ToDoの数だけ繰り返す --%>
						<c:forEach var="i" begin="0" end="${todayList.size() -1}" step="1">
							<tr>
								<%--タイトル --%>
								<td><a
									href="/DetailItem?itemId=${todayList[i].getItemId()}">${todayList[i].getTitle()}</a>
								</td>
								<%--期限 --%>
								<td>${todayList[i].getHour()}時${todayList[i].getMinute()}分</td>
								<%--重要度 --%>
								<td><c:choose>
										<c:when test="${todayList[i].getImportance() == 3 }">大</c:when>
										<c:when test="${todayList[i].getImportance() == 2 }">中</c:when>
										<c:when test="${todayList[i].getImportance() == 1 }">小</c:when>
									</c:choose></td>
								<%--完了ボタン --%>
								<td><a
									href="/CompleteItem?itemId=${todayList[i].getItemId()}">済</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</c:if>

	</div>

	<%--ToDo一覧の表示 --%>
	<div class="container mt-5">
		<div class="row">
			<div class="col-6">
				<h3 class="bg-primary"><div class="p-1">ToDo一覧</div></h3>
			</div>
			<%--ToDoの追加ボタン --%>
			<div class="col-6">
				<a href="/AddItem" class="btn btn-secondary float-right">ToDoの追加</a>
			</div>
		</div>

		<div class="janbotron bg-light">
			<c:choose>
				<%--ToDoが存在しない場合は、案内文を表示 --%>
				<c:when test="${empty otherList}">
					<h3 class="text-center p-2">
						登録されているToDoがありません<br> ToDoの追加をしてみましょう
					</h3>
				</c:when>
				<%--ToDoが存在する場合は、一覧をテーブルで表示 --%>
				<c:otherwise>
					<table class="table">
						<thead>
							<tr>
								<th>タイトル</th>
								<th>期限</th>
								<th>重要度</th>
								<th></th>	<%--完了ボタン用の見出し --%>
							</tr>
						</thead>
						<tbody>
							<%--ToDoの数だけ繰り返す --%>
							<c:forEach var="i" begin="0" end="${otherList.size() -1}"
								step="1">
								<tr>
									<%--タイトル --%>
									<td><a
										href="/DetailItem?itemId=${otherList[i].getItemId()}">${otherList[i].getTitle()}</a>
									</td>
									<%--期限 --%>
									<td>${otherList[i].getYear()}/${otherList[i].getMonth()}/${otherList[i].getDay()}</td>
									<%--重要度 --%>
									<td><c:choose>
											<c:when test="${otherList[i].getImportance() == 3 }">大</c:when>
											<c:when test="${otherList[i].getImportance() == 2 }">中</c:when>
											<c:when test="${otherList[i].getImportance() == 1 }">小</c:when>
										</c:choose></td>
									<%--完了ボタン --%>
									<td><a
										href="/CompleteItem?itemId=${otherList[i].getItemId()}">済</a></td>
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
			<%--完了のToDoの表示 --%>
			<div class="col-sm-6">
				<%--ToDoが存在する場合のみ表示 --%>
				<c:if test="${not empty completedList}">
					<div class="row">
						<div class="col-6">
							<h3 class="bg-success"><div class="p-1">完了</div></h3>
						</div>
						<%--一括削除ボタン --%>
						<div class="col-6">
							<a href="/DeleteAllCompletedItem"
								class="btn btn-secondary float-right">一括削除</a>
						</div>
					</div>

					<div class="janbotron bg-light">
						<%--一覧をテーブルで表示 --%>
						<table class="table">
							<tbody>
								<%--ToDoの数だけ繰り返す --%>
								<c:forEach var="i" begin="0" end="${completedList.size() -1}"
									step="1">
									<tr>
										<%--タイトル --%>
										<td><a
											href="/DetailItem?itemId=${completedList[i].getItemId()}">${completedList[i].getTitle()}</a></td>
										<%--削除ボタン --%>
										<td><a
											href="DeleteItem?itemId=${completedList[i].getItemId()}">削除</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</c:if>
			</div>

			<%--隙間を開けるための空のcol --%>
			<div class="col-sm-1"></div>

			<div class="col-sm-5">
				<%--ToDoが存在する場合のみ表示 --%>
				<c:if test="${not empty expiredList}">
					<div class="row">
						<div class="col-6">
							<h3 class="bg-danger"><div class="p-1">期限切れ</div></h3>
						</div>
						<%--一括削除ボタン --%>
						<div class="col-6">
							<a href="/DeleteAllExpiredItem"
								class="btn btn-secondary float-right">一括削除</a>
						</div>
					</div>

					<div class="janbotron bg-light">
						<%--一覧をテーブルで表示 --%>
						<table class="table">
							<tbody>
								<%--ToDoの数だけ繰り返す --%>
								<c:forEach var="i" begin="0" end="${expiredList.size() -1}"
									step="1">
									<tr>
										<%--タイトル --%>
										<td><a
											href="/DetailItem?itemId=${expiredList[i].getItemId()}">${expiredList[i].getTitle()}</a></td>
										<%--削除ボタン --%>
										<td><a
											href="DeleteItem?itemId=${expiredList[i].getItemId()}">削除</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</c:if>
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