<%@page import="ci.inventory.entity.Customersorder"%>
<%@page import="ci.inventory.entity.Products"%>
<%@page import="ci.inventory.entity.Users"%>
<%@page import="java.util.List"%>
<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
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
    <title>List Customer Order</title>
    <!-- Custom CSS -->
    <link href="assets/extra-libs/c3/c3.min.css" rel="stylesheet">
    <link href="assets/libs/chartist/dist/chartist.min.css" rel="stylesheet">
    <link href="assets/libs/datatables/css/datatables.min.css" rel="stylesheet">
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
            <!-- ============================================================== -->
            <!-- Bread crumb and right sidebar toggle -->
            
            <%@ include file="breadcrumb.jsp"%>
            <!-- End Bread crumb and right sidebar toggle -->

            <!-- Container fluid  -->
            <div class="container-fluid">
                <table id="myTable" class="table table-striped table-bordered display no-wrap" style="width:100%">
			    <thead>
			        <tr>
			            <th>Order Number</th>
			            <th>Customer</th>
			            <th>Total amount</th>
			            <th>Date</th>
			            <th>Action</th>
			        </tr>
			    </thead>
			    <tbody>
			    
			    <% List<Customersorder> listcustomerorder = (List<Customersorder>)request.getAttribute("listcustomerorder");%>
			  		<% for(Customersorder customerorder : listcustomerorder){ %>
			        <tr>
			        	<td><%= customerorder.getCustomerordernumber() %></td></td>
			            <td><%= customerorder.getCustomer().getCustomername()%></td>
			            <td><%= customerorder.getTotalamount() %></td>
			            <td><%= customerorder.getCreatedate()%></td>
			            <td>
			            	<a class="btn" href="customerorder?action=update&id=<%= customerorder.getId()%>">Edit</a>
			            </td>
			        </tr>
			    <% } %>
			    </tbody>
			</table>
                
            </div>
            <!-- ============================================================== -->
            <!-- End Container fluid  -->
            
            <!-- footer -->
            <%@ include file="footer.jsp" %>
            
        </div>
        <!-- End Page wrapper  -->
    </div>
    <!-- End Wrapper -->
    <!-- ============================================================== -->
    <!-- All Jquery 
    <script src="assets/libs/jquery/dist/jquery.min.js"></script>-->
    <script src="assets/libs/datatables/js/datatables.min.js"></script>
    <script src="assets/libs/popper.js/dist/umd/popper.min.js"></script>
    <script src="assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- apps -->
    <script type="text/javascript">
	    $(document).ready(function() {
	    	
	    	$('#modal').modal('toggle');
	    	$(document).ready(function() {
		    	
		    	$('#modal').modal('toggle');
		    	const $table = new DataTable('#myTable', {
		    		dom: 'lBfrtip',
		            buttons: [
		                'copyHtml5',
		                'excelHtml5',
		                'csvHtml5',
		                'pdfHtml5',
		                'print'
		            ]
		    	});
		    	
		    });
	    });
    </script>
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
    
</body>
</html>
<%}%>