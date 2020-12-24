<%@page import="com.study.springfinal.domain.Gallery"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	//정말로 포워딩이 요청을 유지했는지 테스트해보자
	Gallery gallery = (Gallery)request.getAttribute("gallery");
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {font-family: Arial, Helvetica, sans-serif;}
* {box-sizing: border-box;}

input[type=text], select, textarea {
  width: 100%;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  margin-top: 6px;
  margin-bottom: 16px;
  resize: vertical;
}

input[type=button] {
  background-color: #4CAF50;
  color: white;
  padding: 12px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

input[type=button]:hover {
  background-color: #45a049;
}

.container {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
}

.reply-box{
	background: yellow;
}

.reply-list{
	overflow:hidden;
}

.reply-list p{
	float: left;
	
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.ckeditor.com/4.15.1/standard/ckeditor.js"></script>
<script>
$(function(){
	CKEDITOR.replace("content");
	getCommentList();
	
	$($("input[type='button']")[0]).click(function(){//수정버튼
		edit();//동기방식으로 요청
	});
	
	$($("input[type='button']")[1]).click(function(){//수정버튼
		del();//동기방식으로 요청
	});
	
});


function edit(){
	if(confirm("수정하시겠습니까?")){	
		$("form").attr({
			method : "post",
			action : "/gallery/edit"
		});
		$("form").submit();
	}
}

function del(){
	if(confirm("삭제하시겠습니까?")){	
		$("form").attr({
			method : "post",
			action : "/gallery/delete"
		});
		$("form").submit();
	}
}
//현재 페이지가 새로고침(reloading)되지 않게, 비동기방식으로 등록요청을 시도하자!!
//순수 js의 ajax를 사용하는 것이 아니라 jquery-ajax로 가보자!!

//댓글 목록 가져오기:비동기로 만들어야 한다.(재사용성을 위해...)
function getCommentList(){
	$.ajax({
		url:"/comment/list",
		type:"get",
		data:{
			gallery_id: <%=gallery.getGallery_id()%>			
		},
		success:function(result){
			$("#list-area").html("");//innerHTML과 동일
			
			var tag = "";
			for(var i = 0; i<result.list.length;i++){
				var comment = result.list[i];
				tag += "<div class=\"reply-list\">";
				tag += "<p style = \"width: 75%\">"+comment.msg+"</p>"; 
				tag += "<p style = \"width: 15%\">"+comment.author+"</p>"; 
				tag += "<p style = \"width: 10%\">"+comment.cdate+"</p>"; 
				tag += "</div>"; 				
				
			}
			
			$("#list-area").html(tag);//innerHTML과 동일
			
		}
		
	});
}

function registComment(){
	$.ajax({
		url:"/comment/regist",
		type:"get",
		data: {
			msg : $("input[name='msg']").val(),
			author: $("input[name='author']").val(),
			gallery_id: <%=gallery.getGallery_id()%>
		},
		//피드백은 success로 받는다, 즉 서버에서 에러없이 데이터가 결과값이 전송되면
		//success 우측에 명시된 익명함수가 동작하게 된다..
		success:function(result){
			if(result == 1){
				getCommentList();
			}else{
				alert('등록실패');
			}
		}
	})
}
</script>
</head>
<body>

<h3>Contact Form</h3>

<div class="container">
  <form>
    <input type="hidden" name="gallery_id" value="<%=gallery.getGallery_id()%>">
    <input type="hidden" name="filename" value="<%=gallery.getFilename()%>">
    <input type="text" name="title" value="<%=gallery.getTitle()%>">

    <input type="text" name="writer" value="<%=gallery.getWriter()%>">

    <textarea id="content" name="content" style="height:200px"><%=gallery.getContent()%></textarea>

    <input type="button" value="글 수정">
    <input type="button" value="글 삭제">
    <input type="button" value="목록보기" onClick="location.href='/board/list'">
    <div class="reply-box">
    	<input type = "text" name = "msg" placeholder="댓글 입력..." style="width:75%">
    	<input type = "text" name = "author" placeholder="작성자 입력..." style="width:10%">
    	<button type ="button" onClick = "registComment()">댓글 등록</button>
    </div>
    <div id= "list-area">
	        
    </div>
  </form>
</div>

</body>
</html>