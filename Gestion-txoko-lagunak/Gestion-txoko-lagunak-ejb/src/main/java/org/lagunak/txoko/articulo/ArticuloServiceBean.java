package org.lagunak.txoko.articulo;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.lagunak.txoko.persisence.Articulo;



/**
 * Session Bean implementation class ArticuloServiceBean
 */
@Stateless(name="articuloServiceBean")
public class ArticuloServiceBean implements ArticuloServiceRemote {
	@PersistenceContext(unitName = "gestiontxoko")
	private EntityManager entityManager;
	@Resource
	private SessionContext sessionContext;
	
    /**
     * Default constructor. 
     */
    public ArticuloServiceBean() {
        // TODO Auto-generated constructor stub
    }
    
	@Override
	public List<Articulo> getAll() {
		TypedQuery<Articulo> articulos = entityManager.createNamedQuery("articulo.getAll",Articulo.class);
		return articulos.getResultList();
	}

	@Override
	public Articulo getById(long codigo) {
		Articulo articulo = entityManager.find(Articulo.class, codigo);
		return articulo;
	}

	@Override
	public Articulo update(Articulo articulo) {
		entityManager.merge(articulo);
		return articulo;
	}

	@Override
	public Articulo create(Articulo articulo) {
		articulo=entityManager.merge(articulo);
		entityManager.flush();
		return articulo;
	}
	
	@Override
		public void delete(long codigo) {
		entityManager.remove(entityManager.find(Articulo.class, codigo));
		
	}

}
