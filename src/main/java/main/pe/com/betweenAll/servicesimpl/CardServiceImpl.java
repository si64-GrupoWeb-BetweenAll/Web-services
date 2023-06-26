package main.pe.com.betweenAll.servicesimpl;

import main.pe.com.betweenAll.entities.*;
import main.pe.com.betweenAll.exceptions.IncompleteDataException;
import main.pe.com.betweenAll.repositories.CardRepository;
import main.pe.com.betweenAll.repositories.PurchaseRepository;
import main.pe.com.betweenAll.repositories.UserRepository;
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
    UserRepository userRepository;
    @Autowired
    PurchaseRepository purchaseRepository;
    @Transactional
    public List<Card> listAll() {
        List<Card> cards;
        cards= cardRepository.findAll();
        for(Card c: cards){
            c.setUser(null);
            //c.setName(null);
            //c.setCvv(null);
            //c.setNumber(null);
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
    public Card save(Card card, Long idUser) {
        /*Card newCard =
                cardRepository.save(
                        new Card(card.getName(), card.getNumber(), card.getCvv(),
                                card.getDueDate(),card.getState(),card.getUser()));
        */
        if(card.getName()==null||card.getName().isEmpty()){
            throw new IncompleteDataException("Card Name can not be null or empty");
        }
        if(card.getNumber()==null||card.getNumber().describeConstable().isEmpty()){
            throw new IncompleteDataException("Card Number can not be null or empty");
        }
        if(card.getCvv()==null||card.getCvv().describeConstable().isEmpty()){
            throw new IncompleteDataException("Card CVV can not be null or empty");
        }
        if(card.getDueDate()==null){
            throw new IncompleteDataException("Card Due Date can not be null or empty");
        }
        User user= userRepository.findById(idUser).get();
        card.setUser(user);
        Card newCard=cardRepository.save(card);
        /*
        * if(group.getName()==null || group.getName().isEmpty()){
            throw new IncompleteDataException("Group Name can not be null or empty");
        }
        * */
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
