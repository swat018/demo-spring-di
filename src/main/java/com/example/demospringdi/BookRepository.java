package com.example.demospringdi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository
//public class BookRepository {
//}

public interface BookRepository extends JpaRepository<Book, Integer> {

}