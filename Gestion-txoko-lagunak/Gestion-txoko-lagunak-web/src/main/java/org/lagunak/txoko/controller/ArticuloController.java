package org.lagunak.txoko.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.lagunak.txoko.controller.pojo.Mensaje;
import org.lagunak.txoko.controller.pojo.MensajeType;
import org.lagunak.txoko.persisence.Articulo;
import org.lagunak.txoko.service.interfaces.ArticuloService;
import org.lagunak.txoko.service.interfaces.CategoriaService;
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
@RequestMapping("/articulos")
public class ArticuloController {

	@Autowired
	private ServletContext servletContext;
	private static final Logger LOGGER = LoggerFactory.getLogger(ArticuloController.class);
	
	@Autowired
	private ArticuloService aS;
	
	@Autowired
	private UsuarioService uS;
	
	@Autowired
	private ComentarioService coS;
	
	@Autowired
	private CategoriaService caS;
	
	/*@Resource(name = "articuloValidator")
	ArticuloValidator validator;*/
	
	ModelAndView mav = null;
	
	@InitBinder("articulo")
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
		List<Articulo> articulos = aS.getAll();
		LOGGER.info("tamaño:" + articulos.size());
		model.addAttribute("listadoArticulos", articulos);
		return "articulos";
	}
	
	@RequestMapping("/{codigo}")
	public String getById(@PathVariable("codigo") long codigo, Model model){
		model.addAttribute("articulo",aS.getById(codigo));
		return "articulo";
	}
	

	@RequestMapping(value = "/editArticulo/{codigoarticulo}", method = RequestMethod.GET)
	public ModelAndView editArticulo(@PathVariable("codigoarticulo") long codigoarticulo) {
		mav = new ModelAndView("articuloform");
		Articulo articulo = aS.getById(codigoarticulo);
		mav.addObject("articulo", articulo);
		return mav;
		
	}
	@RequestMapping(value="addArticulo")
	public ModelAndView addArticulo() {
		mav = new ModelAndView("articuloform");
		Articulo articulo = new Articulo();
		articulo.setActivo(true);
		mav.addObject("articulo", articulo);
  		return mav;
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String saveArticulo(@Valid @RequestPart("fichero") MultipartFile file,
			@ModelAttribute("articulo")@Valid Articulo articulo,BindingResult bindingResult, ModelMap model,
			RedirectAttributes redirectMap) throws IOException {
		String destino = "";
		String txt="";
		Mensaje mensaje = null;
		if (bindingResult.hasErrors()) {
			
			LOGGER.info("curso tiene errores");
			txt = "Los datos de formulario contienen errores";
			mensaje = new Mensaje(MensajeType.MSG_TYPE_DANGER);
			
			destino = "articuloform";
		}else{
			destino = "redirect:/articulos";
			if(file.getOriginalFilename().isEmpty() == false){
				InputStream in = file.getInputStream();
				String root= File.separator+ "resources" + File.separator + "docs" +File.separator;
				String ruta = servletContext.getRealPath(root);
				File destination = new File(ruta + file.getOriginalFilename());
				
					FileUtils.copyInputStreamToFile(in,  destination);
				
				LOGGER.info(ruta);
				
				articulo.setImagen(file.getOriginalFilename());
				
				LOGGER.info(file.getOriginalFilename());
			}
			if(articulo.getCodigo() > Articulo.CODIGO_NULO){
				
				LOGGER.info(articulo.toString());
				try {
					aS.update(articulo);
					txt = "El curso se ha actualizado correctamente.";
					mensaje = new Mensaje(MensajeType.MSG_TYPE_SUCCESS);
				} catch (Exception e) {
					LOGGER.info("Se ha lanzadado una excepcion update. " + e.toString());
					mensaje = new Mensaje(MensajeType.MSG_TYPE_DANGER);
					txt = "Ha habido problemas en la actualización.";
				}
				
				
			}else{
				
				LOGGER.info(articulo.toString());
				try {
					aS.create(articulo);
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
	
	@RequestMapping(value = "/deleteCurso/{codigoarticulo}")
	public String deleteCurso(@PathVariable("codigoarticulo") long codigoarticulo, RedirectAttributes redirectMap) {
		String txt="";
		Mensaje mensaje = null;
		
		try {
			aS.delete(codigoarticulo);
			txt = "El curso se ha borrado correctamente.";
			mensaje = new Mensaje(MensajeType.MSG_TYPE_SUCCESS);
		} catch (Exception e) {
			LOGGER.info("Se ha lanzadado una excepcion Delete. " + e.toString());
			mensaje = new Mensaje(MensajeType.MSG_TYPE_DANGER);
			txt = "Ha habido problemas al borrar.";
		} 
		
		mensaje.setMsg(txt);

		redirectMap.addFlashAttribute("mensaje", mensaje);
		
		return "redirect:/articulos";
	}	
	
}
