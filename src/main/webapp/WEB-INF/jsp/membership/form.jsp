<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>회비 납부정보 입력</title>
		
		<!-- Bootstrap Core CSS -->
	    <link href="/static/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	    <!-- MetisMenu CSS -->
	    <link href="/static/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">
	    <!-- Custom CSS -->
	    <link href="/static/dist/css/sb-admin-2.css" rel="stylesheet">
	    <!-- Custom Fonts -->
	    <link href="/static/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	    
	    <link href="/static/css/toastr.min.css" rel="stylesheet" type="text/css">
	    
	    <!-- jQuery -->
	    <script src="/static/vendor/jquery/jquery.min.js"></script>
	    <!-- Bootstrap Core JavaScript -->
	    <script src="/static/vendor/bootstrap/js/bootstrap.min.js"></script>
	    <!-- Metis Menu Plugin JavaScript -->
	    <script src="/static/vendor/metisMenu/metisMenu.min.js"></script>
	    <!-- Custom Theme JavaScript -->
	    <script src="/static/dist/js/sb-admin-2.js"></script>

	    <script src="/static/js/toastr.min.js"></script>
    
	</head>
<body>
	<div id="wrapper">
	
		<%@ include file="/WEB-INF/jsp/navigator.jsp" %>
		     
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h2 class="page-header">회비 납부정보 입력</h2>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-12">
                                    <form role="form" action="" method="POST">
                                        <label class="col-lg-2">이름</label>
                                        <div class="col-lg-10 form-group">
                                            <input class="form-control" name="name" value="">
                                        </div>
                                        <label class="col-lg-2">회원구분</label>
                                        <div class="col-lg-10 form-group">
                                            <select class="form-control" name="is_regular">
                                            	<option value="true">정기</option>
                                            	<option value="false">명예</option>
                                            </select>
                                        </div>
                                        <label class="col-lg-2">납부월</label>
                                        <div class="col-lg-10 form-group">
                                            <input class="form-control" name="occur_months" value="">
                                            <p class="help-block">다수 개월 입력 시 쉼표로 구분하세요.</p>
                                        </div>
                                        <div class="col-lg-12 form-group text-center">
                                        	<button type="submit" class="btn btn-success">저장</button>
                                        	<button type="reset" class="btn btn-default">초기화</button>
                                        </div>
                                    </form>
                                </div>
                                <!-- /.col-lg-6 (nested) -->
                            </div>
                            <!-- /.row (nested) -->
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