package com.Library.LibraryApplication.controller;

import com.Library.LibraryApplication.dao.LibraryRepository;
import com.Library.LibraryApplication.entity.Library;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lib")
public class LibraryController {

    @Autowired
    LibraryRepository libraryRepository;

    @PostMapping("/postlibrary")
    public String saveUser(@RequestBody Library library) {

        return libraryRepository.saveLibrary(library);

    }

    @PutMapping("/putlibrary")
    public String updateUser(@RequestBody Library library) {

        return libraryRepository.updateLibrary(library);

    }

    @GetMapping("/getlibrary/{id}")
    public Library getLibrary(@PathVariable("id") long id) {

        return libraryRepository.getById(id);

    }

    @GetMapping("/getlibrary")
    public List<Library> getBooks() {

        return libraryRepository.allLibrary();


    }
    @DeleteMapping("delbook/{id}")
    public String deleteById( @PathVariable("id") long id){

        return  libraryRepository.deleteById(id);

    }


}

