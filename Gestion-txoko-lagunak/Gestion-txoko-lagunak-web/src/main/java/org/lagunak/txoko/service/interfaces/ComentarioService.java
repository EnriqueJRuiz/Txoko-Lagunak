package org.lagunak.txoko.service.interfaces;

import java.util.List;

import org.lagunak.txoko.comentario.ComentarioServiceRemote;
import org.lagunak.txoko.persisence.Comentario;

public interface ComentarioService {
	
	public void setComentarioServiceRemote(ComentarioServiceRemote comentarioService);
	
	public List<Comentario> getAll();
	
	public Comentario getById(long codigo);
	
	public Comentario update(Comentario comentario);
	
	public Comentario create(Comentario comentario);
	
	public void delete (long codigocomentario);
}
