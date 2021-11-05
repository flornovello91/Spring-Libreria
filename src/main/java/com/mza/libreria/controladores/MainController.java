package com.mza.libreria.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//ESTO INDICA QUE LA CLASE ES UN CONTROLER
@Controller 

//ESTO LO QUE NOS HACE ES MOSTRARNOS EL CAMINO QUE SEGUIRÁ LA URL PARA ESTA VISTA Y TODO LAS ACCIONES LLEVADAS 
//A CABO EN LA MISMA. EN ESTE CASO 
@RequestMapping("/")  

public class MainController {
    
    @GetMapping("/")              //cuando el URL este en localhost:8080/ se va a mostrar mi pagina principal
    public String index(){
        return "index";
    }
}
