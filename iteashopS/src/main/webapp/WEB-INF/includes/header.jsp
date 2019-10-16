<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<!--
Design by Free CSS Templates
http://www.freecsstemplates.org
Released for free under a Creative Commons Attribution 2.5 License

Name       : Photoshoot 
Description: A two-column, fixed-width design with dark color scheme.
Version    : 1.0
Released   : 20110926

-->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Пора своё иметь</title>
<link href="<c:url value="/resources/styles/style.css"/>" rel="stylesheet" type="text/css" media="screen" />
<!-- 
<script type="text/javascript" src="jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="jquery.poptrox-0.1.js"></script>
 -->
</head>
<body>
<div id="header" class="container">
	<div id="logo">
		<h1><a href="#">Мой магазин </a></h1>
		<!-- <p>freecsstemplates<a href="http://www.freecsstemplates.org"></a></p> -->
	</div>
	<div id="menu">
		<ul>
			<li 
			<c:if test="${requestScope['javax.servlet.forward.request_uri'].endsWith('/main')}">
				class="current_page_item"
			</c:if>
			><a href="./main">Главная</a></li>
			<li 
			<c:if test="${requestScope['javax.servlet.forward.request_uri'].endsWith('/products')}">
				class="current_page_item"
			</c:if>
			><a href="./products">Товары</a></li>
			<li 
			<c:if test="${requestScope['javax.servlet.forward.request_uri'].endsWith('/register')}">
				class="current_page_item"
			</c:if>
			><a href="./register">Регистрация</a></li>
			<li 
			<c:if test="${requestScope['javax.servlet.forward.request_uri'].endsWith('/login')}">
				class="current_page_item"
			</c:if>
			><a href="./login">Вход</a></li>
			<li 
			<c:if test="${requestScope['javax.servlet.forward.request_uri'].endsWith('/cart')}">
				class="current_page_item"
			</c:if>
			><a href="./cart">Корзина</a></li>
		</ul>
	</div>
</div>
<!-- end #header -->
<div id="poptrox">
	<!-- start -->
	<ul id="gallery">
		<li><a href="images/img01_big.jpg"><img src="<c:url value="/resources/images/img01.jpg"/>" title="." alt="" /></a></li>
		<li><a href="images/img02_big.jpg"><img src="<c:url value="/resources/images/img02.jpg"/>" title="." alt="" /></a></li>
		<li><a href="images/img03_big.jpg"><img src="<c:url value="/resources/images/img03.jpg"/>" title="." alt="" /></a></li>
		<li><a href="images/img04_big.jpg"><img src="<c:url value="/resources/images/img04.jpg"/>" title="." alt="" /></a></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
	</ul>
	<br class="clear" />
	<!-- <script type="text/javascript">
		$('#gallery').poptrox();
		</script> -->
	<!-- end -->
</div>
<div id="page">
	<div id="bg1">
		<div id="bg2">
			<div id="bg3">
				<div id="content">
