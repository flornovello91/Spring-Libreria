package com.mza.libreria.servicios;

import com.mza.libreria.MyException.MyException;
import com.mza.libreria.entidades.Libro;
import com.mza.libreria.repositorios.RepositorioLibro;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroService {
    @Autowired
    private RepositorioLibro repositorioLibro;
    
    private AutorService autorService;
    private EditorialService editorialService;
    
    public void guardarLibro (long isbn,String titulo,String Autor,String Editorial,Integer anio) throws MyException{
        
        try{
            validarDatos(isbn,titulo,Autor,Editorial);
            Libro libro = new Libro();
            libro.setIsbn(isbn);
            libro.setTitulo(titulo);
            libro.setAutor(autorService.guardarAutor(Autor));
            libro.setEditorial(editorialService.guardarEditorial(Editorial));
            libro.setEjemplares(1000);
            int ejemplaresPrestados = (int) (Math.random() * 151);
            libro.setEjemplaresPrestados(ejemplaresPrestados);
            libro.setEjemplaresRestantes(1000-ejemplaresPrestados);
            libro.setAnio(anio);
            repositorioLibro.save(libro);
        }catch (MyException e ){
            System.out.println("HUBO UN ERROR.");
        }   
    }
    
    @Transactional
    public Libro altaLibro(String tituloLibro){
        Libro libro = repositorioLibro.findByTitulo(tituloLibro).
                orElseThrow(()-> new IllegalStateException("El libro con el titulo : "+tituloLibro+" no existe."));
        if (libro != null){
            libro.setAlta(Boolean.TRUE);
        }
        return repositorioLibro.save(libro);
    }
    public void validarDatos (long isbn,String titulo,String Autor,String Editorial) throws MyException{
        if (titulo==null||titulo.isEmpty()){
            throw new MyException ("Debe indicar el titulo del libro.");
        }
        if (Autor==null||Autor.isEmpty()){
            throw new MyException ("Debe indicar el autor del libro.");
        }
        if (Editorial==null||Editorial.isEmpty()){
            throw new MyException ("Debe indicar la editorial del libro.");
        }
    }
}
/*
public Perro alta(String id) {

		Perro entidad = perroRepository.findById(id).get();

		entidad.setActivo(true);
		return perroRepository.save(entidad);
	}
*/