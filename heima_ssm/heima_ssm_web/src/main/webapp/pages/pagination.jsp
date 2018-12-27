<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 分页 -->
<!-- .box-footer-->
<div class="box-footer">
	<div class="pull-left">
		<div class="form-group form-inline">
			总共${pageInfo.pages} 页，共${pageInfo.total} 条数据。 每页
			<select class="form-control" id="changePageSize" onchange="changePageSize()">
				<option <c:if test="${pageInfo.pageSize == 1}"> selected</c:if>>1</option>
				<option <c:if test="${pageInfo.pageSize == 2}"> selected</c:if>>2</option>
				<option <c:if test="${pageInfo.pageSize == 3}"> selected</c:if>>3</option>
				<option <c:if test="${pageInfo.pageSize == 4}"> selected</c:if>>4</option>
				<option <c:if test="${pageInfo.pageSize == 5}"> selected</c:if>>5</option>
			</select> 条
		</div>
	</div>

	<div class="box-tools pull-right">
		<ul class="pagination">
			<li>
				<a href="./findAll.do?page=1&size=${pageInfo.pageSize}" aria-label="Previous">首页</a>
			</li>
			<li>
				<c:if test="${pageInfo.hasPreviousPage}">
					<a href="./findAll.do?page=${pageInfo.prePage}&size=${pageInfo.pageSize}">上一页</a>
				</c:if>
				<c:if test="${!pageInfo.hasPreviousPage}">
					<a>上一页</a>
				</c:if>
			</li>
			<%--页码--%>
			<c:forEach var="i" begin="${pageInfo.navigateFirstPage}" end="${pageInfo.navigateLastPage}" >
				<c:if test="${pageInfo.pageNum == i}">
					<li class="active"><a href="./findAll.do?page=${i}&size=${pageInfo.pageSize}">${i}</a></li>
				</c:if>
				<c:if test="${pageInfo.pageNum != i}">
					<li><a href="./findAll.do?page=${i}&size=${pageInfo.pageSize}">${i}</a></li>
				</c:if>
			</c:forEach>

			<li>
				<c:if test="${pageInfo.hasNextPage}">
				<a href="./findAll.do?page=${pageInfo.nextPage}&size=${pageInfo.pageSize}">上一页</a>
				</c:if>
				<c:if test="${!pageInfo.hasNextPage}">
				<a>下一页</a>
				</c:if>
				<%--<a href="#">下一页</a></li>--%>
			<li>
				<a href="./findAll.do?page=${pageInfo.pages}&size=${pageInfo.pageSize}" aria-label="Next">尾页</a>
			</li>
		</ul>
	</div>

</div>
<script>
    function changePageSize() {
        //获取下拉框的值
        var pageSize = $("#changePageSize").val();

        //向服务器发送请求，改变没页显示条数
        location.href = "./findAll.do?page=1&size="
            + pageSize;
    }
</script>
<!-- /.box-footer-->
<!-- 分页 /-->