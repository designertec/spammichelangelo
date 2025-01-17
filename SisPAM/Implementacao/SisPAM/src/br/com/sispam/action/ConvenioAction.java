package br.com.sispam.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityExistsException;

import br.com.sispam.dominio.Convenio;

import br.com.sispam.enums.Acao;
import br.com.sispam.enums.Funcionalidade;
import br.com.sispam.excecao.CampoInteiroException;
import br.com.sispam.excecao.CampoInvalidoException;
import br.com.sispam.facade.AuditoriaFacade;
import br.com.sispam.facade.ConvenioFacade;
import br.com.sispam.util.AuditoriaUtil;
import br.com.sispam.util.CampoUtil;


public class ConvenioAction extends Action{

	private Convenio convenio;
	private ConvenioFacade convenioFacade;
	private int isExisteConvenio;
	private List<Convenio> conveniosCadastrados;
	private String codigoANSAux;
	private String telefoneAux;
	private String cepAux;
	private String cnpjAux;
	private String dddAux;
	private AuditoriaFacade auditoriaFacade;
	
	/**
	 * : Recebe os dados da tela para efetuar a inclusão do convênio.
	 * @return
	 */
	public String incluirConvenio(){
		convenioFacade = new ConvenioFacade();
		
		//limpa os caracteres dos campos
		telefoneAux = CampoUtil.replaceCampo("-", telefoneAux);
		cepAux = CampoUtil.replaceCampo("-", cepAux);
		
		//monta um mapa com todos os campos que devem ser inteiros.	
		Map<String, String> mapa = new HashMap<String, String>();
		mapa.put("codigoANS", codigoANSAux);
		mapa.put("cep", cepAux);
		mapa.put("ddd", dddAux);
		mapa.put("telefone", telefoneAux);		

		try {
			
			//verifica se os campo obrigatorios foram preenchidos
			convenioFacade.validaCampos(convenio);

			//verifica se os campos são inteiros
			convenioFacade.verificaCampoInteiro(mapa);
			
			//seta os valores das variváveis auxiliares.
			convenio.setCodigoANS(Integer.parseInt(codigoANSAux));
			convenio.setCep(Long.parseLong(cepAux));
			convenio.setDdd(Integer.parseInt(dddAux));			
			convenio.setTelefone(Integer.parseInt(telefoneAux));

			//verifica se já existe convênio cadastrado com esses dados.
			convenioFacade.verificaExistencia(convenio);
			
			if(convenio.getId() > 0){
				convenioFacade.salvaConvenio(convenio);
				mensagens.put("salvo", "Convênio alterado com sucesso!");
				//salva o Log de auditoria
				auditoriaFacade = new AuditoriaFacade();
				auditoriaFacade.gravaAuditoria(AuditoriaUtil.montaAuditoria(Funcionalidade.MANTER_CONVENIO, Acao.ALTERACAO, getUsuarioLogado()));
			}else{
				convenioFacade.salvaConvenio(convenio);
				mensagens.put("salvo", "Convênio cadastrado com sucesso!");
				//salva o Log de auditoria
				auditoriaFacade = new AuditoriaFacade();
				auditoriaFacade.gravaAuditoria(AuditoriaUtil.montaAuditoria(Funcionalidade.MANTER_CONVENIO, Acao.INCLUSAO, getUsuarioLogado()));
			}	
		}catch (CampoInvalidoException e) {
			e.printStackTrace();
			erros.put("campoInvalido", e.getMessage());
			apresentaErrors();
			return FALHA_SALVAR_CONVENIO;
		}catch (CampoInteiroException e) {
			erros.put("campoInvalido", e.getMessage());
			apresentaErrors();
			return FALHA_SALVAR_CONVENIO;
		}
		apresentaMensagens();
		this.limparCampos();
		return SUCESSO_INCLUIR_CONVENIO;
	}
	
	/**
	 * : Recebe o convênio a ser excluído.
	 * @return
	 */
	public String excluirConvenio(){

		this.convenioFacade = new ConvenioFacade();		
		try {
			this.convenioFacade.excluiConvenio(this.convenio);
			mensagens.put("excluido", "Convenio excluido com sucesso!");
			//salva o Log de auditoria
			auditoriaFacade = new AuditoriaFacade();
			auditoriaFacade.gravaAuditoria(AuditoriaUtil.montaAuditoria(Funcionalidade.MANTER_CONVENIO, Acao.EXCLUSAO, getUsuarioLogado()));
		}catch (CampoInvalidoException e) {
			e.printStackTrace();					
			return FALHA_EXCLUIR_CONVENIO;
		}catch (EntityExistsException e){
			erros.put("erro", "Atenção! Pacientes vinculados a esse convênio, não permitindo a sua exclusão.");			
			return FALHA_EXCLUIR_CONVENIO;
		}		
		apresentaMensagens();		
		return SUCESSO_EXCLUIR_CONVENIO;
	}
	/**
	 * : Realiza a consulta de convênios.
	 * @return
	 */
	public String consultarConvenio(){
		convenioFacade = new ConvenioFacade();
		try {
			this.conveniosCadastrados = new ArrayList<Convenio>();
			this.conveniosCadastrados = convenioFacade.pesquisaConvenio(convenio);
			//salva o Log de auditoria
			auditoriaFacade = new AuditoriaFacade();
			auditoriaFacade.gravaAuditoria(AuditoriaUtil.montaAuditoria(Funcionalidade.MANTER_CONVENIO, Acao.CONSULTA, getUsuarioLogado()));
		} catch (CampoInvalidoException e) {
			erros.put("erro", e.getMessage());
		}
		apresentaErrors();
		apresentaMensagens();
		limparCampos();
		return LISTAR_CONVENIOS;

	}
	
	/**
	 * : Carrega a tela de consulta de convênios.
	 * @return
	 */
	public String carregarConsulta(){
		this.convenioFacade = new ConvenioFacade();
		limparCampos();
		apresentaErrors();
		this.conveniosCadastrados = this.convenioFacade.recuperarUltimosCadastrados();
		return SUCESSO_CARREGAR_CONSULTA;
	}
	
	/**
	 * : Carrega os últimos convênios cadastrados e define a tela para realizar consultas.
	 * @return
	 */
	public String definirTelaConsulta(){		
			this.convenioFacade = new ConvenioFacade();
			this.conveniosCadastrados = this.convenioFacade.recuperarUltimosCadastrados();
		return SUCESSO_TELA_CONSULTA;
	}
	
	/**
	 * : Carrega o convênio a ser alterado.
	 * @return
	 */
	public String carregaEdicaoConvenio(){
		this.convenioFacade = new ConvenioFacade();
		this.convenio = this.convenioFacade.recuperarPeloId(convenio.getId());
		this.codigoANSAux = String.valueOf(convenio.getCodigoANS());
		this.cepAux = String.valueOf(convenio.getCep());
		this.dddAux = String.valueOf(convenio.getDdd());
		this.telefoneAux = String.valueOf(convenio.getTelefone());
		return SUCESSO_INCLUIR_CONVENIO;
	}


	/*Utilitário*/
	/**
	 * : Limpa os campos da tela.
	 */
	private void limparCampos(){
		this.convenio = null;
		this.cepAux = null;
		this.cnpjAux = null;
		this.codigoANSAux = null;
		this.dddAux = null;
		this.telefoneAux = null;
	}

	public Convenio getConvenio() {
		return convenio;
	}

	public void setConvenio(Convenio convenio) {
		this.convenio = convenio;
	}

	public ConvenioFacade getConvenioFacade() {
		return convenioFacade;
	}

	public void setConvenioFacade(ConvenioFacade convenioFacade) {
		this.convenioFacade = convenioFacade;
	}

	public int getIsExisteConvenio() {
		return isExisteConvenio;
	}

	public void setIsExisteConvenio(int isExisteConvenio) {
		this.isExisteConvenio = isExisteConvenio;
	}

	public List<Convenio> getConveniosCadastrados() {
		return conveniosCadastrados;
	}

	public void setConveniosCadastrados(List<Convenio> conveniosCadastrados) {
		this.conveniosCadastrados = conveniosCadastrados;
	}

	public String getCodigoANSAux() {
		return codigoANSAux;
	}

	public void setCodigoANSAux(String codigoANSAux) {
		this.codigoANSAux = codigoANSAux;
	}

	public String getTelefoneAux() {
		return telefoneAux;
	}

	public void setTelefoneAux(String telefoneAux) {
		this.telefoneAux = telefoneAux;
	}

	public String getCepAux() {
		return cepAux;
	}

	public void setCepAux(String cepAux) {
		this.cepAux = cepAux;
	}

	public String getCnpjAux() {
		return cnpjAux;
	}

	public void setCnpjAux(String cnpjAux) {
		this.cnpjAux = cnpjAux;
	}

	public String getDddAux() {
		return dddAux;
	}

	public void setDddAux(String dddAux) {
		this.dddAux = dddAux;
	}

	public AuditoriaFacade getAuditoriaFacade() {
		return auditoriaFacade;
	}

	public void setAuditoriaFacade(AuditoriaFacade auditoriaFacade) {
		this.auditoriaFacade = auditoriaFacade;
	}


}
