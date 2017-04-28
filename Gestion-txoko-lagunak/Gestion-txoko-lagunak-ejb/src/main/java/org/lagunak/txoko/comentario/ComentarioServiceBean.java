package org.lagunak.txoko.comentario;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.lagunak.txoko.persisence.Comentario;

/**
 * Session Bean implementation class ComentarioServiceBean
 */
@Stateless(name="comentarioServiceBean")
public class ComentarioServiceBean implements ComentarioServiceRemote {
    @PersistenceContext(unitName = "gestiontxoko")
	private EntityManager entityManager;
	@Resource
	private SessionContext sessionContext;
    /**
     * Default constructor. 
     */
    public ComentarioServiceBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public List<Comentario> getAll() {
		TypedQuery<Comentario> comentarios = entityManager.createNamedQuery("comentario.getAll",Comentario.class);
		return comentarios.getResultList();
	}

	@Override
	public Comentario getById(long codigo) {
		Comentario comentario = entityManager.find(Comentario.class, codigo);
		return comentario;
	}

	@Override
	public Comentario update(Comentario comentario) {
		entityManager.merge(comentario);
		return comentario;
	}

	@Override
	public Comentario create(Comentario comentario) {
		comentario=entityManager.merge(comentario);
		entityManager.flush();
		return comentario;
	}

	@Override
	public void delete(long codigo) {
		entityManager.remove(entityManager.find(Comentario.class,codigo));
	}

}
