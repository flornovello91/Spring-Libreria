package com.mza.libreria.repositorios;

import com.mza.libreria.entidades.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioEditorial extends JpaRepository<Editorial,String> {

}
