<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>경기 결과</title>
		
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
                    <h2 class="page-header">경기 결과</h2>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-body">
							<!-- Nav tabs -->
                            <ul class="nav nav-tabs">
                                <li class="active"><a href="#match" data-toggle="tab">순위</a></li>
                                <li><a href="#schedule" data-toggle="tab">일정</a></li>
                                <li><a href="#attend" data-toggle="tab">출석</a></li>
                                <li><a href="#goal" data-toggle="tab">득점</a></li>
                                <li><a href="#mvp" data-toggle="tab">MVP</a></li>
                            </ul>
                            <!-- Tab panes -->
                            <div class="tab-content">                               
                                <div class="tab-pane fade in active" id="match">
                                    <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
		                                <thead>
		                                    <tr>
		                                    	<th class="text-center">순위</th>
		                                    	<th class="text-center">팀</th>
		                                    	<th class="text-center">경기</th>
		                                    	<th class="text-center">승점</th>
		                                        <th class="text-center">승</th>
		                                        <th class="text-center">무</th>
		                                        <th class="text-center">패</th>
		                                        <th class="text-center">득실차</th>
		                                        <th class="text-center">득점</th>
		                                        <th class="text-center">실점</th>
		                                    </tr>
		                                </thead>
		                                <tbody>
		                                    <tr>
		                                    	<td class="text-center">1</td>
		                                    	<td class="text-center">${first.team}</td>
		                                    	<td class="text-center">${count}</td>
		                                    	<td class="text-center">${first.point}</td>
		                                    	<td class="text-center">${first.win}</td>
		                                    	<td class="text-center">${first.draw}</td>
		                                    	<td class="text-center">${first.lose}</td>
		                                    	<td class="text-center">${first.diff}</td>
		                                    	<td class="text-center">${first.goal}</td>
		                                    	<td class="text-center">${first.losePoint}</td>
		                                    </tr>
		                                    <tr>
		                                    	<td class="text-center">2</td>
		                                    	<td class="text-center">${second.team}</td>
		                                    	<td class="text-center">${count}</td>
		                                    	<td class="text-center">${second.point}</td>
		                                   		<td class="text-center">${second.win}</td>
		                                    	<td class="text-center">${second.draw}</td>
		                                    	<td class="text-center">${second.lose}</td>
		                                    	<td class="text-center">${second.diff}</td>
		                                    	<td class="text-center">${second.goal}</td>
		                                    	<td class="text-center">${second.losePoint}</td>
		                                    </tr>
		                                </tbody>
		                            </table>
                                </div>
                                <div class="tab-pane fade" id="schedule">
                                    <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                                    	<thead>
                                    		<th class="text-center">일시</th>
                                    		<th class="text-center">장소</th>
                                    		<th class="text-center">결과</th>
                                    	</thead>
		                                <tbody>
			                                <c:forEach items="${history}" var="score">
											    <tr>
											    	<td class="text-center">${score.occurDate}</td>
											    	<td class="text-center">${score.place}</td>
											    	<td class="text-center">청 <strong>${score.score1} : ${score.score2}</strong> 백</td>
											    </tr>
											</c:forEach>
		                                </tbody>
		                            </table>
                                </div>
                                <div class="tab-pane fade" id="attend">
                                    <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
		                                <thead>
		                                	<th class="text-center">순위</th>
		                                	<th class="text-center">이름</th>
		                                	<th class="text-center">팀</th>
		                                	<th class="text-center">출석횟수</th>		                            
		                                </thead>
		                                <tbody>
			                                <c:forEach items="${attendRanking}" var="record">
											    <tr>
											    	<td class="text-center">${record.rank}</td>
											    	<td class="text-center">${record.NAME}</td>
											    	<td class="text-center">${record.TEAM}</td>
											    	<td class="text-center">${record.COUNT}</td>
											    </tr>
											</c:forEach>
		                                </tbody>
		                            </table>
                                </div>
                                <div class="tab-pane fade" id="goal">
                                    <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
		                                <thead>
		                                	<th class="text-center">순위</th>
		                                	<th class="text-center">이름</th>
		                                	<th class="text-center">팀</th>
		                                	<th class="text-center">골</th>		                            
		                                </thead>
		                                <tbody>
			                                <c:forEach items="${goalRanking}" var="record">
											    <tr>
											    	<td class="text-center">${record.rank}</td>
											    	<td class="text-center">${record.NAME}</td>
											    	<td class="text-center">${record.TEAM}</td>
											    	<td class="text-center">${record.GOAL}</td>
											    </tr>
											</c:forEach>
		                                </tbody>
		                            </table>
                                </div>
                                <div class="tab-pane fade" id="mvp">
                                    <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
		                                <thead>
		                                	<th class="text-center">순위</th>
		                                	<th class="text-center">이름</th>
		                                	<th class="text-center">팀</th>
		                                	<th class="text-center">MVP횟수</th>		                            
		                                </thead>
		                                <tbody>
			                                <c:forEach items="${mvpRanking}" var="record">
											    <tr>
											    	<td class="text-center">${record.rank}</td>
											    	<td class="text-center">${record.NAME}</td>
											    	<td class="text-center">${record.TEAM}</td>
											    	<td class="text-center">${record.COUNT}</td>
											    </tr>
											</c:forEach>
		                                </tbody>
		                            </table>
                                </div>
                            </div>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->
</body>
</html>