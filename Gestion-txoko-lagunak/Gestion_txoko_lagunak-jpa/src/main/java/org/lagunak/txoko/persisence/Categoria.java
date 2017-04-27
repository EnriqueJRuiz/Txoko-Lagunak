package org.lagunak.txoko.persisence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity(name = "categoria")
@Table(name = "categoria")
@NamedQueries({ @NamedQuery(name = "categoria.getAll", query = "SELECT ca FROM categoria as ca WHERE ca.activo =true") })
public class Categoria implements Comparable<Categoria>, Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int CODIGO_NULO = -1;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo")
	private long codigo;
	private String nombre;
	private boolean activo;
	@Transient
	private List<Articulo> articulos;
	
	public Categoria() {
		super();
	}

	public long getCodigo() {
		return codigo;
	}


	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public boolean isActivo() {
		return activo;
	}


	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public List<Articulo> getArticulos() {
		return articulos;
	}

	public void setArticulos(List<Articulo> articulos) {
		this.articulos = articulos;
	}

	@Override
	public String toString() {
		return "Categoria [codigo=" + codigo + ", nombre=" + nombre + ", activo=" + activo + ", articulos=" + articulos
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (codigo ^ (codigo >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categoria other = (Categoria) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}

	@Override
	public int compareTo(Categoria o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
