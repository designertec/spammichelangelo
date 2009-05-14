<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="../componentes/css/estilo.css"	type="text/css" />
<script type="text/javascript" src="../componentes/js/sispam.js"></script>
<link rel="stylesheet" href="componentes/css/estilo.css" type="text/css" />
<script type="text/javascript" src="componentes/js/sispam.js"></script>
</head>
<body>
<table width="89%" id="cmnUsr" class="caminhoUsuario">
	<tr>
		<td><br>
		<div>Atendimento <img src="../componentes/img/seta.gif"/> Agenda M�dica <img
			src="../componentes/img/seta.gif" /> Incluir</div>
		</td>
	</tr>
</table>
<h2>Cadastro de Compromissos</h2>
<s:form action="">
	<table class="tabela_moldura">
		<tr>
			<td>
			<table border="0" width="90%" class="tabela_moldura" cellpadding="3"
				cellspacing="4">
				<tr>
					<td><label class="label">Tipo:</label></td>
					<td><select name="agendaMedica.tipo">
						<option value="0">Selecione</option>
						<option value="1">Consulta</option>
						<option value="2">Cirurgia</option>
						<option value="3">Reuni�o</option>
						<option value="4">Palestra</option>
						<option value="5">Semin�rio</option>
					</select></td>
				</tr>
				<tr>
					<td><label class="label">Data:</label></td>
					<td><s:textfield theme="simple" name="agendaMedica.data"
						size="10" maxlength="10"/>&nbsp;</td>
				</tr>
				<tr>
					<td><label class="label">Hora Inicial:</label></td>
					<td><s:textfield theme="simple" name="agendaMedica.horaInicial"
						size="4" maxlength="5" />&nbsp;&nbsp;&nbsp;&nbsp; <label
						class="label">Hora Final:</label>&nbsp; <s:textfield
						theme="simple" name="agendaMedica.horaFinal" size="4" maxlength="5" />&nbsp;</td>

				</tr>
				<tr>
					<td><label class="label">Descri��o:</label></td>
					<td colspan="4"><s:textfield theme="simple"
						name="agendaMedica.descricao" size="65" maxlength="50" />&nbsp;</td>
				</tr>
			</table>
		<table border="0" align="center">
		<tr>
			<td align="center"><br><input type="submit" value="Salvar" class="button"><br></td>		
		</tr>
		</table>
			</td>
		</tr>
	</table>
</s:form>
</body>
</html>