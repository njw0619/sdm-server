<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>회비 지출내역 확인</title>
		
		<!-- Bootstrap Core CSS -->
	    <link href="/static/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	    <!-- MetisMenu CSS -->
	    <link href="/static/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">
	    <!-- Custom CSS -->
	    <link href="/static/dist/css/sb-admin-2.css" rel="stylesheet">
	    <!-- Custom Fonts -->
	    <link href="/static/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	    
	    <!-- jQuery -->
	    <script src="/static/vendor/jquery/jquery.min.js"></script>
	    <!-- Bootstrap Core JavaScript -->
	    <script src="/static/vendor/bootstrap/js/bootstrap.min.js"></script>
	    <!-- Metis Menu Plugin JavaScript -->
	    <script src="/static/vendor/metisMenu/metisMenu.min.js"></script>
	    <!-- Custom Theme JavaScript -->
	    <script src="/static/dist/js/sb-admin-2.js"></script>
    
	</head>
<body>
	<div id="wrapper">
	
		<%@ include file="/WEB-INF/jsp/navigator.jsp" %>
		     
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h2 class="page-header">수입/지출 내역</h2>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <ul class="nav nav-tabs">
                                <c:forEach items="${months}" var="month">
                                	<li <c:if test = "${month.selected == 'Y'}"> class="active"</c:if> ><a href="${month.yyyyMM}">${month.MM}</a></li>
                                </c:forEach>
                            </ul>   
                            <div class="tab-content" style="padding-top:20px">                               
                                <div><h4 class = "text-center">
                                	당월 총 수입 : <fmt:formatNumber value = "${income}" pattern="#,###" />원, 
                                	총 지출 : <fmt:formatNumber value = "${expense}" pattern="#,###" />원</h4></div>
                                <c:forEach items="${transactions}" var="transaction">
                                	<div 
	                                	<c:choose> 
	                                		<c:when test="${transaction.status == 'O'}">class="panel panel-red"</c:when>
	   										<c:otherwise>class="panel panel-green"</c:otherwise> 
	   									</c:choose> >
				                        <div class="panel-heading 
				                        	<c:choose> 
				                            	<c:when test="${transaction.status == 'O'}">text-left</c:when>
   												<c:otherwise>text-right</c:otherwise> 
   											</c:choose>" > 
				                            <c:choose> 
				                            	<c:when test="${transaction.status == 'O'}">출금</c:when>
   												<c:otherwise>입금</c:otherwise> 
   											</c:choose>
				                        </div>
				                        <div class="panel-body text-right">
				                        	<h4>${transaction.occurDate}</h4>
				                            <h3><small style="padding-right:20px">${transaction.title}</small>
				                            <fmt:formatNumber value = "${transaction.amount}" pattern="#,###" />원</h3> 
				                        </div>
				                    </div>
                                </c:forEach>
                            </div>                     
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- row -->
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->
</body>
</html>