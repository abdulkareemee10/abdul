package com.Library.LibraryApplication.controller;

import com.Library.LibraryApplication.dao.CategoryRepository;
import com.Library.LibraryApplication.dao.PublisherRepository;
import com.Library.LibraryApplication.entity.Category;
import com.Library.LibraryApplication.entity.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pub")
public class PublisherController {

    @Autowired
    PublisherRepository publisherRepository;

    @PostMapping("/publisher")
    public String savePublisher(@RequestBody Publisher publisher) {

        return publisherRepository.savePublisher(publisher);

    }

    @PutMapping("/category")
    public String updatePublisher(@RequestBody Publisher publisher) {

        return publisherRepository.updatePublisher(publisher);

    }

    @GetMapping("/Publisher/{id}")
    public Publisher getPublisher(@PathVariable("id") long id) {

        return publisherRepository.getById(id);

    }

    @GetMapping("/category")
    public List<Publisher> getPublisher() {

        return publisherRepository.allPublishers();


    }
    @DeleteMapping("Publisher/{id}")
    public String deleteById( @PathVariable("id") long id) {

        return publisherRepository.deleteById(id);

    }

}
