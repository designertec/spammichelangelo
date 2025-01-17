package br.com.sispam.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.RollbackException;

import br.com.sispam.banco.Conexao;
import br.com.sispam.dominio.Convenio;
import br.com.sispam.dominio.Paciente;

public class PacienteDao {

	private Conexao conexao;
	private EntityManager manager;


	/**
	 * : Salva o paciente no banco.
	 * @param paciente
	 * @return
	 */
	public Paciente salvar(Paciente paciente){
		this.conexao = new Conexao();
		this.manager = conexao.getEntityManger();

		this.manager.getTransaction().begin();
		if(paciente != null && paciente.getId() > 0){
			this.manager.merge(paciente);
		}
		else{
			this.manager.persist(paciente);
		}
		this.manager.getTransaction().commit();
		return paciente;
	}

	/**
	 * : Recupera o paciente pelo seu id
	 * @param id
	 * @return
	 */
	public Paciente recuperarPeloId(int id){
		Paciente paciente = null;
		this.conexao = new Conexao();
		this.manager = conexao.getEntityManger();
		paciente = this.manager.find(Paciente.class, id);
		return paciente;
	}

	/**
	 * Recupera todos os pacientes do convenio passado.
	 * @param convenio
	 * @return
	 */
	public List<Paciente> recuperaTodosPaciente(Convenio convenio, int status){
		this.conexao = new Conexao();
		this.manager = conexao.getEntityManger();
		Query query = this.manager.createQuery("from Paciente where convenio.id = :idConvenio and status = :status");
		query.setParameter("idConvenio", convenio.getId());
		query.setParameter("status", status);
		return query.getResultList();
	}

	public Paciente recuperarPeloUsuario(int idUsuario){
		this.conexao = new Conexao();
		this.manager = conexao.getEntityManger();
		Query query = this.manager.createQuery("from Paciente where usuario.id = :id");
		query.setParameter("id", idUsuario);
		return (Paciente)query.getSingleResult();
	}

	/**
	 * : Lista os ultimos pacientes cadastrados
	 * @return
	 */
	public List<Paciente> recuperaUltimosCadastrados(){
		this.conexao = new Conexao();
		this.manager = conexao.getEntityManger();
		List<Paciente> lista = null;
		try{
			Query query = this.manager.createQuery("from Paciente where usuario.status = 1 order by id desc");
			query.setMaxResults(8);
			lista = query.getResultList();
		}catch (NoResultException e) {
			e.printStackTrace();
		}
		return lista;
	}

	/**
	 * : Recupera o paciente pelo seu CPF.
	 * @param cpf
	 * @return
	 */
	public Paciente recuperarPeloCpf(String cpf, int status){
		this.conexao = new Conexao();
		this.manager = conexao.getEntityManger();
		Paciente paciente = null;
		try{
			Query query = this.manager.createQuery("from Paciente where usuario.cpf = :cpf and usuario.status = :status");
			query.setParameter("cpf", cpf);
			query.setParameter("status", status);
			paciente = (Paciente) query.getSingleResult();
		}catch (NoResultException e) {
			e.printStackTrace();
			paciente = null;
		}
		return paciente;
	}

	/**
	 * : Recupera os pacientes pelo seu NOME.
	 * @param nome
	 * @return
	 */
	public List<Paciente> recuperarPeloNome(String nome, int status){
		this.conexao = new Conexao();
		this.manager = this.conexao.getEntityManger();
		List<Paciente> pacientes = null;
		try{
			Query query = this.manager.createQuery("from Paciente where usuario.nome like :nome and usuario.status = :status");
			query.setParameter("nome", "%"+nome+"%");
			query.setParameter("status", status);
			pacientes = query.getResultList();
		}catch (NoResultException e) {
			e.printStackTrace();
			pacientes = null;
		}
		return pacientes;
	}

	/**
	 * : Recupera todos os pacientes cadastrados.
	 * @return
	 */
	public List<Paciente> recuperarTodos(){
		this.conexao = new Conexao();
		this.manager = conexao.getEntityManger();
		List<Paciente> pacientes = null;
		try{
			Query query = this.manager.createQuery("from Paciente where usuario.status = 1");
			pacientes = query.getResultList();
		}catch (NoResultException e) {
			e.printStackTrace();
			pacientes = null;
		}
		return pacientes;
	}


	/**
	 * : Remove o paciente do sistema.
	 * @param paciente
	 */
	public void removerPaciente(Paciente paciente) throws RollbackException{
		this.conexao = new Conexao();
		this.manager = conexao.getEntityManger();
		this.manager.getTransaction().begin();
		paciente = this.manager.merge(paciente);
		this.manager.remove(paciente);
		this.manager.getTransaction().commit();
	}
}
