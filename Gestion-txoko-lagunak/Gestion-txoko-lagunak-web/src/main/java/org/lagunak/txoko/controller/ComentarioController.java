package org.lagunak.txoko.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.lagunak.txoko.controller.pojo.Mensaje;
import org.lagunak.txoko.controller.pojo.MensajeType;
import org.lagunak.txoko.persisence.Articulo;
import org.lagunak.txoko.persisence.Comentario;
import org.lagunak.txoko.service.interfaces.ArticuloService;
import org.lagunak.txoko.service.interfaces.ComentarioService;
import org.lagunak.txoko.service.interfaces.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/comentarios")
public class ComentarioController {

	@Autowired
	//private ServletContext servletContext;
	private static final Logger LOGGER = LoggerFactory.getLogger(ComentarioController.class);
	
	@Autowired
	private ArticuloService aS;
	
	@Autowired
	private UsuarioService uS;
	
	@Autowired
	private ComentarioService coS;

	/*@Resource(name = "comentarioValidator")
	ComentarioValidator validator;*/
	
	ModelAndView mav = null;
	
	@InitBinder("comentario")
	public void initBinder(WebDataBinder binder,Locale locale){
		DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, locale);
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		//binder.addValidators(validator);
	}
	
	@InitBinder("fichero")
	public void initBinder(WebDataBinder binder) {
		//binder.addValidators(new FileValidator());
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String getAll(Model model){
		List<Comentario> comentarios = coS.getAll();
		LOGGER.info("tamaño:" + comentarios.size());
		model.addAttribute("listadoComentarios", comentarios);
		return "comentarios";
	}
	
	@RequestMapping("/{codigo}")
	public String getById(@PathVariable("codigo") long codigo, Model model){
		model.addAttribute("comentario",coS.getById(codigo));
		return "comentario";
	}
	

	@RequestMapping(value = "/editComentario/{codigocomentario}", method = RequestMethod.GET)
	public ModelAndView editComentario(@PathVariable("codigocomentario") long codigocomentario) {
		mav = new ModelAndView("comentarioform");
		Comentario comentario = coS.getById(codigocomentario);
		mav.addObject("comentario", comentario);
		return mav;
		
	}
	@RequestMapping(value="addComentario")
	public ModelAndView addComentario() {
		mav = new ModelAndView("comentarioform");
		Comentario comentario = new Comentario();
		comentario.setActivo(true);
		mav.addObject("comentario", comentario);
  		return mav;
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String saveArticulo(@Valid @RequestPart("fichero") MultipartFile file,
			@ModelAttribute("comentario")@Valid Comentario comentario,BindingResult bindingResult, ModelMap model,
			RedirectAttributes redirectMap) throws IOException {
		String destino = "";
		String txt="";
		Mensaje mensaje = null;
		if (bindingResult.hasErrors()) {
			
			LOGGER.info("curso tiene errores");
			txt = "Los datos de formulario contienen errores";
			mensaje = new Mensaje(MensajeType.MSG_TYPE_DANGER);
			
			destino = "comentarioform";
		}else{
			destino = "redirect:/comentarios";
			
			if(comentario.getCodigo() > Articulo.CODIGO_NULO){
				
				LOGGER.info(comentario.toString());
				try {
					coS.update(comentario);
					txt = "El curso se ha actualizado correctamente.";
					mensaje = new Mensaje(MensajeType.MSG_TYPE_SUCCESS);
				} catch (Exception e) {
					LOGGER.info("Se ha lanzadado una excepcion update. " + e.toString());
					mensaje = new Mensaje(MensajeType.MSG_TYPE_DANGER);
					txt = "Ha habido problemas en la actualización.";
				}
				
				
			}else{
				
				LOGGER.info(comentario.toString());
				try {
					coS.create(comentario);
					txt = "El curso se ha creado correctamente.";
					mensaje = new Mensaje(MensajeType.MSG_TYPE_SUCCESS);
				} catch (Exception e) {
					LOGGER.info("Se ha lanzadado una excepcion create. " + e.toString());
					mensaje = new Mensaje(MensajeType.MSG_TYPE_DANGER);
					txt = "Ha habido problemas en la creación del curso.";
					
				}
			}
			mensaje.setMsg(txt);
			redirectMap.addFlashAttribute("mensaje", mensaje);
			
		}
		return destino;
		
	}
	
	@RequestMapping(value = "/deleteComentario/{codigocomentario}")
	public String deleteComentario(@PathVariable("codigocomentario") long codigocomentario, RedirectAttributes redirectMap) {
		String txt="";
		Mensaje mensaje = null;
		
		try {
			coS.delete(codigocomentario);
			txt = "El curso se ha borrado correctamente.";
			mensaje = new Mensaje(MensajeType.MSG_TYPE_SUCCESS);
		} catch (Exception e) {
			LOGGER.info("Se ha lanzadado una excepcion Delete. " + e.toString());
			mensaje = new Mensaje(MensajeType.MSG_TYPE_DANGER);
			txt = "Ha habido problemas al borrar.";
		} 
		
		mensaje.setMsg(txt);

		redirectMap.addFlashAttribute("mensaje", mensaje);
		
		return "redirect:/comentarios";
	}	
	
}
	

