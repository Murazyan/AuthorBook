package com.example.authorbook.rest;


import com.example.authorbook.model.Book;
import com.example.authorbook.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping()
    public ResponseEntity users(){
        return ResponseEntity.ok(bookRepository.findAll());
    }

    @PostMapping()
    public ResponseEntity addAuthor(@RequestBody Book book){
        bookRepository.save(book);
        return ResponseEntity.ok("created");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteBookByid(@PathVariable(name = "id") int id){
        try{
            bookRepository.delete(id);
            return  ResponseEntity.ok("delete");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("book by "+ id + " does not exist");
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity userById(@PathVariable(name = "id") int id ){
        Book one = bookRepository.findOne(id);
        return ResponseEntity.ok(one);
    }

    @PutMapping()
    public ResponseEntity updateAuthor(@RequestBody Book book){
        if(bookRepository.exists(book.getId())){
            bookRepository.save(book);
            return ResponseEntity.ok("update");
        }else{
            return ResponseEntity.badRequest().body("User with " + book.getTitle()+ " does not exist");
        }
    }
}
