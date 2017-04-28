package org.lagunak.txoko.categoria;

import java.util.List;

import javax.ejb.Remote;

import org.lagunak.txoko.persisence.Categoria;

@Remote
public interface CategoriaServiceRemote {
	
	public List<Categoria> getAll();
	
	public Categoria getById(long codigo);
	
	public Categoria update(Categoria categoria);
	
	public Categoria create(Categoria categoria);
	
	public void delete (long codigo);
}
