package com.mza.libreria.repositorios;

import com.mza.libreria.entidades.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioAutor extends JpaRepository <Autor,String> {
    
}
