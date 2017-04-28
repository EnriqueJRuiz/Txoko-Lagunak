package org.lagunak.txoko.controller;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.lagunak.txoko.controller.pojo.Mensaje;
import org.lagunak.txoko.controller.pojo.MensajeType;
import org.lagunak.txoko.persisence.Categoria;
import org.lagunak.txoko.service.interfaces.CategoriaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	//private ServletContext servletContext; //por si hay que subir archivos
	private static final Logger LOGGER = LoggerFactory.getLogger(CategoriaController.class);
	
	@Autowired
	private CategoriaService caS;
	
	/*@Resource(name = "articuloValidator")
	ArticuloValidator validator;*/
	
	ModelAndView mav = null;
	
	@InitBinder("articulo")
	public void initBinder(WebDataBinder binder,Locale locale){
		/*DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, locale);
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));*/
		//binder.addValidators(validator);
	}
	
	@InitBinder("fichero")
	public void initBinder(WebDataBinder binder) {
		//binder.addValidators(new FileValidator());
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String getAll(Model model){
		List<Categoria> categorias = caS.getAll();
		LOGGER.info("tamaño:" + categorias.size());
		model.addAttribute("listadoCategorias", categorias);
		return "categorias";
	}
	
	@RequestMapping("/{codigo}")
	public String getById(@PathVariable("codigo") long codigo, Model model){
		model.addAttribute("categoria",caS.getById(codigo));
		return "categoria";
	}
	

	@RequestMapping(value = "/editArticulo/{codigocategoria}", method = RequestMethod.GET)
	public ModelAndView editArticulo(@PathVariable("codigocategoria") long codigocategoria) {
		mav = new ModelAndView("articuloform");
		Categoria categoria = caS.getById(codigocategoria);
		mav.addObject("categoria", categoria);
		return mav;
		
	}
	@RequestMapping(value="addCategoria")
	public ModelAndView addArticulo() {
		mav = new ModelAndView("categoriaform");
		Categoria categoria = new Categoria();
		categoria.setActivo(true);
		mav.addObject("categoria", categoria);
  		return mav;
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String saveCategoria(@Valid @RequestPart("fichero") MultipartFile file,
			@ModelAttribute("articulo")@Valid Categoria categoria,BindingResult bindingResult, ModelMap model,
			RedirectAttributes redirectMap) throws IOException {
		String destino = "";
		String txt="";
		Mensaje mensaje = null;
		if (bindingResult.hasErrors()) {
			
			LOGGER.info("curso tiene errores");
			txt = "Los datos de formulario contienen errores";
			mensaje = new Mensaje(MensajeType.MSG_TYPE_DANGER);
			
			destino = "categoriaform";
		}else{
			destino = "redirect:/categorias";
			
			if(categoria.getCodigo() > Categoria.CODIGO_NULO){
				
				LOGGER.info(categoria.toString());
				try {
					caS.update(categoria);
					txt = "El curso se ha actualizado correctamente.";
					mensaje = new Mensaje(MensajeType.MSG_TYPE_SUCCESS);
				} catch (Exception e) {
					LOGGER.info("Se ha lanzadado una excepcion update. " + e.toString());
					mensaje = new Mensaje(MensajeType.MSG_TYPE_DANGER);
					txt = "Ha habido problemas en la actualización.";
				}
				
				
			}else{
				
				LOGGER.info(categoria.toString());
				try {
					caS.create(categoria);
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
	
	@RequestMapping(value = "/deleteCurso/{codigocategoria}")
	public String deleteCurso(@PathVariable("codigocategoria") long codigocategoria, RedirectAttributes redirectMap) {
		String txt="";
		Mensaje mensaje = null;
		
		try {
			caS.delete(codigocategoria);
			txt = "El curso se ha borrado correctamente.";
			mensaje = new Mensaje(MensajeType.MSG_TYPE_SUCCESS);
		} catch (Exception e) {
			LOGGER.info("Se ha lanzadado una excepcion Delete. " + e.toString());
			mensaje = new Mensaje(MensajeType.MSG_TYPE_DANGER);
			txt = "Ha habido problemas al borrar.";
		} 
		
		mensaje.setMsg(txt);

		redirectMap.addFlashAttribute("mensaje", mensaje);
		
		return "redirect:/categorias";
	}	
	
}
