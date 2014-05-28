<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<!DOCTYPE html>
<html lang="en">
  	<head>
	    <meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
    	<meta name="author" content="Pedro Almir">
    	<meta name="description" content="Convert an Eclipse Project to PDF">
    	<link rel="shortcut icon" href="${pageContext.request.contextPath}/img/favicon.ico">

    	<title>Convert an Eclipse Project to PDF</title>

    	<!-- Bootstrap core CSS -->
    	<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    	<link rel="shortcut icon" href="${pageContext.request.contextPath}/img/favicon.ico">

	    <!-- Bootstrap core CSS -->
	    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
	    <!-- Custom styles for this template -->
	    <link href="${pageContext.request.contextPath}/css/sticky-footer-navbar.css" rel="stylesheet">
	
	    <!-- Just for debugging purposes. Don't actually copy this line! -->
	    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
	
	    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	    <!--[if lt IE 9]>
	      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
	    <![endif]-->
  	</head>
  	<body>
    	<!-- Fixed navbar -->
    	<div class="navbar navbar-default navbar-fixed-top">
      		<div class="container">
        		<div class="navbar-header">
          			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
	           			<span class="sr-only">Toggle navigation</span>
	            		<span class="icon-bar"></span>
	            		<span class="icon-bar"></span>
	            		<span class="icon-bar"></span>
          			</button>
          			<a class="navbar-brand" href="#">Code to PDF Converter</a>
        		</div>
        		<div class="collapse navbar-collapse">
          			<ul class="nav navbar-nav">
            			<li class="active"><a href="#">Home</a></li>
            			<li><a href="#about">About</a></li>
            			<li><a href="#contact">Contact</a></li>
          			</ul>
        		</div><!--/.nav-collapse -->
      		</div>
    	</div>
    	<!-- Begin page content -->
    	<div class="container">
      		<div class="page-header">
        		<h2>Convert your source code to a PDF file</h2>
      		</div>
      		<div class="row">
      			<div class="col-md-6">
      				<div class="panel panel-default" style="height: 390px;">
						<div class="panel-heading">
					    	<h3 class="panel-title">Step 1: Upload the source code (ZIP file)</h3>
					  	</div>
					  	<div class="panel-body">
					    	<form action="${pageContext.request.contextPath}/convert" method="post" enctype="multipart/form-data">
					    		<div class="form-group">
									<label for="projectName">Project Name</label>
								    <input type="text" class="form-control" id="projectName" name="projectName" placeholder="Enter project name">
								</div>
								
								<div class="form-group">
									<label for="inputFile">File input</label> 
									<input type="file" id="inputFile" name="inputFile">
									<p class="help-block">Enter a source code ZIP File</p>
								</div>
								
								<div class="form-group">
								<label for="extensions">File Extensions</label> 
								<select multiple class="form-control" name="extensions">
									<option value="java">.java</option>
									<option value="jsp">.jsp</option>
									<option value="js">.js</option>
									<option value="css">.css</option>
									<option value="html">.html</option>
								</select>
								</div>
								
								<div class="form-group">
									<button type="submit" class="btn btn-primary" style="width: 100%;">Submit</button>
								</div>
							</form>
					  	</div>
					</div>
      			</div>
      			<div class="col-md-6">
      				<div class="panel panel-success" style="height: 390px;">
						<div class="panel-heading">
					    	<h3 class="panel-title">Step 2: Enjoy life</h3>
					  	</div>
					  	<div class="panel-body text-center">
					    	<img alt="Enjoy life" src="${pageContext.request.contextPath}/img/praia2.jpg" style="width: 97%;">
					  	</div>
					</div>
      			</div>
      		</div>
    	</div>

    	<div id="footer">
      		<div class="container">
        		<p class="text-muted">Developed by <a href="http://pedroalmir.com">Pedro Almir</a> © 2014</p>
      		</div>
    	</div>

	    <!-- Bootstrap core JavaScript
	    ================================================== -->
	    <!-- Placed at the end of the document so the pages load faster -->
	    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	    <script src="${pageContext.request.contextPath}/js/docs.min.js"></script>
  	</body>
</html>