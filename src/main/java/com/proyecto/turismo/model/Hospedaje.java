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
@Table(name="tb_hospedaje")
public class Hospedaje {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_hospedaje")
	private Integer idhospedaje;
	
	@NotBlank
	//@Column(name="des_hospedaje")
	@Column(columnDefinition = "TEXT")
	private String desHospedaje;
	
	@NotBlank
	//@Column(name="nom_hospedaje")
	private String nomHospedaje;
	
	
	@NotBlank
	//@Column(name="dir_hospedaje")
	private String dirHospedaje;
	
	//AGREGUE PARA COMBO HORARIOS
			@NotNull
			@ManyToOne
			@JoinColumn(name="id_horario")
			private Horario horarios;
		
		@NotBlank
		//@Column(name="precio_hospedaje")
		private String precioHospedaje;
		
		@NotNull
		@DateTimeFormat(iso = ISO.DATE)
		//@Column(name="fecha_hospedaje")
		private LocalDate fechaHospedaje;
		
		//AGREGUE PARA COMBO PUNTOS
				@NotNull
				@ManyToOne
				@JoinColumn(name="id_punto")
				private Puntos puntos;
		
		@NotBlank
		private String youtubeTrailerId;
		
		private String rutaPortada;
		
		@NotEmpty
		@ManyToMany(fetch = FetchType.LAZY)
		@JoinTable(name = "servicio_hospedaje", joinColumns = @JoinColumn(name = "id_hospedaje"), inverseJoinColumns = @JoinColumn(name = "id_servicio"))
		private List<Servicio> servicios;
		
		
		@Transient
		private MultipartFile portada;
		
		public Hospedaje() {
			
		}

		public Hospedaje(@NotBlank String desHospedaje, @NotBlank String nomHospedaje, @NotBlank String dirHospedaje,
				@NotNull Horario horarios, @NotBlank String precioHospedaje, @NotNull LocalDate fechaHospedaje,
				@NotNull Puntos puntos, @NotBlank String youtubeTrailerId, String rutaPortada,
				@NotEmpty List<Servicio> servicios, MultipartFile portada) {
			super();
			this.desHospedaje = desHospedaje;
			this.nomHospedaje = nomHospedaje;
			this.dirHospedaje = dirHospedaje;
			this.horarios = horarios;
			this.precioHospedaje = precioHospedaje;
			this.fechaHospedaje = fechaHospedaje;
			this.puntos = puntos;
			this.youtubeTrailerId = youtubeTrailerId;
			this.rutaPortada = rutaPortada;
			this.servicios = servicios;
			this.portada = portada;
		}

		public Hospedaje(Integer idhospedaje, @NotBlank String desHospedaje, @NotBlank String nomHospedaje,
				@NotBlank String dirHospedaje, @NotNull Horario horarios, @NotBlank String precioHospedaje,
				@NotNull LocalDate fechaHospedaje, @NotNull Puntos puntos, @NotBlank String youtubeTrailerId,
				String rutaPortada, @NotEmpty List<Servicio> servicios, MultipartFile portada) {
			super();
			this.idhospedaje = idhospedaje;
			this.desHospedaje = desHospedaje;
			this.nomHospedaje = nomHospedaje;
			this.dirHospedaje = dirHospedaje;
			this.horarios = horarios;
			this.precioHospedaje = precioHospedaje;
			this.fechaHospedaje = fechaHospedaje;
			this.puntos = puntos;
			this.youtubeTrailerId = youtubeTrailerId;
			this.rutaPortada = rutaPortada;
			this.servicios = servicios;
			this.portada = portada;
		}

		public Integer getIdhospedaje() {
			return idhospedaje;
		}

		public void setIdhospedaje(Integer idhospedaje) {
			this.idhospedaje = idhospedaje;
		}

		public String getDesHospedaje() {
			return desHospedaje;
		}

		public void setDesHospedaje(String desHospedaje) {
			this.desHospedaje = desHospedaje;
		}

		public String getNomHospedaje() {
			return nomHospedaje;
		}

		public void setNomHospedaje(String nomHospedaje) {
			this.nomHospedaje = nomHospedaje;
		}

		public String getDirHospedaje() {
			return dirHospedaje;
		}

		public void setDirHospedaje(String dirHospedaje) {
			this.dirHospedaje = dirHospedaje;
		}

		public Horario getHorarios() {
			return horarios;
		}

		public void setHorarios(Horario horarios) {
			this.horarios = horarios;
		}

		public String getPrecioHospedaje() {
			return precioHospedaje;
		}

		public void setPrecioHospedaje(String precioHospedaje) {
			this.precioHospedaje = precioHospedaje;
		}

		public LocalDate getFechaHospedaje() {
			return fechaHospedaje;
		}

		public void setFechaHospedaje(LocalDate fechaHospedaje) {
			this.fechaHospedaje = fechaHospedaje;
		}

		public Puntos getPuntos() {
			return puntos;
		}

		public void setPuntos(Puntos puntos) {
			this.puntos = puntos;
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

		public List<Servicio> getServicios() {
			return servicios;
		}

		public void setServicios(List<Servicio> servicios) {
			this.servicios = servicios;
		}

		public MultipartFile getPortada() {
			return portada;
		}

		public void setPortada(MultipartFile portada) {
			this.portada = portada;
		}
	
}
