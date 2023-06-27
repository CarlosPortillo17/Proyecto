package com.proyecto.turismo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.proyecto.turismo.model.Usuario;
import com.proyecto.turismo.model.Guia;
import com.proyecto.turismo.model.Hospedaje;
import com.proyecto.turismo.model.LugarTuristico;
import com.proyecto.turismo.repository.GuiaRepositorio;
import com.proyecto.turismo.repository.HospedajeRepositorio;
import com.proyecto.turismo.repository.LugarTuristicoRepositorio;


@Controller
@RequestMapping("")
public class HomeControlador {
	@Autowired
	private LugarTuristicoRepositorio lugarTuristicoRepositorio;
	
	@Autowired
	private GuiaRepositorio guiaRepositorio;
	
	@Autowired
	private HospedajeRepositorio hospedajeRepositorio;

	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "login";
	}
	
	@PostMapping("/login")
	public ModelAndView ingresar(@ModelAttribute Usuario usuario, Model model) {
		return new ModelAndView("index");
	}
	
	/*@PostMapping("")
	public ModelAndView ingresar2(@ModelAttribute Usuario usuario, Model model) {
		return new ModelAndView("login");
	}*/
	
	
	@GetMapping("")
	public ModelAndView verPaginaDeInicio() {
		List<LugarTuristico> ultimosLugaresTuristicos = lugarTuristicoRepositorio.findAll(PageRequest.of(0,4,Sort.by("fechaTuristico").descending())).toList();
		List<Guia> ultimosGuias = guiaRepositorio.findAll(PageRequest.of(0,4,Sort.by("fechaGuia").descending())).toList();
		List<Hospedaje> ultimosHospedajes = hospedajeRepositorio.findAll(PageRequest.of(0,4,Sort.by("fechaHospedaje").descending())).toList();
		return new ModelAndView("index")
				      .addObject("ultimosLugaresTuristicos", ultimosLugaresTuristicos)
					  .addObject("ultimosGuias", ultimosGuias)
					  .addObject("ultimosHospedajes", ultimosHospedajes);
	}

	@GetMapping("/lugaresTuristicos")
	public ModelAndView listarLugaresTuristicos(@PageableDefault(sort = "fechaTuristico",direction = Sort.Direction.DESC) Pageable pageable) {
		Page<LugarTuristico> lugaresTuristicos = lugarTuristicoRepositorio.findAll(pageable);
		return new ModelAndView("lugaresTuristicos")
				        .addObject("lugaresTuristicos",lugaresTuristicos);
	}
	
	@GetMapping("/lugaresTuristicos/{idlugTuristico}")
	public ModelAndView mostrarDetallesDeLugaresTuristicos(@PathVariable Integer idlugTuristico) {
		LugarTuristico lugarTuristico = lugarTuristicoRepositorio.getOne(idlugTuristico);
		return new ModelAndView("lugarTuristico").addObject("lugarTuristico",lugarTuristico);
	}
	
	
	
	/*SEPARACION DE GUIAS*/
	
	
	@GetMapping("/guias")
	public ModelAndView listarGuias(@PageableDefault(sort = "fechaGuia",direction = Sort.Direction.DESC) Pageable pageable) {
		Page<Guia> guias = guiaRepositorio.findAll(pageable);
		return new ModelAndView("guias")
				        .addObject("guias",guias);
	}
	
	@GetMapping("/guias/{idguia}")
	public ModelAndView mostrarDetallesDeGuias(@PathVariable Integer idguia) {
		Guia guia = guiaRepositorio.getOne(idguia);
		return new ModelAndView("guia").addObject("guia",guia);
	}
	
	
	/*SEPARACION DE HOSPEDAJES*/
	
	@GetMapping("/hospedajes")
	public ModelAndView listarHospedajes(@PageableDefault(sort = "fechaHospedaje",direction = Sort.Direction.DESC) Pageable pageable) {
		Page<Hospedaje> hospedajes = hospedajeRepositorio.findAll(pageable);
		return new ModelAndView("hospedajes")
				        .addObject("hospedajes",hospedajes);
	}
	
	@GetMapping("/hospedajes/{idhospedaje}")
	public ModelAndView mostrarDetallesDeHospedajes(@PathVariable Integer idhospedaje) {
		Hospedaje hospedaje = hospedajeRepositorio.getOne(idhospedaje);
		return new ModelAndView("hospedaje").addObject("hospedaje",hospedaje);
	}
	
	
}
