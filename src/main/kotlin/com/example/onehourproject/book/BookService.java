package com.example.onehourproject.book;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class BookService {
    private final List<Book> books = new ArrayList<>();

    public Book createBook(Book book) {
        book.setId((long) (books.size() + 1));
        books.add(book);
        return book;

    }

    public List<Book> getBooks() {
        return books;
    }

    public Book updateBook(Long id, Book updatedBook) {
        for (Book book : books) {
            if (book.getId().equals(id)) {
                book.setTitle(updatedBook.getTitle());
                return book;
            }
        }
        return null;
    }

    public boolean deleteBook(Long id) {
        for (Iterator<Book> iterator = books.iterator(); iterator.hasNext();) {
            Book book = iterator.next();
            if (book.getId().equals(id)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }
}
