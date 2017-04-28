package org.lagunak.txoko.comentario;

import java.util.List;

import javax.ejb.Remote;

import org.lagunak.txoko.persisence.Comentario;

@Remote
public interface ComentarioServiceRemote {

	public List<Comentario> getAll();
	
	public Comentario getById(long codigo);
	
	public Comentario update(Comentario comentario);
	
	public Comentario create(Comentario comentario);
	
	public void delete (long codigo);
}
