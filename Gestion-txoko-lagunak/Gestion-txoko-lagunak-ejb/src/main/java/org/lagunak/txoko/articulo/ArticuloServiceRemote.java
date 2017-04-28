package org.lagunak.txoko.articulo;

import java.util.List;

import javax.ejb.Remote;

import org.lagunak.txoko.persisence.Articulo;



@Remote
public interface ArticuloServiceRemote {

public List<Articulo> getAll();
	
	public Articulo getById(long codigo);
	
	public Articulo update(Articulo articulo);
	
	public Articulo create(Articulo articulo);
	
	void delete(long codigo);

}
