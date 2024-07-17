package com.alura.literalura.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(unique = true)
    private String title;

    private String language;

    private Long downloadCount;

    @ManyToOne
    private Author author;

    public Book(BookDataDTO bookDataDTO) {
        this.title = bookDataDTO.title();
        this.language = bookDataDTO.languages().get(0);
        this.downloadCount = bookDataDTO.downloadCount();
        this.author = bookDataDTO.authors().stream().map(Author::new).toList().get(0);
    }

    public void addAuthor(Author author) {
        author.getBooks().add(this);
    }
}
