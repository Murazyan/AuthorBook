package com.example.authorbook.rest;

import com.example.authorbook.model.Author;
import com.example.authorbook.model.Book;
import com.example.authorbook.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/authors")
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping()
    public ResponseEntity users(){
        return ResponseEntity.ok(authorRepository.findAll());
    }



    @PostMapping()
    public ResponseEntity addAuthor(@RequestBody Author author){
        authorRepository.save(author);
        return ResponseEntity.ok("created");
    }


    @DeleteMapping("/{id}")
    public ResponseEntity deleteBookByid(@PathVariable(name = "id") int id){
        try{
            authorRepository.delete(id);
            return  ResponseEntity.ok("delete");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("book by "+ id + " does not exist");
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity userById(@PathVariable(name = "id") int id ){
        Author one = authorRepository.findOne(id);
        return ResponseEntity.ok(one);
    }
    @PutMapping()
    public ResponseEntity updateAuthor(@RequestBody Author author){

        if(authorRepository.exists(author.getId())){
            authorRepository.save(author);
            return ResponseEntity.ok("update");
        }else{
            return ResponseEntity.badRequest().body("User with " + author.getName()+ " does not exist");
        }
    }
}
