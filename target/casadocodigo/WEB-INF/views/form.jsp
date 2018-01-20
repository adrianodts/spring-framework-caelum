<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cadastro de Livros</title>
</head>
<body>
	<spring:hasBindErrors name="produto">
		<ul>
			<c:forEach var="error" items="${errors.allErrors}">
				<li>${error.code}</li>
			</c:forEach>
		</ul>
	</spring:hasBindErrors>
	<br/>
	<c:url value="/products/save" var="url"/>
	<form:form action="${spring:mvcUrl('PC#save').build()}" 
		method="POST" commandName="produto" 
		enctype="multipart/form-data">
		<div>
			<label for="titulo">Título</label>
			<form:input path="titulo" id="titulo" />
			<form:errors path="titulo" />
		</div>
		<div>
			<label for="descricao">Descrição</label>
			<form:textarea cols="10" rows="20" path="descricao" id="descricao"/>
			<form:errors path="descricao" />
		</div>
		<div>
			<label for="paginas">Número de páginas</label>
			<form:input path="paginas" id="paginas"/>
			<form:errors path="paginas" />
		</div>produto
		<div>
			<label for="dataLancamento">Data de Lançamento</label>
			<form:input path="dataLancamento" type="date" id="dataLancamento"/>
			<form:errors path="dataLancamento" />
		</div>				
		<div>
			<c:forEach items="${types}" var="tipoLivro" varStatus="status">
				<label for="price_${tipoLivro}">${tipoLivro}</label>
				<input type="text" name="prices[${status.index}].valor" id="price_${tipoLivro}">
				<input type="hidden" 
					name="prices[${status.index}].tipoLivro" 
					value="${tipoLivro}">
				<br/>
			</c:forEach>
		</div>
		<div>
			<label for="sumario">Sumário do Livro</label>
			<input type="file" id="dataLancamento" name="arquivo"/>
		</div>
		<div>
			<input type="submit" value="Enviar">
		</div>		
	</form:form>
</body>
</html>