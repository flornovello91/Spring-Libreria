package com.mza.libreria.controladores;

import com.mza.libreria.MyException.MyException;
import com.mza.libreria.servicios.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/libro")  //localhost:8080/libro
public class LibroController {
    
    @Autowired
    private LibroService libroService;
    
    @GetMapping("/registro")       //localhost:8080/libro/registro
    public String formularioIngreso(){
        return ("formularioIngreso");
    }
    
    @PostMapping("/registro")
    public String guardarLibro(ModelMap modelo,@RequestParam (required = false) Long isbn,@RequestParam String titulo,@RequestParam String autor,@RequestParam String editorial,@RequestParam (required = false) Integer anio)throws MyException{
        try{
            libroService.guardarLibro(isbn,titulo,autor,editorial,anio);
            modelo.put("exito", "Guardado de manera exitosa!");
            return ("formularioIngreso");
        }catch (MyException e){
            modelo.put("error", "Error en la carga del libro.");
            return ("formularioIngreso");
        }
    }
}

