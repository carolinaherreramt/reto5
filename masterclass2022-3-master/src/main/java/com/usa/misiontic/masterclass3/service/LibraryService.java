package com.usa.misiontic.masterclass3.service;

import com.usa.misiontic.masterclass3.entities.Category;
import com.usa.misiontic.masterclass3.entities.Client;
import com.usa.misiontic.masterclass3.entities.Library;
import com.usa.misiontic.masterclass3.repository.ClientRepository;
import com.usa.misiontic.masterclass3.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibraryService {

    @Autowired
    private LibraryRepository libraryRepository;

    public List<Library> getAll() {
        return libraryRepository.getAll();
    }

    public Optional<Library> getLibrary(int id) {
        return libraryRepository.getLibrary(id);
    }
    public Library save(Library library){
        if(library.getId()==null) {
            return libraryRepository.save(library);
        }else{
            Optional<Library>libraryEncontrado=getLibrary(library.getId());
            if( libraryEncontrado.isEmpty()){
                return libraryRepository.save(library);
            }else{
                return library;
            }
        }
    }
    public Library update(Library library){
        if (library.getId()!=null){
            Optional<Library> libraryEncontrado= getLibrary(library.getId());
            if(!libraryEncontrado.isEmpty()){
                if(library.getName()!=null){
                    libraryEncontrado.get().setName(library.getName());
                }
                if (library.getTarget()!=null){
                    libraryEncontrado.get().setTarget(library.getTarget());
                }

                if (library.getCapacity()!=null) {
                    libraryEncontrado.get().setCapacity(library.getCapacity());
                }
                if (library.getDescription()!=null) {
                    libraryEncontrado.get().setDescription(library.getDescription());
                }

                if (library.getCategory()!=null) {
                    libraryEncontrado.get().setCategory(library.getCategory());
                }

                return libraryRepository.save(libraryEncontrado.get());

            }
        }
        return library;
    }
    public boolean deleteLibrary(int id){
        Boolean respuesta= getLibrary(id).map(library-> {
            libraryRepository.delete(library);
            return true;
        }).orElse(false);
        return respuesta;
    }
}


