<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	page import="kr.co.happy.*"%>
<%@ page import="java.util.ArrayList" %>
<link rel="stylesheet" type="text/css" href="css/boardDetail.css">
<script>
	
	function regOrDel(str) {
		var a = ${param.bid };
		var b = ${param.btype };
		if(str=="reg") {
			frm1.action = "boardReg?bid=" + a + '&btype=' + b;
		} else {
			frm1.action = 'boardDel?bid=' + a + '&btype=' + b;
		}
	}
	
</script>

<div class="container">
	<div class="title"> 
		${data.getBtitle() }
	</div>
	<div class="article">
		<pre>${data.getBcontent() }</pre>
	</div>
	<form name="frm1" method="post">
		비밀번호 : <input type="password" name="pw" >
		<input type="submit" value="수정" onclick='regOrDel("reg")'>
		<input type="submit" value="삭제" onclick='regOrDel("del")'>
		<input type="button" value="목록으로" onclick='location.href="boardList?btype=${param.btype }"'>
	</form>
	<form class="commentFrm" action="commentReg?bid=${param.bid }&btype=${param.btype }" method="post">
		<textarea name="comment" class="commentWrite" placeholder="댓글을 입력하세요"></textarea><br>
		<input type="submit" value="등록">
	</form>
<%	
	ArrayList<CommentDTO> list = (ArrayList<CommentDTO>)request.getAttribute("comments");
	if(list!=null) {
		for(CommentDTO i : list) {
%>	
	<div class="commentView">
		<div class="commentTitle">
			<%=i.getRegdate() %>
		</div>		
		<div class="commentContent">
			<pre><%=i.getContent() %></pre>
		</div>
	</div>
<% 	
		}	
	}
%>	
</div>