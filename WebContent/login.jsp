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
    <label for="uiId">아이디</label>
    <input type="text" class="form-control" id="uiId" aria-describedby="uiId">
    <small id="uiId" class="form-text text-muted">We'll never share your id with anyone else.</small>
  </div>
  <div class="form-group">
    <label for="uiPassword">비밀번호</label>
    <input type="password" class="form-control" id="uiPassword">
  </div>
  <div class="form-group form-check">
    <input type="checkbox" class="form-check-input" id="saveId">
    <label class="form-check-label" for="saveId">아이디 유지</label>
  </div>
  <button type="submit" class="btn btn-primary" onclick="doLogin()">로그인</button>
  <a href="/join.jsp"><button type="submit" class="btn btn-primary">회원가입</button></a>
</body>
<script>
	function doLogin(){
		var params = {
				uiId : $('#uiId').val(),
				uiPassword : $('#uiPassword').val(),
				cmd : 'login'
		}
		$.ajax({
			url : '/ajax/user',
			method : 'POST',
			data : JSON.stringify(params),
			contentType : 'application/json;charset=utf8',
			success : function(res){
				if(res.result) {
					alert('로그인성공');
					location.href = '/';
				}
				else {
					alert('로그인실패');
				}
			}
		})
	}
</script>
</html>