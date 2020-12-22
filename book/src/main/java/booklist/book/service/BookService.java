package booklist.book.service;

import booklist.book.model.Book;
import booklist.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    String line = "";

    public void saveBookData(){


        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/book.csv"));

            while ((line = bufferedReader.readLine()) != null){

                String[] data = line.split(", ");
                Book book = new Book();
                book.setTitle(data[0]);
                book.setAuthor(data[1]);
                book.setGenre(data[2]);
                book.setHeight(data[3]);
                book.setPublisher(data[4]);
                bookRepository.save(book);
                }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

}
