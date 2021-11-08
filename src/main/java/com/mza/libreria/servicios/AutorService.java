package com.mza.libreria.servicios;

import com.mza.libreria.entidades.Autor;
import com.mza.libreria.repositorios.RepositorioAutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorService {
    @Autowired
    private RepositorioAutor repositorioAutor;
    
    public Autor guardarAutor(String Autor){
        Autor autor = new Autor ();
        autor.setNombre(Autor);
        autor.setAlta(Boolean.TRUE);
        return repositorioAutor.save(autor);     
    }
}
