package com.mza.libreria.servicios;

import com.mza.libreria.entidades.Editorial;
import com.mza.libreria.repositorios.RepositorioEditorial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditorialService {
    @Autowired
    RepositorioEditorial repositorioEditorial;
    
    public Editorial guardarEditorial (String Editorial){
        Editorial editorial = new Editorial ();
        editorial.setNombre(Editorial);
        editorial.setAlta(Boolean.TRUE);
        return repositorioEditorial.save(editorial);
    }
}
