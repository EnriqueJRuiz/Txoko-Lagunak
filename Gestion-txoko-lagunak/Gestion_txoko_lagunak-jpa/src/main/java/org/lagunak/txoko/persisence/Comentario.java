package org.lagunak.txoko.persisence;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity(name = "comentario")
@Table(name = "comentario")
@NamedQueries({ @NamedQuery(name = "comentario.getAll", query = "SELECT co FROM comentario as co WHERE co.activo =true") })
public class Comentario implements Comparable<Comentario>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int CODIGO_NULO = -1;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo")
	private long codigo;
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "codigo_usuario")
	private Usuario usuario;
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "codigo_articulo")
	private Articulo articulo;
	private String texto;
	private Date fpublicacion;
	private boolean activo;
	
	public Comentario() {
		super();
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Date getFpublicacion() {
		return fpublicacion;
	}

	public void setFpublicacion(Date fpublicacion) {
		this.fpublicacion = fpublicacion;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	@Override
	public String toString() {
		return "Comentario [codigo=" + codigo + ", usuario=" + usuario + ", articulo=" + articulo + ", texto=" + texto
				+ ", fpublicacion=" + fpublicacion + ", activo=" + activo + "]";
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
		Comentario other = (Comentario) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}

	@Override
	public int compareTo(Comentario o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
