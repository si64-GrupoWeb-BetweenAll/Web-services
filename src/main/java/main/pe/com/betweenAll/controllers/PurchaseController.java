package main.pe.com.betweenAll.controllers;

import main.pe.com.betweenAll.entities.Purchase;
import main.pe.com.betweenAll.entities.SocialEvent;
import main.pe.com.betweenAll.services.PurchaseService;
import main.pe.com.betweenAll.services.SocialEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")

public class PurchaseController {

    @Autowired
    PurchaseService purchaseService;

    @GetMapping("/purchases")
    public ResponseEntity<List<Purchase>> getAllPurchase() {
        List<Purchase> purchases = purchaseService.listAll();
        return new ResponseEntity<List<Purchase>>(purchases, HttpStatus.OK);

    }

    @GetMapping("/purchases/{id}")
    public ResponseEntity<Purchase> getAllPurchasesById(@PathVariable("id") Long id) {
        Purchase purchase = purchaseService.listById(id);
        return new ResponseEntity<Purchase>(purchase, HttpStatus.OK);

    }

    @PostMapping("/purchases")
    public ResponseEntity<Purchase> createPurchase (@RequestBody Purchase purchase) {
        Purchase newPurchase = purchaseService.save(purchase);
        return new ResponseEntity<Purchase>(newPurchase, HttpStatus.CREATED);
    }

    @DeleteMapping("/purchases/{id}")
    public ResponseEntity<HttpStatus> deletePurchase (@PathVariable("id") Long id) {
        purchaseService.delete(id, true);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/purchases/{id}")
    public ResponseEntity<Purchase> updatePurchase (@RequestBody Purchase purchase, @PathVariable("id") Long id) {
        Purchase foundPurchase=purchaseService.listById(id);

        if(purchase.getDate()!=null){
            foundPurchase.setDate(purchase.getDate());
        }
        if(purchase.getQuantity()!=null){
            foundPurchase.setQuantity(purchase.getQuantity());
        }
        if(purchase.getUser()!=null){
            foundPurchase.setUser(purchase.getUser());
        }
        if(purchase.getCard()!=null){
            foundPurchase.setCard(purchase.getCard());
        }

        Purchase updatePurchase = purchaseService.save(foundPurchase);
        return new ResponseEntity<Purchase>(updatePurchase, HttpStatus.OK);
    }
}
