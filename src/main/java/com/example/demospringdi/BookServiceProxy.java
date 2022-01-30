package com.example.demospringdi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class BookServiceProxy implements BookService{

    @Autowired @Qualifier("defaultBookService")
    BookService bookService;

    public BookServiceProxy(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public void rent(Book book) {
        System.out.println("aaaaa");
        bookService.rent(book);
        System.out.println("bbbbb");
    }
}
