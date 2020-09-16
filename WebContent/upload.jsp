<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="POST" action="/upload" enctype="multipart/form-data">
		파일1 : <input type="file" name="org_file_path1" id="org_file_path1"><br>
		파일2 : <input type="file" name="org_file_path2" id="org_file_path2"><br>
		올린사람 : <input type="text" name="up_name" id="up_name"><br>
		<button type="button" onclick="upload()">업로드</button>
		<progress max="0" id="prog" value="0"></progress>
		<div id="preDiv">0%</div>
	</form>
	<script>
		function upload() {
			var xhr = new XMLHttpRequest();
			xhr.open('POST', '/upload');
			xhr.onreadystatechange = function() {
				if (xhr.readyState == 4) {
					if (xhr.status == 200) {

					}
				}
			}
			var formData = new FormData();
			var f1 = document.querySelector('#org_file_path1');
			var file1 = f1.files[0];
			var progObj = document.querySelector('#prog');
			progObj.max = file1.size;
			//var f2 = document.querySelector('#org_file_path2');
			formData.append('org_file_path1', file1);
			//formData.append('org_file_path2', f2.files[0]);
			formData.append('up_name', document.querySelector('#up_name').value);
			xhr.upload.onprogress = function(f){
				progObj.value = f.loaded;
				document.querySelector('#preDiv').innerHTML = Math.floor((f.loaded/progObj.max)*100) + '%';
			}
			xhr.send(formData);
		}
	</script>
</body>
</html>