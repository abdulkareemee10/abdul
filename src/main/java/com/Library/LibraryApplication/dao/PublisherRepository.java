package com.Library.LibraryApplication.dao;

import com.Library.LibraryApplication.entity.Category;
import com.Library.LibraryApplication.entity.Publisher;

import java.util.List;

public interface PublisherRepository {

    String savePublisher(Publisher publisher);

    String updatePublisher(Publisher publisher);

    String deleteById(long id);

    Publisher getById(long id);

    List<Publisher> allPublishers();

	Publisher getByName(String name);
}
