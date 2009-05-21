<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="componentes/css/estilo.css" type="text/css" />
	<script type="text/javascript" src="componentes/js/sispam.js"></script>
	<link rel="stylesheet" href="../componentes/css/estilo.css" type="text/css" />
	<script type="text/javascript" src="../componentes/js/sispam.js"></script>
	<title>Atualizar Hist�rico de Prontuario</title>
</head>
<body>
	<table width="89%" id="cmnUsr" class="caminhoUsuario">
	<tr>
    <td>
    	<br>
		<div>Atendimento<img src="../componentes/img/seta.gif" />    		
    	Hist�rico de Prontu�rio<img src="../componentes/img/seta.gif" />
		Atualizar				    		
    	</div>		
    </td>	
	</tr>	
	</table>
	<h2>Atualiza Hist�rico de Prontu�rio</h2>
	<s:form action="historicoProntuarioAction!atualizarHistoricoProntuario.action" theme="simple">	

	<s:hidden name="paciente.id" value="%{paciente.id}"/>
		<table border="0" class="tabela_moldura" cellpadding="3" cellspacing="4" >
			<tr>
				<td width="271px"><label class="label">Nome:</label></td>
				<td><s:property value="paciente.usuario.nome"></s:property></td>
				
				<td width="271px"><label class="label">CPF:</label></td>
				<td><s:label  value="%{usuario.cpf}" theme="simple" name="usuario.cpf"/></td>
			</tr>
			<tr>
				<td><label class="label">RG:</label></td>
				<td><s:label value="%{rgAux}" theme="simple" name="rgAux"/></td>
				<td><label class="label">Data de Nascimento:</label></td>
				<td><s:label value="%{usuario.dataNascimento}" theme="simple" name="usuario.dataNascimento"/></td>				
			</tr>
			<tr>
				<td><label class="label">Endere�o:</label></td>
				<td><s:label value="%{usuario.endereco}" theme="simple" name="usuario.endereco"/></td>
				<td><label class="label">Cidade:</label></td>
				<td><s:label value="%{usuario.cidade}" theme="simple" name="usuario.cidade"/></td>
			</tr>
			<tr>
				<td><label class="label">Estado:</label></td>
				<td>
				<s:label value="%{usuario.uf}" name="usuario.uf" theme="simple"/></td>						
					<td><label class="label">CEP:</label></td>
					<td><s:label value="%{cepAux}" theme="simple" name="cepAux"/></td>				
			</tr>
			<tr>
				<td><label class="label">DDD:</label></td>
				<td>
					<s:label value="%{dddAux}" theme="simple" name="dddAux"/></td>
					<td><label class="label">Telefone:</label></td><td>
					<s:label value="%{telefoneAux}" theme="simple" name="telefoneAux"/>
				</td>
			</tr>
			<tr>				
				<td><label class="label">E-mail:</label></td>
				<td><s:label  value="%{usuario.email}" theme="simple" name="usuario.email"/></td>
				<td><label class="label">Sexo:</label></td>
				<td><s:label value="%{usuario.sexo}" theme="simple" name="usuario.sexo"/></td>				
			</tr>			
		</table>
		<table class="tabela_moldura">
			<tr>
				<td><label>Sintoma</label>
			</tr>
			<tr>
				<td><s:textarea name="historicoProntuario.sintoma" id="sintoma" theme="simple" cols="70" rows="5"/></td>
			</tr>
			<tr>	
				<td><label>Laudo</label></td>
			</tr>
			<tr>
				<td><s:textarea name="historicoProntuario.laudo" id="sintoma" theme="simple" cols="70" rows="5"/></td>
			</tr>
			<tr>	
				<td><label>Prescri��o</label>
			</tr>
			<tr>
				<td><s:textarea name="historicoProntuario.prescricao" id="sintoma" theme="simple" cols="70" rows="5"/></td>
			</tr>
			<tr>	
				<td><label>Observa��o</label></td>
			</tr>
			<tr>
				<td><s:textarea name="historicoProntuario.observacao" id="sintoma" theme="simple" cols="70" rows="5"/></td>
			</tr>
			<tr>				
				<td align="center"><s:submit value="Atualizar" cssClass="button" theme="simple"/></td>
			</tr>
		</table>
	</s:form>	
</body>
</html>