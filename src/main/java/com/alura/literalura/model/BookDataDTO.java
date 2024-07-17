package com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookDataDTO(
        @JsonAlias("title") String title,
        @JsonAlias("download_count") Long downloadCount,
        @JsonAlias("languages") List<String> languages,
        @JsonAlias("authors") List<AuthorDataDTO> authors
        )
    {
}
