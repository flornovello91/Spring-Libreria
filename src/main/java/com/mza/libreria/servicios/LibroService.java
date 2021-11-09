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
    
    @Autowired
    private AutorService autorService;
    
    @Autowired
    private EditorialService editorialService;
    
    public void guardarLibro (Long isbn,String titulo,String autor,String editorial,Integer anio) throws MyException{
        try {
            Libro libro = new Libro();
            
            validarDatos(isbn, titulo, autor, editorial,anio);

            libro.setIsbn(isbn);
            libro.setTitulo(titulo);
            libro.setAnio(anio);
            libro.setAlta(Boolean.TRUE);
            libro.setEjemplares(1000);
            int ejemplaresPrestados = (int) (Math.random() * 151);
            libro.setEjemplaresPrestados(ejemplaresPrestados);
            libro.setEjemplaresRestantes(1000 - ejemplaresPrestados);
            libro.setAutor(autorService.guardarAutor(autor));
            libro.setEditorial(editorialService.guardarEditorial(editorial));
            
            repositorioLibro.save(libro);
        }catch (MyException e){
            System.out.println("Algo salió mal.");
        }
    }
    
    public void validarDatos (Long isbn,String titulo,String autor,String editorial,Integer anio) throws MyException{
        
        if (titulo==null||titulo.isEmpty() || titulo.contains("  ")){
            throw new MyException ("Debe indicar el titulo del libro.");
        }
        if (autor==null||autor.isEmpty() || titulo.contains("  ")){
            throw new MyException ("Debe indicar el autor del libro.");
        }
        if (editorial==null||editorial.isEmpty() || titulo.contains("  ")){
            throw new MyException ("Debe indicar la editorial del libro.");
        }
        if (isbn <= 0 || isbn==null){
            throw new MyException("Debe indicar el isbn correctamente.");
        }
        if (anio<=999){
            throw new MyException("Debe indicar un año de publicación correcto.");
        }
        
        //REVISAR ESTA QUERY PORQUE NO ME TRAE LAS COSAS
        
//        Optional <Libro> opcional = repositorioLibro.findByTitulo(titulo);
//        if (opcional.isPresent()){
//           throw new MyException ("Este titulo ya se encuentra registrado."); 
//        }
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
}
/*
public void validar(Long isbn, String titulo, Integer anio, Integer ejemplares, Integer ejemplaresPrestados, Integer ejemplaresRestantes, String autor, String editorial) throws Exception {

        if (titulo == null || titulo.isEmpty() || titulo.contains("  ")) {
            throw new Exception();
        }

        if (autor == null || autor.isEmpty() || autor.contains("  ")) {
            throw new Exception();
        }

        if (editorial == null || editorial.isEmpty() || editorial.contains("  ")) {
            throw new Exception();
        }

        if (isbn == null || anio == null || ejemplares == null || ejemplaresPrestados == null || ejemplaresRestantes == null) {
            throw new Exception();
        }
    }

*/