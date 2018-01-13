<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cadastro de Livros</title>
</head>
<body>
	<c:url value="/products/save" var="url"/>
	<form action="${url}" method="post">
		<div>
			<label for="titulo">Título</label>
			<input type="text" name="titulo" id="title">
		</div>
		<div>
			<label for="descricao">Descrição</label>
			<textarea cols="10" rows="20" name="descricao" id="descricao"></textarea>
		</div>
		<div>
			<label for="paginas">Número de páginas</label>
			<input type="text" name="paginas" id="paginas">
		</div>		
		<div>
			<input type="submit" value="Enviar">
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
	</form>
</body>
</html>