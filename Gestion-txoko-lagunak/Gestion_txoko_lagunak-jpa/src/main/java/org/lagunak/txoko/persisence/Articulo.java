package org.lagunak.txoko.persisence;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
import javax.persistence.Transient;


@Entity(name = "articulo")
@Table(name = "articulo")
@NamedQueries({ @NamedQuery(name = "articulo.getAll", query = "SELECT a FROM articulo as a WHERE a.activo =true") })
public class Articulo implements Comparable<Articulo>, Serializable {

	
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
	private String texto;
	private String imagen;
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "codigo_usuario")
	private Usuario usuario;
	@Transient
	private List<Comentario> comentarios;
	private Date fpublicacion;
	private boolean activo;
	private boolean permiso_comentar;

	public Articulo() {
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

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
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

	public boolean isPermiso_comentar() {
		return permiso_comentar;
	}

	public void setPermiso_comentar(boolean permiso_comentar) {
		this.permiso_comentar = permiso_comentar;
	}

	@Override
	public String toString() {
		return "Articulo [codigo=" + codigo + ", nombre=" + nombre + ", texto=" + texto + ", imagen=" + imagen
				+ ", usuario=" + usuario + ", comentarios=" + comentarios + ", fpublicacion=" + fpublicacion
				+ ", activo=" + activo + ", permiso_comentar=" + permiso_comentar + "]";
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
		Articulo other = (Articulo) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}



	@Override
	public int compareTo(Articulo o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
