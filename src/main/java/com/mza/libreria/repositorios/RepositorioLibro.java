 package com.mza.libreria.repositorios;

import com.mza.libreria.entidades.Libro;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioLibro extends JpaRepository <Libro,String>{
    
    @Query("SELECT s FROM Libro s WHERE s.titulo = tituloLibro")
    Optional<Libro> findByTitulo(String tituloLibro);
}
