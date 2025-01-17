package br.com.sispam.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.sispam.banco.Conexao;
import br.com.sispam.dominio.EspecialidadeMedica;

public class EspecialidadeDao {
	
	private Conexao conexao;
	private EntityManager manager;

	
	/**
	 * : Recupera a especialidade apartir do id passado.
	 * @param id
	 * @return
	 */
	public EspecialidadeMedica recuperarEspecialidade(int id){
		conexao = new Conexao();
		manager = conexao.getEntityManger();
		return manager.find(EspecialidadeMedica.class, id) ;
	}
	
	/**
	 * : Recupera todas as especialidades cadastradas.
	 * @return
	 */
	public List<EspecialidadeMedica> recuperarTodas(){
		conexao = new Conexao();
		manager = conexao.getEntityManger();
		Query query = manager.createQuery("from EspecialidadeMedica");
		return query.getResultList();
	}
	
	/**
	 * : Recupera todas as especialidades cadastradas menos a do medico.
	 * @return
	 */
	public List<EspecialidadeMedica> recuperarTodas(List<Integer> lista){
		conexao = new Conexao();
		manager = conexao.getEntityManger();
		Query query = manager.createQuery("from EspecialidadeMedica where id not in (:lista)");
		query.setParameter("lista", lista);
		return query.getResultList();
	}
	
	/**
	 * : Recupera uma lista de especialidades apartir dos ids passados.
	 * @param lista
	 * @return
	 */
	public List<EspecialidadeMedica> recuperarEspecialidades(List<Integer> lista){
		conexao = new Conexao();
		manager = conexao.getEntityManger();
		Query query = manager.createQuery("from EspecialidadeMedica where id in (:lista)");
		query.setParameter("lista", lista);
		return query.getResultList();
	}

}
