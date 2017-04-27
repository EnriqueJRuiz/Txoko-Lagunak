package org.lagunak.txoko.categoria;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import org.lagunak.txoko.persisence.Categoria;

/**
 * Session Bean implementation class CategoriaServiceBean
 */
@Stateless(name="categoriaServiceBean")
public class CategoriaServiceBean implements CategoriaServiceRemote {
    @PersistenceContext(unitName = "gestiontxoko")
	private EntityManager entityManager;
	@Resource
	private SessionContext sessionContext;
    /**
     * Default constructor. 
     */
    public CategoriaServiceBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public List<Categoria> getAll() {
		TypedQuery<Categoria> categorias = entityManager.createNamedQuery("categoria.getAll",Categoria.class);
		return categorias.getResultList();
	}

	@Override
	public void delete(int codigo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Categoria getById(long codigo) {
		Categoria categoria= entityManager.find(Categoria.class, codigo);
		return categoria;
	}

	@Override
	public Categoria update(Categoria categoria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Categoria create(Categoria categoria) {
		// TODO Auto-generated method stub
		return null;
	}




}
