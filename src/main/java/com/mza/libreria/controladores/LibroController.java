package com.mza.libreria.controladores;

import com.mza.libreria.MyException.MyException;
import com.mza.libreria.servicios.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/libro")  //localhost:8080/libro
public class LibroController {
    
    @Autowired
    private LibroService libroService;
    
    @GetMapping("registro")       //localhost:8080/perro/registro
    public String formularioIngreso(){
        return ("formularioIngreso");
    }
    
    @PostMapping("/registro")
    public String guardarLibro(@RequestParam Integer isbn,@RequestParam String titulo,@RequestParam String Autor,@RequestParam String Editorial,@RequestParam Integer anio) throws MyException{
        
        try{
            libroService.guardarLibro(isbn,titulo,Autor,Editorial,anio);
            return ("formularioIngreso");
        }catch (MyException e){
            //poner el model aca
            return ("formularioIngreso");
        }
    }
}
