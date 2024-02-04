package org.agoncal.quarkus.microservices.book;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IsbnThirteen {

    @JsonProperty("isbn_13")
    public String isbn13;
}
