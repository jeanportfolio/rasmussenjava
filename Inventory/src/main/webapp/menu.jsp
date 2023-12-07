<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<aside class="left-sidebar" data-sidebarbg="skin6">
	<!-- Sidebar scroll-->
	<div class="scroll-sidebar" data-sidebarbg="skin6">
		<!-- Sidebar navigation-->
		<nav class="sidebar-nav">
			<ul id="sidebarnav">
				<li class="sidebar-item"><a class="sidebar-link sidebar-link"
					href="index.jsp" aria-expanded="false"><i data-feather="home"
						class="feather-icon"></i><span class="hide-menu">Dashboard</span></a></li>
				
				<li class="list-divider"></li>
				<li class="nav-small-cap"><span class="hide-menu">Product Area</span></li>
				<li class="sidebar-item"><a class="sidebar-link has-arrow" href="" aria-expanded="false">
					<i data-feather="tag" class="feather-icon"></i>
					<span class="hide-menu">Categories </span></a>
					<ul aria-expanded="false" class="collapse  first-level base-level-line">
						<li class="sidebar-item"><a href="category"
							class="sidebar-link"><span class="hide-menu">New Category </span></a></li>
						<li class="sidebar-item"><a href="category?action=list"
							class="sidebar-link"><span class="hide-menu">Categories List </span></a></li>
					</ul></li>
				<li class="sidebar-item"><a class="sidebar-link has-arrow" href="" aria-expanded="false">
					<i data-feather="message-square" class="feather-icon"></i>
					<span class="hide-menu">Products</span></a>
					<ul aria-expanded="false" class="collapse  first-level base-level-line">
						<li class="sidebar-item"><a href="product"	class="sidebar-link">
							<span class="hide-menu">New Product </span></a></li>
						<li class="sidebar-item"><a href="product?action=list"
							class="sidebar-link"><span class="hide-menu">Products List </span></a></li>
					</ul></li>

				<li class="list-divider"></li>
				<li class="nav-small-cap"><span class="hide-menu">Stock Area</span></li>
				
				<li class="sidebar-item"><a class="sidebar-link has-arrow" href="" aria-expanded="false">
					<i data-feather="message-square" class="feather-icon"></i>
					<span class="hide-menu">Inventories</span></a>
					<ul aria-expanded="false" class="collapse  first-level base-level-line">
						<li class="sidebar-item"><a href="stockinventory" class="sidebar-link">
							<span class="hide-menu">New Stock </span></a></li>
						<li class="sidebar-item"><a href="stockinventory?action=list"
							class="sidebar-link"><span class="hide-menu">List </span></a></li>
					</ul></li>
				
				<li class="sidebar-item"><a class="sidebar-link has-arrow" href="a" aria-expanded="false">
					<i data-feather="message-square" class="feather-icon"></i>
					<span class="hide-menu">Stock up</span></a>
					<ul aria-expanded="false" class="collapse  first-level base-level-line">
						<li class="sidebar-item"><a href="stockinventory"	class="sidebar-link">
							<span class="hide-menu">New Order </span></a></li>
						<li class="sidebar-item"><a href="stockinventory?action=list"
							class="sidebar-link"><span class="hide-menu">List orders </span></a></li>
					</ul></li>	
				<li class="sidebar-item"><a class="sidebar-link has-arrow" href="app-chat.html" aria-expanded="false">
					<i data-feather="message-square" class="feather-icon"></i>
					<span class="hide-menu">Suppliers</span></a>
					<ul aria-expanded="false" class="collapse  first-level base-level-line">
						<li class="sidebar-item"><a href="supplier"	class="sidebar-link">
							<span class="hide-menu">New </span></a></li>
						<li class="sidebar-item"><a href="supplier?action=list"
							class="sidebar-link"><span class="hide-menu">List </span></a></li>
					</ul></li>
						
				<li class="list-divider"></li>
				<li class="nav-small-cap"><span class="hide-menu">Customer Area</span></li>

				<li class="sidebar-item"><a class="sidebar-link has-arrow"
					href="javascript:void(0)" aria-expanded="false"><i
						data-feather="file-text" class="feather-icon"></i><span
						class="hide-menu">Customer </span></a>
					<ul aria-expanded="false"
						class="collapse  first-level base-level-line">
						<li class="sidebar-item"><a href="customer"
							class="sidebar-link"><span class="hide-menu"> Create</span></a></li>
						<li class="sidebar-item"><a href="customer?action=list"
							class="sidebar-link"><span class="hide-menu"> List</span></a></li>
					</ul></li>
				<li class="sidebar-item"><a class="sidebar-link has-arrow"
					href="javascript:void(0)" aria-expanded="false"><i
						data-feather="file-text" class="feather-icon"></i><span
						class="hide-menu">Order </span></a>
					<ul aria-expanded="false"
						class="collapse  first-level base-level-line">
						<li class="sidebar-item"><a href="customerorder"
							class="sidebar-link"><span class="hide-menu"> Create</span></a></li>
						<li class="sidebar-item"><a href="customerorder?action=list"
							class="sidebar-link"><span class="hide-menu"> List</span></a></li>
					</ul></li>
					
				<li class="list-divider"></li>
				<li class="nav-small-cap"><span class="hide-menu">User Area</span></li>

				<li class="sidebar-item"><a class="sidebar-link has-arrow"
					href="javascript:void(0)" aria-expanded="false"><i
						data-feather="file-text" class="feather-icon"></i><span
						class="hide-menu">Roles </span></a>
					<ul aria-expanded="false"
						class="collapse  first-level base-level-line">
						<li class="sidebar-item"><a href="usersrole"
							class="sidebar-link"><span class="hide-menu"> Create</span></a></li>
						<li class="sidebar-item"><a href="usersrole?action=list"
							class="sidebar-link"><span class="hide-menu"> List</span></a></li>
					</ul></li>
				
				<li class="sidebar-item"><a class="sidebar-link has-arrow"
					href="javascript:void(0)" aria-expanded="false"><i
						data-feather="file-text" class="feather-icon"></i><span
						class="hide-menu">Users </span></a>
					<ul aria-expanded="false"
						class="collapse  first-level base-level-line">
						<li class="sidebar-item"><a href="users"
							class="sidebar-link"><span class="hide-menu"> Create</span></a></li>
						<li class="sidebar-item"><a href="users?action=list"
							class="sidebar-link"><span class="hide-menu"> List</span></a></li>
					</ul></li>
				
				<li class="sidebar-item"><a class="sidebar-link has-arrow"
					href="javascript:void(0)" aria-expanded="false"><i
						data-feather="grid" class="feather-icon"></i><span
						class="hide-menu">Status </span></a>
					<ul aria-expanded="false"
						class="collapse  first-level base-level-line">
						<li class="sidebar-item"><a href="userstatus"
							class="sidebar-link"><span class="hide-menu"> Create</span></a></li>
						<li class="sidebar-item"><a href="userstatus?action=list"
							class="sidebar-link"><span class="hide-menu"> List</span></a></li>
					</ul></li>
		
				<li class="list-divider"></li>
				<li class="nav-small-cap"><span class="hide-menu">Parameters & Monitoring</span></li>
				<li class="sidebar-item"><a class="sidebar-link has-arrow" href="app-chat.html" aria-expanded="false">
					<i data-feather="message-square" class="feather-icon"></i>
					<span class="hide-menu">Movements Stock</span></a>
					<ul aria-expanded="false" class="collapse  first-level base-level-line">
						<li class="sidebar-item"><a href="stockmovement"	class="sidebar-link">
							<span class="hide-menu">Create </span></a></li>
						<li class="sidebar-item"><a href="stockmovement?action=list"
							class="sidebar-link"><span class="hide-menu">List </span></a></li>
					</ul></li>
				<li class="sidebar-item"><a class="sidebar-link has-arrow" href="javascript:void(0)" aria-expanded="false">
					<i data-feather="file-text" class="feather-icon"></i><span class="hide-menu">User Activities </span></a>
					<ul aria-expanded="false" class="collapse  first-level base-level-line">
						<li class="sidebar-item"><a href="form-inputs.html"
							class="sidebar-link"><span class="hide-menu"> Form
									Inputs </span></a></li>
						<li class="sidebar-item"><a href="form-input-grid.html"
							class="sidebar-link"><span class="hide-menu"> Form
									Grids </span></a></li>
						<li class="sidebar-item"><a href="form-checkbox-radio.html"
							class="sidebar-link"><span class="hide-menu">Checkboxes & Radios </span></a></li>
					</ul></li>
				
				<li class="sidebar-item"><a class="sidebar-link has-arrow"
					href="javascript:void(0)" aria-expanded="false"><i
						data-feather="feather" class="feather-icon"></i><span
						class="hide-menu">Activities log </span></a>
					<ul aria-expanded="false"
						class="collapse first-level base-level-line">
						<li class="sidebar-item"><a href="icon-fontawesome.html"
							class="sidebar-link"><span class="hide-menu">
									Fontawesome Icons </span></a></li>

						<li class="sidebar-item"><a href="icon-simple-lineicon.html"
							class="sidebar-link"><span class="hide-menu"> Simple
									Line Icons </span></a></li>
					</ul></li>

			</ul>
		</nav>
		<!-- End Sidebar navigation -->
	</div>
	<!-- End Sidebar scroll-->
</aside>
