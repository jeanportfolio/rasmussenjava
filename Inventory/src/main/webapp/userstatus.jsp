<%@page import="ci.inventory.entity.Stock_movement"%>
<%@page import="ci.inventory.entity.Categoryproduct"%>
<%@page import="ci.inventory.entity.Users"%>
<%@page import="ci.inventory.entity.Usersrole"%>
<%@page import="java.util.List"%>
<%@page import="ci.inventory.entity.Userstatus"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	if((Users)session.getAttribute("user") == null || session.getId().isEmpty()){
		response.sendRedirect("login.jsp");
	}else{
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- Tell the browser to be responsive to screen width -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Favicon icon -->
    <link rel="icon" type="image/png" sizes="16x16" href="/assets/images/favicon.png">
    <title>Adminmart Template - The Ultimate Multipurpose admin template</title>
    <!-- Custom CSS -->
    <link href="assets/extra-libs/c3/c3.min.css" rel="stylesheet">
    <link href="assets/libs/chartist/dist/chartist.min.css" rel="stylesheet">
    <link href="assets/extra-libs/jvector/jquery-jvectormap-2.0.2.css" rel="stylesheet" />
    <!-- Custom CSS -->
    <link href="dist/css/style.min.css" rel="stylesheet">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
</head>

<body>

    <!-- Preloader - style you can find in spinners.css -->
    <div class="preloader">
        <div class="lds-ripple">
            <div class="lds-pos"></div>
            <div class="lds-pos"></div>
        </div>
    </div>
    <!-- Main wrapper - style you can find in pages.scss -->
    <div id="main-wrapper" data-theme="light" data-layout="vertical" data-navbarbg="skin6" data-sidebartype="full"
        data-sidebar-position="fixed" data-header-position="fixed" data-boxed-layout="full">
        
        <!-- Topbar header - style you can find in pages.scss -->
        <%@ include file="header.jsp"%>
        <!-- End Topbar header -->
		<!-- ============================================================== -->
        <!-- Left Sidebar - style you can find in sidebar.scss  -->
        <%@ include file="menu.jsp"%>
        <!-- End Left Sidebar - style you can find in sidebar.scss  -->
        
        <!-- ============================================================== -->
        <!-- Page wrapper  -->
        <div class="page-wrapper">
            <!-- Bread crumb and right sidebar toggle -->
            <%@ include file="breadcrumb.jsp"%>
            
            <!-- End Bread crumb and right sidebar toggle -->
            <!-- ============================================================== -->
            <!-- Container fluid  -->
            <div class="container-fluid">
                <div class="row">
                    <div class="col-12">
                    	<div class="row">
		                    <div class="col-sm-12 offset-md-1 offset-lg-2 col-md-10 col-lg-8">
		                        <div class="card">
		                            <div class="card-body">
		                                <h4 class="card-title">Status User Form</h4>
		                                <% Userstatus userstatus = (Userstatus)request.getAttribute("userstatus"); %>
		                                <form class="row" action="userstatus" method="post">
		                                	<input type="hidden" name = "id" value = "<%= userstatus.getId() > 0 ? userstatus.getId():""%>" >
		                                	<input type="hidden" name="action" value="<%= userstatus.getId() <= 0 ? "create" : "update"%>">
		                                    <div class="form-group col-sm-12 col-md-6 col-lg-6">
		                                    	<label class="">Title </label>
		                                        <input required type="text" class="form-control" name="title" 
		                                        value="<%= userstatus.getTitle() == null ? "": userstatus.getTitle()%>" placeholder="Title">
		                                        
		                                    </div>
		                                    <div class="form-group col-sm-12 col-md-6 col-lg-6">
		                                    	
		                                    	
		                                    </div>
		                                    <div class="form-group col-sm-12 offset-lg-2 col-md-6 col-lg-4">
		                                    	<button type="reset" class="btn btn-light form-control">Reset</button>
		                                    </div>
		                                    <div class="form-group col-sm-12 col-md-6 col-lg-4">
		                                    	<input type="submit" value="Save" class="btn btn-primary form-control"/>
		                                    </div>
		                                </form>
		                            </div>
		                        </div>
		                    </div>
		            	</div>
                    </div>
                </div>
            </div>
          	
            <!-- ============================================================== -->
            <!-- footer -->
            <%@ include file="footer.jsp" %>
        </div>
        <!-- End Page wrapper  -->
    </div>
    <!-- End Wrapper -->
    <!-- ============================================================== -->
    
    <!-- All Jquery -->
    <script src="assets/libs/jquery/dist/jquery.min.js"></script>
    <script src="assets/libs/popper.js/dist/umd/popper.min.js"></script>
    <script src="assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- apps -->
    <!-- apps -->
    <script src="dist/js/app-style-switcher.js"></script>
    <script src="dist/js/feather.min.js"></script>
    <script src="assets/libs/perfect-scrollbar/dist/perfect-scrollbar.jquery.min.js"></script>
    <script src="dist/js/sidebarmenu.js"></script>
    <!--Custom JavaScript -->
    <script src="dist/js/custom.min.js"></script>
    <!--This page JavaScript -->
    <script src="assets/extra-libs/c3/d3.min.js"></script>
    <script src="assets/extra-libs/c3/c3.min.js"></script>
    <script src="assets/libs/chartist/dist/chartist.min.js"></script>
    <script src="assets/libs/chartist-plugin-tooltips/dist/chartist-plugin-tooltip.min.js"></script>
    <script src="assets/extra-libs/jvector/jquery-jvectormap-2.0.2.min.js"></script>
    <script src="assets/extra-libs/jvector/jquery-jvectormap-world-mill-en.js"></script>
    <script src="dist/js/pages/dashboards/dashboard1.min.js"></script>
</body>
</html>
<%}%>