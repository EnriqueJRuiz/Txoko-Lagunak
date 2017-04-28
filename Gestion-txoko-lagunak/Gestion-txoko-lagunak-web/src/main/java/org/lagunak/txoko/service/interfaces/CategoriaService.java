package org.lagunak.txoko.service.interfaces;

import java.util.List;

import org.lagunak.txoko.categoria.CategoriaServiceRemote;
import org.lagunak.txoko.persisence.Categoria;

public interface CategoriaService {

	public void setCategoriaServiceRemote(CategoriaServiceRemote categoriaService);
	
	public List<Categoria> getAll();
	
	public Categoria getById(long codigo);
	
	public Categoria update(Categoria categoria);
	
	public Categoria create(Categoria categoria);
	
	public void delete (long codigocategoria);
}
