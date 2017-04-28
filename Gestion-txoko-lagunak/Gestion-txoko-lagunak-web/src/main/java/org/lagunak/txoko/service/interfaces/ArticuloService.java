package org.lagunak.txoko.service.interfaces;

import java.util.List;

import org.lagunak.txoko.articulo.ArticuloServiceRemote;
import org.lagunak.txoko.persisence.Articulo;

public interface ArticuloService {

	public void setArticuloServiceRemote(ArticuloServiceRemote articuloService);
	
	public List<Articulo> getAll();
	
	public Articulo getById(long codigo);
	
	public Articulo update(Articulo articulo);
	
	public Articulo create(Articulo articulo);
	
	public void delete (long codigoarticulo);
}
