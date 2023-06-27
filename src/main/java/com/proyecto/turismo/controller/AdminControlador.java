package com.proyecto.turismo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.proyecto.turismo.model.Ciudad;
import com.proyecto.turismo.model.Guia;
import com.proyecto.turismo.model.Horario;
import com.proyecto.turismo.model.Hospedaje;
import com.proyecto.turismo.model.Lenguaje;
import com.proyecto.turismo.model.LugarTuristico;
import com.proyecto.turismo.model.Nacionalidad;
import com.proyecto.turismo.model.Puntos;
import com.proyecto.turismo.model.Servicio;
import com.proyecto.turismo.model.Usuario;
import com.proyecto.turismo.repository.CiudadRepositorio;
import com.proyecto.turismo.repository.GuiaRepositorio;
import com.proyecto.turismo.repository.HorarioRepositorio;
import com.proyecto.turismo.repository.HospedajeRepositorio;
import com.proyecto.turismo.repository.LenguajeRepositorio;
import com.proyecto.turismo.repository.LugarTuristicoRepositorio;
import com.proyecto.turismo.repository.NacionalidadRepositorio;
import com.proyecto.turismo.repository.PuntosRepositorio;
import com.proyecto.turismo.repository.ServicioRepositorio;
import com.proyecto.turismo.servicio.AlmacenServicioImpl;

@Controller
@RequestMapping("/admin")
public class AdminControlador {
	
	@Autowired
	private LugarTuristicoRepositorio lugarTuristicoRepositorio;
	@Autowired
	private CiudadRepositorio ciudadRepositorio;

	@Autowired
	private AlmacenServicioImpl servicio;
	
	@Autowired
	private GuiaRepositorio guiaRepositorio;
	
	@Autowired
	private LenguajeRepositorio lenguajeRepositorio;
	
	@Autowired
	private NacionalidadRepositorio nacionalidadRepositorio;
	
	@Autowired
	private HorarioRepositorio horarioRepositorio;
	
	@Autowired
	private PuntosRepositorio puntosRepositorio;
	
	@Autowired
	private HospedajeRepositorio hospedajeRepositorio;

	@Autowired
	private ServicioRepositorio servicioRepositorio;
	
	
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "login";
	}
	
	@PostMapping("/login")
	public ModelAndView ingresar(@ModelAttribute Usuario usuario, Model model) {
		return new ModelAndView("admin/nueva-lugarTuristico");
	}
	
	@GetMapping("")
	public ModelAndView verPaginaDeInicio(@PageableDefault(sort = "ciudades", size = 5) Pageable pageable) {
		Page<LugarTuristico> lugaresTuristicos = lugarTuristicoRepositorio.findAll(pageable);
		return new ModelAndView("admin/index").addObject("lugaresTuristicos", lugaresTuristicos);
	}

	@GetMapping("/lugaresTuristicos/nuevo")
	public ModelAndView mostrarFormularioDeNuevoLugarTuristico() {
		List<Ciudad> ciudades = ciudadRepositorio.findAll(Sort.by("ciudad"));
		List<Horario> horarios = horarioRepositorio.findAll(Sort.by("horario"));
		return new ModelAndView("admin/nueva-lugarTuristico")
				.addObject("lugarTuristico", new LugarTuristico())
				.addObject("ciudades",ciudades)
				.addObject("horarios",horarios);
	}
	
	@PostMapping("/lugaresTuristicos/nuevo")
	public ModelAndView registrarLugarTuristico(@Validated LugarTuristico lugarTuristico,BindingResult bindingResult) {
		if(bindingResult.hasErrors() || lugarTuristico.getPortada().isEmpty()) {
			if(lugarTuristico.getPortada().isEmpty()) {
				bindingResult.rejectValue("portada","MultipartNotEmpty");
			}
			
			List<Ciudad> ciudades = ciudadRepositorio.findAll(Sort.by("ciudad"));
			List<Horario> horarios = horarioRepositorio.findAll(Sort.by("horario"));
			return new ModelAndView("admin/nueva-lugarTuristico")
					.addObject("lugarTuristico",lugarTuristico)
					.addObject("ciudades",ciudades)
					.addObject("horarios",horarios);
		}
		
		String rutaPortada = servicio.almacenarArchivo(lugarTuristico.getPortada());
		lugarTuristico.setRutaPortada(rutaPortada);
		
		lugarTuristicoRepositorio.save(lugarTuristico);
		return new ModelAndView("redirect:/admin/lugaresTuristicos");
	}
	
	@GetMapping("/lugaresTuristicos/{idlugTuristico}/editar")
	public ModelAndView mostrarFormilarioDeEditarLugarTuristico(@PathVariable Integer idlugTuristico) {
		LugarTuristico lugarTuristico = lugarTuristicoRepositorio.getOne(idlugTuristico);
		List<Ciudad> ciudades = ciudadRepositorio.findAll(Sort.by("ciudad"));
		List<Horario> horarios = horarioRepositorio.findAll(Sort.by("horario"));
		
		return new ModelAndView("admin/editar-lugarTuristico")
				.addObject("lugarTuristico",lugarTuristico)
				.addObject("ciudades",ciudades)
				.addObject("horarios",horarios);
	}
	
	@PostMapping("/lugaresTuristicos/{idlugTuristico}/editar")
	public ModelAndView actualizarLugarTuristico(@PathVariable Integer idlugTuristico,@Validated LugarTuristico lugarTuristico,BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			List<Ciudad> ciudades = ciudadRepositorio.findAll(Sort.by("ciudad"));
			List<Horario> horarios = horarioRepositorio.findAll(Sort.by("horario"));
			return new ModelAndView("admin/editar-lugarTuristico")
					.addObject("lugarTuristico",lugarTuristico)
					.addObject("ciudades",ciudades)
					.addObject("horarios",horarios);
		}
		
		LugarTuristico lugarTuristicoDB = lugarTuristicoRepositorio.getOne(idlugTuristico);
		lugarTuristicoDB.setDesTuristico(lugarTuristico.getDesTuristico());
		lugarTuristicoDB.setNomTuristico(lugarTuristico.getNomTuristico());
		lugarTuristicoDB.setHorarios(lugarTuristico.getHorarios());
		lugarTuristicoDB.setFechaTuristico(lugarTuristico.getFechaTuristico());
		lugarTuristicoDB.setPrecioTuristico(lugarTuristico.getPrecioTuristico());
		lugarTuristicoDB.setYoutubeTrailerId(lugarTuristico.getYoutubeTrailerId());
		lugarTuristicoDB.setCiudades(lugarTuristico.getCiudades());
		
		
		if(!lugarTuristico.getPortada().isEmpty()) {
			servicio.eliminarArchivo(lugarTuristicoDB.getRutaPortada());
			String rutaPortada = servicio.almacenarArchivo(lugarTuristico.getPortada());
			lugarTuristicoDB.setRutaPortada(rutaPortada);
		}
		
		lugarTuristicoRepositorio.save(lugarTuristicoDB);
		return new ModelAndView("redirect:/admin/lugaresTuristicos");
	}
	
	@PostMapping("/lugaresTuristicos/{idlugTuristico}/eliminar")
	public String eliminarLugarTuristico(@PathVariable Integer idlugTuristico) {
		LugarTuristico lugarTuristico = lugarTuristicoRepositorio.getOne(idlugTuristico);
		lugarTuristicoRepositorio.delete(lugarTuristico);
		servicio.eliminarArchivo(lugarTuristico.getRutaPortada());
		
		return "redirect:/admin";
	}
	
	@GetMapping("/lugaresTuristicos/buscar")
	public ModelAndView buscarLugaresTuristicosPorCiudad(@RequestParam("ciudad") String ciudad,
	                                           @PageableDefault(sort = "ciudades", size = 5) Pageable pageable) {
	    Page<LugarTuristico> lugaresTuristicos = lugarTuristicoRepositorio.findByCiudades_CiudadContainingIgnoreCase(ciudad, pageable);
	    return new ModelAndView("admin/index").addObject("lugaresTuristicos", lugaresTuristicos);
	}
	
	
	
	/******************* PARA EL ADMIN GUIAS *********************/
	
	@GetMapping("/guias")
	public ModelAndView verPaginaDeGuia(@PageableDefault(sort = "lenguajes", size = 5) Pageable pageable) {
		Page<Guia> guias = guiaRepositorio.findAll(pageable);
		return new ModelAndView("admin/index-guia").addObject("guias", guias);
	}

	
	
	@GetMapping("/guias/nuevo")
	public ModelAndView mostrarFormularioDeNuevoGuia() {
		List<Lenguaje> lenguajes = lenguajeRepositorio.findAll(Sort.by("lenguaje"));
		List<Nacionalidad> nacionalidades = nacionalidadRepositorio.findAll(Sort.by("nacionalidad"));
		List<Horario> horarios = horarioRepositorio.findAll(Sort.by("horario"));
		List<Puntos> puntos = puntosRepositorio.findAll(Sort.by("punto"));
		// ESTE ES PARA EL TEMPLATE EN TODOS LOS RETURN
		return new ModelAndView("admin/nueva-guia")
				.addObject("guia", new Guia())
				.addObject("lenguajes",lenguajes)
				.addObject("nacionalidades",nacionalidades)
				.addObject("horarios",horarios)
				.addObject("puntos",puntos);
	}
	
	@PostMapping("/guias/nuevo")
	public ModelAndView registrarGuia(@Validated Guia guia,BindingResult bindingResult) {
		if(bindingResult.hasErrors() || guia.getPortada().isEmpty()) {
			if(guia.getPortada().isEmpty()) {
				bindingResult.rejectValue("portada","MultipartNotEmpty");
			}
			
			List<Lenguaje> lenguajes = lenguajeRepositorio.findAll(Sort.by("lenguaje"));
			List<Nacionalidad> nacionalidades = nacionalidadRepositorio.findAll(Sort.by("nacionalidad"));
			List<Horario> horarios = horarioRepositorio.findAll(Sort.by("horario"));
			List<Puntos> puntos = puntosRepositorio.findAll(Sort.by("punto"));
			return new ModelAndView("admin/nueva-guia")
					.addObject("guia",guia)
					.addObject("lenguajes",lenguajes)
					.addObject("nacionalidades",nacionalidades)
					.addObject("horarios",horarios)
					.addObject("puntos",puntos);
		}
		
		String rutaPortada = servicio.almacenarArchivo(guia.getPortada());
		guia.setRutaPortada(rutaPortada);
		
		guiaRepositorio.save(guia);
		return new ModelAndView("redirect:/admin/guias");
	}
	
	@GetMapping("/guias/{idguia}/editar")
	public ModelAndView mostrarFormularioDeEditarGuia(@PathVariable Integer idguia) {
		Guia guia = guiaRepositorio.getOne(idguia);
		List<Lenguaje> lenguajes = lenguajeRepositorio.findAll(Sort.by("lenguaje"));
		List<Nacionalidad> nacionalidades = nacionalidadRepositorio.findAll(Sort.by("nacionalidad"));
		List<Horario> horarios = horarioRepositorio.findAll(Sort.by("horario"));
		List<Puntos> puntos = puntosRepositorio.findAll(Sort.by("punto"));
		
		return new ModelAndView("admin/editar-guia")
				.addObject("guia",guia)
				.addObject("lenguajes",lenguajes)
				.addObject("nacionalidades",nacionalidades)
				.addObject("horarios",horarios)
				.addObject("puntos",puntos);
	}
	
	@PostMapping("/guias/{idguia}/editar")
	public ModelAndView actualizarGuia(@PathVariable Integer idguia,@Validated Guia guia,BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			List<Lenguaje> lenguajes = lenguajeRepositorio.findAll(Sort.by("lenguaje"));
			List<Nacionalidad> nacionalidades = nacionalidadRepositorio.findAll(Sort.by("nacionalidad"));
			List<Horario> horarios = horarioRepositorio.findAll(Sort.by("horario"));
			List<Puntos> puntos = puntosRepositorio.findAll(Sort.by("punto"));
			return new ModelAndView("admin/editar-guia")
					.addObject("guia",guia)
					.addObject("lenguajes",lenguajes)
					.addObject("nacionalidades",nacionalidades)
					.addObject("horarios",horarios)
					.addObject("puntos",puntos);
		}
		
		Guia guiaDB = guiaRepositorio.getOne(idguia);
		guiaDB.setDesGuia(guia.getDesGuia());
		guiaDB.setNomGuia(guia.getNomGuia());
		guiaDB.setApellidoGuia(guia.getApellidoGuia());
		guiaDB.setEdadGuia(guia.getEdadGuia());
		guiaDB.setNacionalidades(guia.getNacionalidades());
		guiaDB.setCorreoGuia(guia.getCorreoGuia());
		guiaDB.setCelularGuia(guia.getCelularGuia());
		guiaDB.setHorarios(guia.getHorarios());
		guiaDB.setPuntos(guia.getPuntos());
		guiaDB.setFechaGuia(guia.getFechaGuia());
		guiaDB.setPrecioGuia(guia.getPrecioGuia());
		guiaDB.setLenguajes(guia.getLenguajes());
		
		
		if(!guia.getPortada().isEmpty()) {
			servicio.eliminarArchivo(guiaDB.getRutaPortada());
			String rutaPortada = servicio.almacenarArchivo(guia.getPortada());
			guiaDB.setRutaPortada(rutaPortada);
		}
		
		guiaRepositorio.save(guiaDB);
		return new ModelAndView("redirect:/admin/guias");
	}
	
	@PostMapping("/guias/{idguia}/eliminar")
	public String eliminarGuia(@PathVariable Integer idguia) {
		Guia guia = guiaRepositorio.getOne(idguia);
		guiaRepositorio.delete(guia);
		servicio.eliminarArchivo(guia.getRutaPortada());
		
		return "redirect:/admin/guias";
	}
	
	
	@GetMapping("/guias/buscar")
	public ModelAndView buscarGuiasPorNacionalidad(@RequestParam("nacionalidad") String nacionalidad,
	                                               @PageableDefault(sort = "lenguajes", size = 5) Pageable pageable) {
	    Page<Guia> guias = guiaRepositorio.findByNacionalidades_NacionalidadContainingIgnoreCase(nacionalidad, pageable);
	    return new ModelAndView("admin/index-guia").addObject("guias", guias);
	}
	

	
	/******************* PARA EL ADMIN HOSPEDAJES *********************/
	
	@GetMapping("/hospedajes")
	public ModelAndView verPaginaDeHospedaje(@PageableDefault(sort = "servicios", size = 5) Pageable pageable) {
	    Page<Hospedaje> hospedajes = hospedajeRepositorio.findAll(pageable);
	    return new ModelAndView("admin/index-hospedaje").addObject("hospedajes", hospedajes);
	}

	
	@GetMapping("/hospedajes/nuevo")
	public ModelAndView mostrarFormularioDeNuevoHospedaje() {
		List<Servicio> servicios = servicioRepositorio.findAll(Sort.by("servicio"));
		List<Horario> horarios = horarioRepositorio.findAll(Sort.by("horario"));
		List<Puntos> puntos = puntosRepositorio.findAll(Sort.by("punto"));
	
		// ESTE ES PARA EL TEMPLATE EN TODOS LOS RETURN
		return new ModelAndView("admin/nuevo-hospedaje")
				.addObject("hospedaje", new Hospedaje())
				.addObject("servicios",servicios)
				.addObject("horarios",horarios)
				.addObject("puntos",puntos);
	}
	
	@PostMapping("/hospedajes/nuevo")
	public ModelAndView registrarHospedaje(@Validated Hospedaje hospedaje,BindingResult bindingResult) {
		if(bindingResult.hasErrors() || hospedaje.getPortada().isEmpty()) {
			if(hospedaje.getPortada().isEmpty()) {
				bindingResult.rejectValue("portada","MultipartNotEmpty");
			}
			
			List<Servicio> servicios = servicioRepositorio.findAll(Sort.by("servicio"));
			List<Horario> horarios = horarioRepositorio.findAll(Sort.by("horario"));
			List<Puntos> puntos = puntosRepositorio.findAll(Sort.by("punto"));
			
			return new ModelAndView("admin/nuevo-hospedaje")
					.addObject("hospedaje",hospedaje)
					.addObject("servicios",servicios)
					.addObject("horarios",horarios)
					.addObject("puntos",puntos);
		}
		
		String rutaPortada = servicio.almacenarArchivo(hospedaje.getPortada());
		hospedaje.setRutaPortada(rutaPortada);
		
		hospedajeRepositorio.save(hospedaje);
		return new ModelAndView("redirect:/admin/hospedajes");
	}
	
	@GetMapping("/hospedajes/{idhospedaje}/editar")
	public ModelAndView mostrarFormularioDeEditarHospedaje(@PathVariable Integer idhospedaje) {
		Hospedaje hospedaje = hospedajeRepositorio.getOne(idhospedaje);
		List<Servicio> servicios = servicioRepositorio.findAll(Sort.by("servicio"));
		List<Horario> horarios = horarioRepositorio.findAll(Sort.by("horario"));
		List<Puntos> puntos = puntosRepositorio.findAll(Sort.by("punto"));
		
		
		return new ModelAndView("admin/editar-hospedaje")
				.addObject("hospedaje",hospedaje)
				.addObject("servicios",servicios)
				.addObject("horarios",horarios)
				.addObject("puntos",puntos);
	}
	
	@PostMapping("/hospedajes/{idhospedaje}/editar")
	public ModelAndView actualizarHospedaje(@PathVariable Integer idhospedaje,@Validated Hospedaje hospedaje,BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			List<Servicio> servicios = servicioRepositorio.findAll(Sort.by("servicio"));
			List<Horario> horarios = horarioRepositorio.findAll(Sort.by("horario"));
			List<Puntos> puntos = puntosRepositorio.findAll(Sort.by("punto"));
			return new ModelAndView("admin/editar-hospedaje")
					.addObject("hospedaje",hospedaje)
					.addObject("servicios",servicios)
					.addObject("horarios",horarios)
					.addObject("puntos",puntos);
		}
		
		Hospedaje hospedajeDB = hospedajeRepositorio.getOne(idhospedaje);
		hospedajeDB.setDesHospedaje(hospedaje.getDesHospedaje());
		hospedajeDB.setNomHospedaje(hospedaje.getNomHospedaje());
		hospedajeDB.setDirHospedaje(hospedaje.getDirHospedaje());
		hospedajeDB.setHorarios(hospedaje.getHorarios());
		hospedajeDB.setPrecioHospedaje(hospedaje.getPrecioHospedaje());
		hospedajeDB.setFechaHospedaje(hospedaje.getFechaHospedaje());
		hospedajeDB.setPuntos(hospedaje.getPuntos());
		hospedajeDB.setYoutubeTrailerId(hospedaje.getYoutubeTrailerId());
		hospedajeDB.setServicios(hospedaje.getServicios());
		
		
		if(!hospedaje.getPortada().isEmpty()) {
			servicio.eliminarArchivo(hospedajeDB.getRutaPortada());
			String rutaPortada = servicio.almacenarArchivo(hospedaje.getPortada());
			hospedajeDB.setRutaPortada(rutaPortada);
		}
		
		hospedajeRepositorio.save(hospedajeDB);
		return new ModelAndView("redirect:/admin/hospedajes");
	}
	
	@PostMapping("/hospedajes/{idhospedaje}/eliminar")
	public String eliminarHospedaje(@PathVariable Integer idhospedaje) {
		Hospedaje hospedaje = hospedajeRepositorio.getOne(idhospedaje);
		hospedajeRepositorio.delete(hospedaje);
		servicio.eliminarArchivo(hospedaje.getRutaPortada());
		
		return "redirect:/admin/hospedajes";
	}
	
	@GetMapping("/hospedajes/buscar")
	public ModelAndView buscarHospedajesPorPuntos(@RequestParam("punto") String punto) {
	    List<Hospedaje> hospedajes = hospedajeRepositorio.findByPuntosPuntoContaining(punto);
	    return new ModelAndView("admin/index-hospedaje").addObject("hospedajes", hospedajes);
	}
	
}
