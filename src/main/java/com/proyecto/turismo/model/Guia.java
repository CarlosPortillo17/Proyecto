package com.proyecto.turismo.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="tb_guia")
public class Guia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_guia")
	private Integer idguia;
	
	@NotBlank
	//@Column(name="des_guia")
	@Column(columnDefinition = "TEXT")
	private String desGuia;
	
	@NotBlank
	//@Column(name="nom_guia")
	private String nomGuia;
	
	@NotBlank
	//@Column(name="apellido_guia")
	private String apellidoGuia;
	
	@NotBlank
	//@Column(name="edad_guia")
	private String edadGuia;
	
	//AGREGUE PARA COMBO NACIONALIDAD
		@NotNull
		@ManyToOne
		@JoinColumn(name="id_nacionalidad")
		private Nacionalidad nacionalidades;
		
	@NotBlank
	//@Column(name="correo_guia")
	private String correoGuia;
	
	@NotBlank
	//@Column(name="celular_guia")
	private String celularGuia;
	
	//AGREGUE PARA COMBO HORARIOS
	@NotNull
	@ManyToOne
	@JoinColumn(name="id_horario")
	private Horario horarios;
	
	//AGREGUE PARA COMBO PUNTOS
	@NotNull
	@ManyToOne
	@JoinColumn(name="id_punto")
	private Puntos puntos;
	
	@NotNull
	@DateTimeFormat(iso = ISO.DATE)
	//@Column(name="fechaGuia")
	private LocalDate fechaGuia;
	
	@NotBlank
	//@Column(name="precio_guia")
	private String precioGuia;


	private String rutaPortada;

	@NotEmpty
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "lenguaje_guia", joinColumns = @JoinColumn(name = "id_guia"), inverseJoinColumns = @JoinColumn(name = "id_lenguaje"))
	private List<Lenguaje> lenguajes;
	
	@Transient
	private MultipartFile portada;
	
	public Guia() {
		
	}

	public Guia(Integer idguia, @NotBlank String desGuia, @NotBlank String nomGuia, @NotBlank String apellidoGuia,
			@NotBlank String edadGuia, @NotEmpty Nacionalidad nacionalidades, @NotBlank String correoGuia,
			@NotBlank String celularGuia, @NotEmpty Horario horarios, @NotEmpty Puntos puntos,
			@NotNull LocalDate fechaGuia, @NotBlank String precioGuia, String rutaPortada,
			@NotEmpty List<Lenguaje> lenguajes, MultipartFile portada) {
		super();
		this.idguia = idguia;
		this.desGuia = desGuia;
		this.nomGuia = nomGuia;
		this.apellidoGuia = apellidoGuia;
		this.edadGuia = edadGuia;
		this.nacionalidades = nacionalidades;
		this.correoGuia = correoGuia;
		this.celularGuia = celularGuia;
		this.horarios = horarios;
		this.puntos = puntos;
		this.fechaGuia = fechaGuia;
		this.precioGuia = precioGuia;
		this.rutaPortada = rutaPortada;
		this.lenguajes = lenguajes;
		this.portada = portada;
	}

	public Guia(@NotBlank String desGuia, @NotBlank String nomGuia, @NotBlank String apellidoGuia,
			@NotBlank String edadGuia, @NotEmpty Nacionalidad nacionalidades, @NotBlank String correoGuia,
			@NotBlank String celularGuia, @NotEmpty Horario horarios, @NotEmpty Puntos puntos,
			@NotNull LocalDate fechaGuia, @NotBlank String precioGuia, String rutaPortada,
			@NotEmpty List<Lenguaje> lenguajes, MultipartFile portada) {
		super();
		this.desGuia = desGuia;
		this.nomGuia = nomGuia;
		this.apellidoGuia = apellidoGuia;
		this.edadGuia = edadGuia;
		this.nacionalidades = nacionalidades;
		this.correoGuia = correoGuia;
		this.celularGuia = celularGuia;
		this.horarios = horarios;
		this.puntos = puntos;
		this.fechaGuia = fechaGuia;
		this.precioGuia = precioGuia;
		this.rutaPortada = rutaPortada;
		this.lenguajes = lenguajes;
		this.portada = portada;
	}

	public Integer getIdguia() {
		return idguia;
	}

	public void setIdguia(Integer idguia) {
		this.idguia = idguia;
	}

	public String getDesGuia() {
		return desGuia;
	}

	public void setDesGuia(String desGuia) {
		this.desGuia = desGuia;
	}

	public String getNomGuia() {
		return nomGuia;
	}

	public void setNomGuia(String nomGuia) {
		this.nomGuia = nomGuia;
	}

	public String getApellidoGuia() {
		return apellidoGuia;
	}

	public void setApellidoGuia(String apellidoGuia) {
		this.apellidoGuia = apellidoGuia;
	}

	public String getEdadGuia() {
		return edadGuia;
	}

	public void setEdadGuia(String edadGuia) {
		this.edadGuia = edadGuia;
	}

	public Nacionalidad getNacionalidades() {
		return nacionalidades;
	}

	public void setNacionalidades(Nacionalidad nacionalidades) {
		this.nacionalidades = nacionalidades;
	}

	public String getCorreoGuia() {
		return correoGuia;
	}

	public void setCorreoGuia(String correoGuia) {
		this.correoGuia = correoGuia;
	}

	public String getCelularGuia() {
		return celularGuia;
	}

	public void setCelularGuia(String celularGuia) {
		this.celularGuia = celularGuia;
	}

	public Horario getHorarios() {
		return horarios;
	}

	public void setHorarios(Horario horarios) {
		this.horarios = horarios;
	}

	public Puntos getPuntos() {
		return puntos;
	}

	public void setPuntos(Puntos puntos) {
		this.puntos = puntos;
	}

	public LocalDate getFechaGuia() {
		return fechaGuia;
	}

	public void setFechaGuia(LocalDate fechaGuia) {
		this.fechaGuia = fechaGuia;
	}

	public String getPrecioGuia() {
		return precioGuia;
	}

	public void setPrecioGuia(String precioGuia) {
		this.precioGuia = precioGuia;
	}

	public String getRutaPortada() {
		return rutaPortada;
	}

	public void setRutaPortada(String rutaPortada) {
		this.rutaPortada = rutaPortada;
	}

	public List<Lenguaje> getLenguajes() {
		return lenguajes;
	}

	public void setLenguajes(List<Lenguaje> lenguajes) {
		this.lenguajes = lenguajes;
	}

	public MultipartFile getPortada() {
		return portada;
	}

	public void setPortada(MultipartFile portada) {
		this.portada = portada;
	}
	
}
