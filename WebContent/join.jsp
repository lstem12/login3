<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/common/head.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="/common/body.jsp"></jsp:include>
	<div class="form-group">
		<label for="uiId">아이디</label> <input type="text" class="form-control"
			id="uiId" aria-describedby="uiId">
	</div>
	<div class="form-group">
		<label for="uiPassword">비밀번호</label> <input type="password"
			class="form-control" id="uiPassword">
	</div>
	<div class="form-group">
		<label for="uiName">이름</label> <input type="text" class="form-control"
			id="uiName">
	</div>
	<div class="form-group">
		<label for="uiBirth">생년월일</label> <input type="date"
			class="form-control" id="uiBirth">
	</div>
	<div class="form-group">
		<label for="uiAge">나이</label> <input type="number"
			class="form-control" id="uiAge">
	</div>
	<div class="form-group">
		<label for="uiPhone">전화번호</label> <input type="text"
			class="form-control" id="uiPhone"> <small id="uiPhone"
			class="form-text text-muted">전화번호는 - 를 생략하고 작성해주세요.</small>
	</div>
	<div class="form-group">
		<label for="uiEmail">이메일</label> <input type="email"
			class="form-control" id="uiEmail">
	</div>
	<div class="form-group">
		<label for="uiNickname">닉네임</label> <input type="text"
			class="form-control" id="uiNickname">
	</div>

	<button type="submit" class="btn btn-primary" onclick="doJoin()">회원가입</button>
	<a href="/join.jsp"><button type="submit" class="btn btn-primary">취소하기</button></a>
</body>
<script>
	function doJoin() {
		if ($('#uiId').val().trim().length < 4) {
			alert('아이디는 4글자 이상으로 작성해주세요.');
			$('#uiId').focus();
			return;
		}
		var params = {
				uiId : $('#uiId').val(),
				uiPassword : $('#uiPassword').val(),
				uiName : $('#uiName').val(),
				uiBirth : $('#uiBirth').val(),
				uiAge : $('#uiAge').val(),
				uiPhone : $('#uiPhone').val(),
				uiEmail : $('#uiEmail').val(),
				uiNickname : $('#uiNickname').val(),
				cmd : 'join'
		}
		$.ajax({
			url : '/ajax/user',
			method : 'POST',
			data : JSON.stringify(params),
			contentType : 'application/json;charset=utf-8',
			success : function(res){
				if(res.result == 1){
					alert('가입이 성공했습니다.');
					location.href = '/login.jsp';
				}
				else{
					alert('가입이 실패했습니다.');
				}
			},error : function(res){
				alert('예기치 못한 오류');
			}
		})
	}
</script>
</html>