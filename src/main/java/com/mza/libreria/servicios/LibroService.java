package com.mza.libreria.servicios;

import com.mza.libreria.MyException.MyException;
import com.mza.libreria.entidades.Libro;
import com.mza.libreria.repositorios.RepositorioLibro;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroService {
    @Autowired
    private RepositorioLibro repositorioLibro;
    
    private AutorService autorService;
    private EditorialService editorialService;
    
    public void guardarLibro (Long isbn,String titulo,String Autor,String Editorial,Integer anio) throws MyException{
        try {
            Libro libro = new Libro();

            validarDatos(isbn, titulo, Autor, Editorial);

            libro.setIsbn(isbn);
            libro.setTitulo(titulo);
            libro.setAutor(Autor);
            libro.setEditorial(Editorial);
            libro.setEjemplares(1000);
            int ejemplaresPrestados = (int) (Math.random() * 151);
            libro.setEjemplaresPrestados(ejemplaresPrestados);
            libro.setEjemplaresRestantes(1000 - ejemplaresPrestados);
            libro.setAnio(anio);
            repositorioLibro.save(libro);
            //libro.setAutor(autorService.guardarAutor(Autor));
            //libro.setEditorial(editorialService.guardarEditorial(Editorial));
        }catch (MyException e){
            System.out.println("Algo salió mal.");
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
        Optional <Libro> opcional = repositorioLibro.findByTitulo(titulo);
        if (opcional.isPresent()){
           throw new MyException ("Este titulo ya se encuentra registrado."); 
        }
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
