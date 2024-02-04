package org.agoncal.quarkus.microservices.book;

import io.quarkus.logging.Log;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.time.Instant;

@Path("/api/books")
@Tag(name = "Book REST Endpoint")
public class BookResource {

    @RestClient
    NumberProxy numberProxy;

    @Retry(delay = 3000, maxRetries = 3)
    @Fallback(fallbackMethod = "fallbackOnCreateBook")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Operation(description = "Creates a new book", summary = "Creates a book with an ISBN number")
    public Response createBook(
            @FormParam("title") String title,
            @FormParam("author") String author,
            @FormParam("year") int yearOfPublication,
            @FormParam("genre") String genre) {

        Book book = new Book();
        book.isbn13 = numberProxy.generateIsbnNumbers().isbn13;
        book.title = title;
        book.author = author;
        book.yearOfPublication = yearOfPublication;
        book.genre = genre;
        book.creationDate = Instant.now();

        Log.infof("Book created: %s", book.creationDate);
        return Response.status(201).entity(book).build();
    }

    public Response fallbackOnCreateBook(String title,
                                         String author,
                                         int yearOfPublication,
                                         String genre) {
        Log.warn("fallback on createBook invoked");
        Book book = new Book();
        book.isbn13 = "Will be set later";
        book.title = title;
        book.author = author;
        book.yearOfPublication = yearOfPublication;
        book.genre = genre;
        book.creationDate = Instant.now();
        saveBookOnDisk(book);
        Log.infof("Book created: %s", book.creationDate);
        return Response.status(201).entity(book).build();
    }

    private void saveBookOnDisk(Book book) {
    }
}
