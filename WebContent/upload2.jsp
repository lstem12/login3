<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
</head>
<body>
<form id="fileForm" method="POST" action="/upload2" enctype="multipart/form-data">
파일1 : <input type="file" name="org_file_path1" id="org_file_path1"><br>
파일2 : <input type="file" name="org_file_path2" id="org_file_path2"><br>
올린사람 : <input type="text" name="up_name" id="up_name"><br>
<button type="button" onclick="upload()">파일올리기</button>
</form>
<script>
	function upload(){
		var fileForm = $('#fileForm')[0];
		var formData = new FormData(fileForm);
		$.ajax({		
			url : '/upload2',
			method : 'POST',
			data : formData,
			processData : false,
			contentType : false,
			success : function(res){
				if(res === 1){
					alert('파일업로드 성공');
				}else {
					alert('파일업로드 실패');
				}
			}
		})
	}
	
</script>
</body>
</html>