package com.usa.misiontic.masterclass3.controller;


import com.usa.misiontic.masterclass3.entities.Admin;
import com.usa.misiontic.masterclass3.entities.Library;
import com.usa.misiontic.masterclass3.service.AdminService;
import com.usa.misiontic.masterclass3.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "*" )
@RestController
@RequestMapping("/api/Lib")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @GetMapping("/all")
    public List<Library> getAll(){ return libraryService.getAll();
    }
    @GetMapping("/{id}")
    public Optional<Library> getLibrary(@PathVariable ("id")int id){
        return libraryService.getLibrary(id);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Library save(@RequestBody Library library){
        return libraryService.save(library);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Library update(@RequestBody Library library) {
        return libraryService.update(library);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") int id){
        return libraryService.deleteLibrary(id);
    }


}
