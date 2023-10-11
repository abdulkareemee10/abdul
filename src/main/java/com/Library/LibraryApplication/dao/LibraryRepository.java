package com.Library.LibraryApplication.dao;

import com.Library.LibraryApplication.entity.Library;

import java.util.List;

public interface LibraryRepository {

    String saveLibrary(Library library);

    String updateLibrary(Library library);

    String deleteById(long id);

    Library getById(long id);

    List<Library> allLibrary();

    List<Library> getBooksByCatAuthorBook(String category);
}

