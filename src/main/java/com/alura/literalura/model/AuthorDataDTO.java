package com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AuthorDataDTO(
        @JsonAlias("name") String name,
        @JsonAlias("birth_year") Integer yearOfBirth,
        @JsonAlias("death_year") Integer yearOfDeath)
    {
}
