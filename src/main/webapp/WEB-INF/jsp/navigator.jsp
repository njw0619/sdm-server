<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script>
	$(function(){		
		setTimeout(function() {
	        toastr.options = {
	            closeButton: true,
	            progressBar: true,
	            showMethod: 'slideDown',
	            timeOut: 4000
	        };

	        $('#flashMessages li').each(function(index) {
	            if($(this).hasClass("success")){
	                toastr.success($(this).html());
	            }
	            else if($(this).hasClass("warning")){
	                toastr.warning($(this).html());
	            }
	            else if($(this).hasClass("fail")){
	                toastr.error($(this).html());
	            }
	        });

	    }, 500);		
	});
</script>

<div id="flashMessages" style="display: none;">
    <ol>
    	<c:forEach items="${flashMessages}" var="flashMessage">
            <li class="${flashMessage.type}">${flashMessage.message}</li>
        </c:forEach>
    </ol>
</div>

<c:remove var = "flashMessages"/>

<!-- Navigation -->
<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
    <div class="navbar-header">
        <a class="navbar-brand" href="/home">Welcome To SDM Football Club.</a>
    </div>
    <!-- /.navbar-header -->

    <div class="navbar-default sidebar" role="navigation">
        <div class="sidebar-nav">
            <ul class="nav" id="side-menu">
            	<li class="sidebar-search">
                    <!-- /input-group -->
                </li>
                <li>
                    <a href="members">회원 목록</a>
                </li>
                <li>
                    <a href="#">회비<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a href="/memberships">회비 납부이력</a>
                        </li>
                        <li>
                            <a href="/transactions">수입/지출내역</a>
                        </li>
                    </ul>
                    <!-- /.nav-second-level -->
                </li>
                <li>
                    <a href="/games/records">경기결과</a>
                </li>
                <li>
                    <a href="#">관리자<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a href="/memberships/form">회비 납부정보 입력</a>
                        </li>
                        <li>
                            <a href="/transactions/form">수입/지출내역 입력</a>
                        </li>
                        <li>
                            <a href="/games/records/form">경기결과 입력</a>
                        </li>
                    </ul>
                    <!-- /.nav-second-level -->
                </li>                
            </ul>
        </div>
        <!-- /.sidebar-collapse -->
    </div>
    <!-- /.navbar-static-side -->
</nav>