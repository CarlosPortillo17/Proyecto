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
@Table(name="tb_lugares_turisticos")
public class LugarTuristico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_lugturistico")
	private Integer idlugTuristico;
	
	@NotBlank
	//@Column(name="des_turistico")
	@Column(columnDefinition = "TEXT")
	private String desTuristico;
	
	@NotBlank
	//@Column(name="nombre_turistico")
	private String nomTuristico;
	
	
	//AGREGUE PARA COMBO HORARIOS
		@NotNull
		@ManyToOne
		@JoinColumn(name="id_horario")
		private Horario horarios;
		
	
	@NotNull
	@DateTimeFormat(iso = ISO.DATE)
	//@Column(name="fecha_tour")
	private LocalDate fechaTuristico;
	
	@NotBlank
	//@Column(name="precio_entrada")
	private String precioTuristico;
	
	@NotBlank
	private String youtubeTrailerId;

	private String rutaPortada;

	@NotEmpty
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "ciudad_turistica", joinColumns = @JoinColumn(name = "id_lugturistico"), inverseJoinColumns = @JoinColumn(name = "id_ciudad"))
	private List<Ciudad> ciudades;
	
	@Transient
	private MultipartFile portada;
	
	public LugarTuristico() {
		
	}

	public LugarTuristico(@NotBlank String desTuristico, @NotBlank String nomTuristico, @NotNull Horario horarios,
			@NotNull LocalDate fechaTuristico, @NotBlank String precioTuristico, @NotBlank String youtubeTrailerId,
			String rutaPortada, @NotEmpty List<Ciudad> ciudades, MultipartFile portada) {
		super();
		this.desTuristico = desTuristico;
		this.nomTuristico = nomTuristico;
		this.horarios = horarios;
		this.fechaTuristico = fechaTuristico;
		this.precioTuristico = precioTuristico;
		this.youtubeTrailerId = youtubeTrailerId;
		this.rutaPortada = rutaPortada;
		this.ciudades = ciudades;
		this.portada = portada;
	}

	public LugarTuristico(Integer idlugTuristico, @NotBlank String desTuristico, @NotBlank String nomTuristico,
			@NotNull Horario horarios, @NotNull LocalDate fechaTuristico, @NotBlank String precioTuristico,
			@NotBlank String youtubeTrailerId, String rutaPortada, @NotEmpty List<Ciudad> ciudades,
			MultipartFile portada) {
		super();
		this.idlugTuristico = idlugTuristico;
		this.desTuristico = desTuristico;
		this.nomTuristico = nomTuristico;
		this.horarios = horarios;
		this.fechaTuristico = fechaTuristico;
		this.precioTuristico = precioTuristico;
		this.youtubeTrailerId = youtubeTrailerId;
		this.rutaPortada = rutaPortada;
		this.ciudades = ciudades;
		this.portada = portada;
	}

	public Integer getIdlugTuristico() {
		return idlugTuristico;
	}

	public void setIdlugTuristico(Integer idlugTuristico) {
		this.idlugTuristico = idlugTuristico;
	}

	public String getDesTuristico() {
		return desTuristico;
	}

	public void setDesTuristico(String desTuristico) {
		this.desTuristico = desTuristico;
	}

	public String getNomTuristico() {
		return nomTuristico;
	}

	public void setNomTuristico(String nomTuristico) {
		this.nomTuristico = nomTuristico;
	}

	public Horario getHorarios() {
		return horarios;
	}

	public void setHorarios(Horario horarios) {
		this.horarios = horarios;
	}

	public LocalDate getFechaTuristico() {
		return fechaTuristico;
	}

	public void setFechaTuristico(LocalDate fechaTuristico) {
		this.fechaTuristico = fechaTuristico;
	}

	public String getPrecioTuristico() {
		return precioTuristico;
	}

	public void setPrecioTuristico(String precioTuristico) {
		this.precioTuristico = precioTuristico;
	}

	public String getYoutubeTrailerId() {
		return youtubeTrailerId;
	}

	public void setYoutubeTrailerId(String youtubeTrailerId) {
		this.youtubeTrailerId = youtubeTrailerId;
	}

	public String getRutaPortada() {
		return rutaPortada;
	}

	public void setRutaPortada(String rutaPortada) {
		this.rutaPortada = rutaPortada;
	}

	public List<Ciudad> getCiudades() {
		return ciudades;
	}

	public void setCiudades(List<Ciudad> ciudades) {
		this.ciudades = ciudades;
	}

	public MultipartFile getPortada() {
		return portada;
	}

	public void setPortada(MultipartFile portada) {
		this.portada = portada;
	}
	
}
