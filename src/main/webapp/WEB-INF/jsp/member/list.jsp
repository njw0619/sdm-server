<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>회원 목록</title>
		
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
                    <h2 class="page-header">회원 목록</h2>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="row">
                                <table class="table">
	                                <thead>
	                                	<th class="text-center">이름</th>
	                                	<th class="text-center">연락처</th>
	                                	<th class="text-center">포지션</th>
	                                	<th class="text-center">학생/직장인</th>
	                                	<th class="text-center">정기/명예</th>
	                                	<th class="text-center">팀</th>
	                                </thead>
	                                <tbody>
		                                <c:forEach items="${members}" var="member">
										    <tr>
										    	<td class="text-center">${member.name}</td>
										    	<td class="text-center">${member.phone}</td>
										    	<td class="text-center">${member.position}</td>
										    	<td class="text-center">${member.occupation}</td>
										    	<td class="text-center">${member.grade}</td>
										    	<td class="text-center">${member.team}</td>
										    </tr>
										</c:forEach>
	                                </tbody>
	                            </table>
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