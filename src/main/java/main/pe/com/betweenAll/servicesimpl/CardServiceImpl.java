package main.pe.com.betweenAll.servicesimpl;

import main.pe.com.betweenAll.entities.*;
import main.pe.com.betweenAll.repositories.CardRepository;
import main.pe.com.betweenAll.repositories.PurchaseRepository;
import main.pe.com.betweenAll.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CardServiceImpl implements CardService {
    @Autowired
    CardRepository cardRepository;

    @Autowired
    PurchaseRepository purchaseRepository;
    @Transactional
    public List<Card> listAll() {
        List<Card> cards;
        cards= cardRepository.findAll();
        for(Card c: cards){
            c.setPurchaseList(null);
        }
        return cards;
    }
    @Transactional
    public Card listById(Long id) {
        Card card;
        card=cardRepository.findById(id).get();
        return card;
    }

    @Transactional
    public Card save(Card card) {
        Card newCard =
                cardRepository.save(
                        new Card(card.getName(), card.getNumber(), card.getCvv(),
                                card.getDueDate(),card.getState(),card.getUser()));
        return newCard;
    }

    @Transactional
    public void delete(Long id, boolean forced) {
        Card card = cardRepository.findById(id).get();
        if (forced){
            List<Purchase> purchases = purchaseRepository.findByCard_Id(id);
            for(Purchase dse: purchases){
                purchaseRepository.delete(dse);
            }
        }
        cardRepository.delete(card);
    }
}
