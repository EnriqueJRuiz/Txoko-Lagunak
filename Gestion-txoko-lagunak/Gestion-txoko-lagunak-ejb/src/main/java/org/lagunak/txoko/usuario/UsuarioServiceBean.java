package org.lagunak.txoko.usuario;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.lagunak.txoko.persisence.Usuario;

/**
 * Session Bean implementation class UsuarioServiceBean
 */
@Stateless(name = "usuarioServiceBean")
public class UsuarioServiceBean implements UsuarioServiceRemote {
	@PersistenceContext(unitName = "gestiontxoko")
	private EntityManager entityManager;
	@Resource
	private SessionContext sessionContext;
    /**
     * Default constructor. 
     */
    public UsuarioServiceBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public List<Usuario> getAll() {
		TypedQuery<Usuario> usuarios = entityManager.createNamedQuery("usuario.getAll",Usuario.class);
		return usuarios.getResultList();
	}

	@Override
	public Usuario getById(long codigo) {
		Usuario usuario = entityManager.find(Usuario.class,codigo);
		return usuario;
	}

	@Override
	public Usuario update(Usuario usuario) {
		entityManager.merge(usuario);
		return usuario;
	}

	@Override
	public Usuario create(Usuario usuario) {
		usuario = entityManager.merge(usuario);
		entityManager.flush();
		return usuario;
	}

	@Override
	public void delete(long codigo) {
		entityManager.remove(entityManager.find(Usuario.class,codigo));
	}

}
