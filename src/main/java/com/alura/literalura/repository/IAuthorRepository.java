package com.alura.literalura.repository;

import com.alura.literalura.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IAuthorRepository extends JpaRepository<Author, Long> {
    Author findByName(String name);

    @Query(value = "select a from author a where a.yearOfBirth <=:year and a.yearOfDeath >=:year", nativeQuery = true)
    List<Author> searchAliveAuthorsByYear(Integer year);
}
