<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>회비 납부이력 확인</title>
		
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
    	
    	<script>
    	$(function(){		
    		$("button[name='copyUnpaid']").click(function(){
    			$("textarea[name='memo']").select();
    			document.execCommand("copy");
    			//window.clipboardData.setData("text", $("input[name='memo']").val());
    			//document.execCommand($("input[name='memo']").val());   			
    		});
    	});
    	</script>
	</head>
<body>
	<div id="wrapper">
	
		<%@ include file="/WEB-INF/jsp/navigator.jsp" %>
		     
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h2 class="page-header">회비</h2>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                        <div class="row">
                        	<div class="col-lg-10">회비 미납자</div>
                        	<div class="col-lg-2 text-right"><button type="button" class="btn btn-danger" name="copyUnpaid">미납자 복사하기</button></div></div>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <table class="table">
	                                <thead>
	                                	<th class="text-center">이름</th>
	                                	<th class="text-center">직업</th>
	                                	<th class="text-center">상태</th>
	                                	<th class="text-center">최근납부월</th>		                            
	                                </thead>
	                                <tbody id="unpaidTable">
		                                <c:forEach items="${unpaid}" var="member">
										    <tr>
										    	<td class="text-center">${member.name}</td>
										    	<td class="text-center">${member.occupation}</td>
										    	<td class="text-center">${member.grade}</td>
										    	<td class="text-center">${member.occurMonth}</td>
										    </tr>
										</c:forEach>
	                                </tbody>
	                            </table>
                            </div>                            
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                    <div class="panel panel-default">
                        <div class="panel-heading">회비 완납자</div>
                        <div class="panel-body">
                            <div class="row">
                                <table class="table">
	                                <thead>
	                                	<th class="text-center">이름</th>
	                                	<th class="text-center">직업</th>
	                                	<th class="text-center">상태</th>
	                                	<th class="text-center">최근납부월</th>		                            
	                                </thead>
	                                <tbody>
		                                <c:forEach items="${paid}" var="member">
										    <tr>
										    	<td class="text-center">${member.name}</td>
										    	<td class="text-center">${member.occupation}</td>
										    	<td class="text-center">${member.grade}</td>
										    	<td class="text-center">${member.occurMonth}</td>
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
    <textarea name="memo" style="height:0px">${memo}</textarea>
</body>
</html>