<%@page import="ci.inventory.entity.Customers"%>
<%@page import="ci.inventory.entity.Customersorder"%>
<%@page import="ci.inventory.entity.Orderitems"%>
<%@page import="ci.inventory.entity.Orderitems"%>
<%@page import="ci.inventory.entity.Customersorder"%>
<%@page import="ci.inventory.entity.Customers"%>
<%@page import="ci.inventory.entity.Products"%>
<%@page import="java.util.List"%>
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
    <title>Customer Order</title>
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
            <!-- Bread crumb and right sidebar toggle -->
            <%@ include file="breadcrumb.jsp"%>
            
            <!-- End Bread crumb and right sidebar toggle -->
            <!-- ============================================================== -->
            <!-- Container fluid  -->
            <div class="container-fluid">
                <div class="row">
                    <div class="col-12">
                    	<div class="row">
		                    <div class="col-sm-12 col-md-12 col-lg-12">
		                        <div class="card">
		                            <div class="card-body">
		                                <h4 class="card-title">Customer order Form</h4>
		                                <% Customersorder customerorder = (Customersorder)request.getAttribute("customerorder"); %>
		                                <form class="row" action="customerorder" method="post">
		                                	<input type="hidden" name = "id" value = "<%= customerorder.getId() > 0 ? customerorder.getId():""%>" >
		                                	<input type="hidden" name="action" value="<%= customerorder.getId() > 0 ? "update" : "create"%>">
		                                    <div class="form-group col-sm-12 offset-lg-2 col-md-6 col-lg-4">
		                                    	<button type="reset" class="btn btn-light form-control">Reset</button>
		                                    </div>
		                                    <div class="form-group col-sm-12 col-md-6 col-lg-4">
		                                    	<input type="submit" value="Save" class="btn btn-primary form-control"/>
		                                    </div>
		                                    <div class="form-group col-sm-12 col-md-6 col-lg-6">
		                                    	
		                                    	<label class="">Order Number </label>
		                                        <input type="text" class="form-control" name="ordernumber" 
		                                        value="<%= customerorder.getCustomerordernumber() == null ? "": customerorder.getCustomerordernumber() %>" 
		                                        placeholder="Can Be Auto Generated">
		                                    	
		                                    	<label class="">Customers </label>
		                                        <% List<Customers> listcustomer = (List<Customers>)request.getAttribute("listcustomer");%>
		                                        <select required class="form-control" name="idsupplier">
		                                        	
													<option value="" >Select a customer</option>
													<% for(Customers customer : listcustomer){ %>
          											<option <%if(customerorder.getIdcustomers() == customer.getId()){ %>selected<%} 
          											%> value="<%= customer.getId() %>"><%= customer.getCustomername()%></option>
									          		<% } %>
		                                        </select>
		                                    	
		                                        
		                                    </div>
		                                    <div class="form-group col-sm-12 col-md-6 col-lg-6">
		                                    	<label class="">Date</label>
		                                        <input type="text" class="form-control" name="datecreation" id="datecreation"
		                                        value="<%= customerorder.getCreatedate() == null ? "": customerorder.getCreatedate() %>" >		                                        
		                                        <label class="">Total amount</label>
		                                        <input required type="text" class="form-control" name="totalam" id="totalam"
		                                        value="<%= customerorder.getTotalamount() == null ? "": customerorder.getTotalamount() %>">
		                                       	<input required type="hidden" class="form-control" name="totalamount" id="totalamount"
		                                        value="<%= customerorder.getTotalamount() == null ? "": customerorder.getTotalamount() %>">
		                                       
		                                    </div>
		                                    
		                                    <div class="form-group col-sm-12 col-md-12 col-lg-12">
		                                    	<button id="addRow" type="button" class="btn btn-secondary form-control">
		                                    	<i class=" fas fa-plus"></i> Add Product</button>
		                                    </div>
		                                    
		                                    <div class="form-group col-sm-12 col-md-12 col-lg-12">
		                                    	<% List<Products> listproduct = (List<Products>)request.getAttribute("listproduct");%>
		                                    	<% List<Orderitems> listcustomerorderitem = (List<Orderitems>)request.getAttribute("listcustomerorderitem");%>
		                                    	<table id="orders" class="table table-striped table-bordered display no-wrap" style="width:100%">
		                                    		<thead>
		                                    			<tr>
		                                    				<th>#</th>
		                                    				<th>Product</th>
		                                    				<th>Unit Price</th>
		                                    				<th>Quantity</th>
		                                    				<th>Total</th>
		                                    				<th>Action</th>
		                                    			</tr>
		                                    		</thead>
		                                    		<tfoot></tfoot>
		                                    		<tbody>
		                                    			<% int i = 1; %>
		                                    			<% for(Orderitems orderitem : listcustomerorderitem){ %>
		                                    			<tr id="row<%=i%>">
		                                    				<td><%=i%></td>
		                                    				<td>
		                                    					<select id="" required class="form-control" name="idproduct[]">
		                                        	
																	<option title="" value="" >Select a product</option>
																	<% for(Products product : listproduct){ %>
				          											<option title="<%= product.getPrice()%>" <%if(orderitem.getIdproduct() == product.getId()){ %>selected<%} 
				          											%> value="<%= product.getId() %>"><%= product.getDesignation()%></option>
													          		<% } %>
						                                        </select>
		                                    				</td>
		                                    				<td>
		                                    					<input min='0' id="" name="price[]" type="number" value="<%= orderitem.getPrice()%>"></td>
		                                    				<td>
		                                    					<input min='0' id="" name="quantity[]" type="number" value="<%= orderitem.getQuantity()%>"></td>
		                                    				<td id="total<%=i%>">
		                                    					<input type="number" required disabled value="<%= orderitem.getPrice().intValue() * orderitem.getQuantity()%>"/></td>
		                                    				<td >
		                                    					<a href="" title="supprimer" id="remove<%= i%>" class="btn btn-danger" >remove</a>
																<input name="idorderitem[]" type="hidden" value="<%=orderitem.getId() %>" /></td>
		                                    			</tr>
		                                    			<%i++; } %>
		                                    		</tbody>
		                                    		
		                                    	</table>
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
    
    <!-- All Jquery 
    <script src="assets/libs/jquery/dist/jquery.min.js"></script>-->
    <script src="assets/libs/datatables/js/datatables.min.js"></script>
    <script src="assets/libs/popper.js/dist/umd/popper.min.js"></script>
    <script src="assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- apps -->
    <script type="text/javascript">
    
	    $('document').ready(function(){
	    	
	    	"use strict";
	    	const $tbody = $("#orders tbody");
	    	let totalAmount = $("#totalamount");
	    	let totalam = $("#totalam");
	        let counter = $tbody.children('tr').length;
	        let datecreation = $("#datecreation");
	        
	        //Initialize date creation if empty
	        if(datecreation.val() == ""){
	        	datecreation.val(new Date().toLocaleDateString("en-US"));
	        }
	        
	    	//function to compute the total of each row
	    	function total(){
	        	let prices = $tbody.children('tr td[2]');
	        	console.log(prices);
	        	alert(prices);
	        }
	    	//Function to compute the sum of all total
	        function totalamount(){
	        	let prices = $tbody.children('tr td[5]');
	        	console.log(prices);
	        	alert(prices);
	        } 
	    	
	        // Function to delete row from the table 
	        //console.log( $("a[id*='remove']"));
	        function removeRow(){
	        	$("a[id*='remove']").click(function(e) {
	        		e.preventDefault();
					var button_id = $(this).attr("id").slice(6);
					var idorderitem = $(this).next('input').val();
					var vehicule = $(this).closest("tr").find('td:eq(1) select option:selected').html();
					let i = 1;
		        
					if(idorderitem == "")
					{
						$("#row"+ button_id).remove();
						counter = $tbody.children('tr').length + 1;						
					}
					/*$("#orders tbody tr").each(function(){
						
						$(this).attr("id",'row'+i);
						$(this).find('td:first-child').html(i);
						$(this).find('td:last-child a').attr("id","remove"+i);
						i++;
					});*/
				});
	        }
	        	
				/*else{
					var mes = "Attention association existante !!!<br/>Voulez-vous supprimer le véhicule "+vehicule+" de la flotte ?";
					$("#mySuppression .modal-body > div").html(mes);
					$(".modal-body div").attr("class","alert alert-danger fade in");
					$("#mySuppression").modal();
					$("#supprim").on("click",function(){
						$.ajax({
							url : "PoolServlet?action=ajax&role=supprimer&idasso="+idasso,
							type : "GET",
							cache : "false",
							success : function(reponse){
								//(reponse.message)
								},
							error : function(xhr){//("erreur de suppresion association "+xhr.status+" "+xhr.statusText);
							}
						});
						$("#row"+ button_id).remove();
						$("#tab_clone  tbody  tr").each(function(){
							$(this).attr("id",'row'+i);
							$(this).find('td:first-child').html(i);
							$(this).find('td:last-child a').attr("id",i);
							i++;
						});
					});
				} 
			}	*/
			
	        
	    	//Function to add new row to the table
	        function addNewRow() {
	        	
	        	let $newtr = $("<tr id='row"+counter+"'><td>"+counter+"</td><td><select id='' required class='form-control' name='idproduct[]'>"+
	        			"<option selected title='' value='' >Select a product</option><% for(Products product : listproduct){ %>"+
	        			"<option title='<%= product.getPrice()%>' value='<%= product.getId() %>'><%= product.getDesignation()%></option><% } %>"+
	        			"<select/></td><td><input min='0' name='price[]' required type='number'/></td>"+
			        	"<td><input min='0' name='quantity[]' required type='number'/></td><td id='total"+counter+"'><input type='text' disabled required/></td>"+
			        	"<td><a href='' name='remove' title='supprimer' id='remove"+counter+"' class='btn btn-danger' >remove</a><input type='hidden' name='idorderitem[]'/></td></tr>" );
	        	
	            $tbody.prepend($newtr);
	        	counter++;
	        	onchangement();
	        	removeRow();
	        }
	         
	        const table = new DataTable('#orders',{
	        			Search: false,
	        			columnDefs: [
	        				{
	        					orderable: false,
	        					targets: '_all'	
	        				}
	        				]
	        });
	         
	        $('#addRow').click(addNewRow);
	         
	        // Automatically add a first row of data if no rows
	        if(counter < 1)
	        {
	        	counter++;
	        	addNewRow();
			}else{
				counter++;
			}
	        
	        
	        //Check an input value
	        function isValid(str) {
	           if (str === "" || str === null || str === undefined) {
	           		return false;
	           } else {
	              	return true;
	           }
	        }
	        
	        //Compute the Total Amount 
	        function totalamount(){
	        	//Get all the total cells
	        	let totalamount = 0;
	        	let totals = $tbody.find('tr > td[id*="total"]> input');
	        	
	        	for(let i = 0; i < totals.length; i++){
	        		let total = $(totals[i]);
	        		
	        		if(isValid(total.val())){
	        			totalamount = Number(totalamount) + Number(total.val());
	        		}else{
	        			totalamount = "";
	        			break;
	        		}
	        	}
	        	return totalamount;
	        	//console.log(totalamount);
	        }
	        
	        //Get all the listed products
	        function idproductes(){
        		let lesid = [];
        		let select = $("select[name='idproduct[]']");
				
				//console.log("before lesid  : "+ lesid.length);
		        $("select[name='idproduct[]']").each(function(){
					if($(this).children(':selected').val().trim() != ""){
						lesid.push($(this).children(':selected').val());
					}
						
        		});
		        //console.log("after lesid  : "+ lesid.length);
        		
        		return lesid;
        	};
        	
	        //Events on change update fields
	        function onchangement()
	        {
	        	//Product selection
	        	//Gathering all the selected products 
				
		        $("select[name='idproduct[]']").each(function(index)
		        {
		        	//For each product changement the corresponding price is applied
		        	$(this).on('change', function(e)
		        	{
		        		let idproducts = idproductes();
		        		//console.log("idproducts.size: " + idproducts.length);
			        	let select = $(this);
			        	let selectioned = false;
						let valeur = $(this).children(':selected').val();
			        	//Browse products selected to avoid duplicate selection
			        	for(const id of idproducts.values())
			        	{
			        		//console.log("Value id  : "+ id);
			        		if(id == valeur)
			        		{

			        			//console.log("option:selected");
			        			//console.log($("select[name='idproduct[]']>option:selected[value='"+id+"']"));
			        			if($("select[name='idproduct[]']>option:selected[value='"+id+"']").length == 2 ){
			        				selectioned = true;
			        				break;
			        			}
			        		}
			        	}
		        	
			        	if(selectioned)
			        	{
			        		//console.log("Risk of duplicate: "+ select.find('option:selected').text());
		        			alert('Product already selected');
		        			select.find('option:first').attr("selected",'selected');
			        	}else
			        	{
				        	let price = select.parent().next('td').children('input');
				        	price.val(select.children(':selected').attr('title'));
				        	
				        	let quatity = select.parent().next().next('td').children('input');
				        	let total = select.parent().next().next().next('td[id*="total"]').children('input');
				        	
				        	if(isValid(price.val()) && isValid(quatity.val()))
				        	{
				        		total.val(Number(price.val()) * Number(quatity.val()));
				        	}else{
				        		total.val("");
				        	}
				        	totalAmount.val(totalamount());
				        	totalam.val(totalamount());
			        	}
		        	});
		        });
		        
		      	//For each price changement the total is updated
		        $("input[name='price[]']").each(function(index){
			        $(this).on('change blur keyup click', function(e){
			        	let price = $(this);
			        	let quatity = price.parent().next('td').children('input');
			        	let total = price.parent().next().next('td[id*="total"]').children('input');
			        	
			        	if(isValid(price.val()) && isValid(quatity.val()))
			        		total.val(Number(price.val()) * Number(quatity.val()));
			        	else
			        		total.val("");
			        	//console.log(total.val());
			        	totalAmount.val(totalamount());
			        	totalam.val(totalamount());
			        });
		        });
		        
		      //For each quantity changement the total is updated
		        $("input[name='quantity[]']").each(function(index){
		        	$(this).on('change blur keyup click', function(e){
			        	let quatity = $(this);
			        	let price = quatity.parent().prev('td').children('input');
			        	let total = quatity.parent().next('td[id*="total"]').children('input');
			        	
			        	if(isValid(price.val()) && isValid(quatity.val()))
			        		total.val(Number(price.val()) * Number(quatity.val()));
			        	else
			        		total.val("");
			        	//console.log(total.val());	
			        	totalAmount.val(totalamount());
			        	totalam.val(totalamount());
		        	});
		        });
	        }
	        
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
    <!-- <script src="assets/libs/chartist-plugin-tooltips/dist/chartist-plugin-tooltip.min.js"></script>
    <script src="assets/extra-libs/jvector/jquery-jvectormap-2.0.2.min.js"></script>
    <script src="assets/extra-libs/jvector/jquery-jvectormap-world-mill-en.js"></script>
    <script src="dist/js/pages/dashboards/dashboard1.min.js"></script> -->
</body>
</html>
<%}%>