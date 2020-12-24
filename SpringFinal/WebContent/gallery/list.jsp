<%@page import="com.study.springfinal.common.Pager"%>
<%@page import="com.study.springfinal.domain.Gallery"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	List<Gallery> galleryList = (List)(request.getAttribute("galleryList"));
	Pager pager = new Pager();
	pager.init(request, galleryList);
%>
<%
	out.print(galleryList.size()+"<br>");
	out.print(pager.getNum()+"<br>");
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
table {
	border-collapse: collapse;
	border-spacing: 0;
	width: 100%;
	border: 1px solid #ddd;
}

th, td {
	text-align: left;
	padding: 16px;
}

tr:nth-child(even) {
	background-color: #f2f2f2;
}
a{
	text-decoration: none;
}
.pageNum{
	font-size: 20px;
	color: red;
}
</style>
</head>
<body>

	<table>
		<tr>
			<th>No</th>
			<th>Img</th>
			<th>title</th>
			<th>writer</th>
			<th>regdate</th>
		</tr>
		
			<%int curPos = pager.getCurPos(); %>
			<%int num = pager.getNum(); %>
			
			<%for(int i=0; i<pager.getPageSize();i++){ %>
				<%if(num < 1) break; %>
				<%Gallery gallery= (Gallery)galleryList.get(curPos++);%>
				<tr>
						<th><%=num-- %></th>
						<th><img src="/data/<%=gallery.getFilename()%>"/></th>
						<th>
							<a href = "/gallery/detail?gallery_id=<%=gallery.getGallery_id()%>"><%=gallery.getTitle() %></a>
						</th>
						<th><%=gallery.getWriter() %></th>
						<th><%=gallery.getRegdate() %></th>
				</tr>
			<%} %>
		
		<tr>
			<td colspan="5" style="text-align:center">
					<%if(pager.getFirstPage() >1){ %>
						<a href = "/board/list?currentPage=<%=pager.getFirstPage()-1%>">◀</a>					
					<%}else{ %>
						<a href = "javascript:alert('처음 페이지 입니다')">◀</a>
					<%} %>
			
				<%for(int i = pager.getFirstPage(); i<=pager.getLastPage(); i++){ %>
					<%if(pager.getTotalPage()<i) break; %>
					<a href = "/board/list?currentPage=<%=i%>" <%if(pager.getCurrentPage()==i) {%>class = "pageNum"<%} %>><%=i %></a>
				<%} %>
					<%if(pager.getLastPage() < pager.getTotalPage()) {%>
						<a href = "/board/list?currentPage=<%=pager.getLastPage()+1%>">▶</a>
					<%}else{ %>
						<a href = "javascript:alert('마지막 페이지입니다.')">▶</a>
					<%} %>
					
			</td>
		</tr>
		
		<tr>
			<td colspan="5">
				<button onClick="location.href='/board/regist_form.jsp'"> 글등록</button>
			</td>
		</tr>
	</table>

</body>
</html>