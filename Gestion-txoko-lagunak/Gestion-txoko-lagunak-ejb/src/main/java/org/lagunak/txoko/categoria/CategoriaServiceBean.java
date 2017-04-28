package org.lagunak.txoko.categoria;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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
	public Categoria getById(long codigo) {
		Categoria categoria = entityManager.find(Categoria.class, codigo);
		return categoria;
	}

	@Override
	public Categoria update(Categoria categoria) {
		entityManager.merge(categoria);
		return categoria;
	}

	@Override
	public Categoria create(Categoria categoria) {
		categoria=entityManager.merge(categoria);
		entityManager.flush();
		return categoria;
	}
	
	@Override
	public void delete(long codigo) {
		entityManager.remove(entityManager.find(Categoria.class, codigo));
	}

}
