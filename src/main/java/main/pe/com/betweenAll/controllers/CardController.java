package main.pe.com.betweenAll.controllers;

import main.pe.com.betweenAll.entities.Card;
import main.pe.com.betweenAll.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class CardController {
    @Autowired
    CardService cardService;

    @GetMapping("/creditCards")
    public ResponseEntity<List<Card>> getAllCards() {
        List<Card> cards = cardService.listAll();
        return new ResponseEntity<List<Card>>(cards, HttpStatus.OK);
    }

    /*@PutMapping("/creditCards/{id}")
    public ResponseEntity<Card> updateCardById(@PathVariable("id") Long id) {
        Card card = cardService.listById(id);
        return new ResponseEntity<Card>(card, HttpStatus.OK);
    }*/

    @GetMapping("/creditCards/{id}")
    public ResponseEntity<Card> getCardById(@PathVariable("id") Long id) {
        Card card = cardService.listByIdUser(id);
        return new ResponseEntity<Card>(card, HttpStatus.OK);
    }

    @PostMapping("/creditCards/{idUser}")
    public ResponseEntity<Card> createCard (@RequestBody Card card, @PathVariable("idUser") Long idUser) {
        Card newCard = cardService.save(card,idUser);
        return new ResponseEntity<Card>(newCard, HttpStatus.CREATED);
    }

    @DeleteMapping("/creditCards/{id}")
    public ResponseEntity<HttpStatus> deleteCard (@PathVariable("id") Long id) {
        cardService.delete(id, true);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
