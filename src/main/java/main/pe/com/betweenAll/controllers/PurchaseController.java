package main.pe.com.betweenAll.controllers;

import main.pe.com.betweenAll.dtos.DTOEventsAssistedSummary;
import main.pe.com.betweenAll.entities.Purchase;
import main.pe.com.betweenAll.entities.SocialEvent;
import main.pe.com.betweenAll.services.PurchaseService;
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
    @GetMapping("/purchases/End")
    public ResponseEntity<Purchase> purchaseEnd() {
        Purchase purchase = purchaseService.purchaseEnd();
        return new ResponseEntity<Purchase>(purchase, HttpStatus.OK);
    }
    @PostMapping("/purchases/{idUser}/{idCard}")
    public ResponseEntity<Purchase> createPurchase (@RequestBody Purchase purchase,@PathVariable("idUser") Long idUser,@PathVariable("idCard") Long idCard) {
        Purchase newPurchase = purchaseService.save(purchase, idUser, idCard);
        return new ResponseEntity<Purchase>(newPurchase, HttpStatus.CREATED);
    }

    @DeleteMapping("/purchases/{id}")
    public ResponseEntity<HttpStatus> deletePurchase (@PathVariable("id") Long id) {
        purchaseService.delete(id, true);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/purchases/{idUser}/{idCard}/{id}")
    public ResponseEntity<Purchase> updatePurchase (@RequestBody Purchase purchase,@PathVariable("idUser") Long idUser,@PathVariable("idCard") Long idCard, @PathVariable("id") Long id) {
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
        if(purchase.getTotal()!=null){
            foundPurchase.setTotal(purchase.getTotal());
        }
        Purchase updatePurchase = purchaseService.save(foundPurchase, idUser, idCard);
        return new ResponseEntity<Purchase>(updatePurchase, HttpStatus.OK);
    }

    @GetMapping("/purchases/summary/{id}")

    public ResponseEntity<List<DTOEventsAssistedSummary>> getAssistedTicketsSummary(@PathVariable("id") Long id) {
        List<DTOEventsAssistedSummary> dtoEventsAssistedSummaryList = purchaseService.listAssistedTicketsSummary(id);
        return new ResponseEntity<List<DTOEventsAssistedSummary>>(dtoEventsAssistedSummaryList, HttpStatus.OK);
    }

}
