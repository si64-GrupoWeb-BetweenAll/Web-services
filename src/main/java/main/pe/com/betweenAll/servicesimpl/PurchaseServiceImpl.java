package main.pe.com.betweenAll.servicesimpl;

import main.pe.com.betweenAll.dtos.DTOEventsAssistedSummary;
import main.pe.com.betweenAll.entities.*;
import main.pe.com.betweenAll.exceptions.IncompleteDataException;
import main.pe.com.betweenAll.repositories.CardRepository;
import main.pe.com.betweenAll.repositories.PurchaseRepository;
import main.pe.com.betweenAll.repositories.TicketRepository;
import main.pe.com.betweenAll.repositories.UserRepository;
import main.pe.com.betweenAll.services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.module.ResolutionException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service

public class PurchaseServiceImpl implements PurchaseService {
    @Autowired
    PurchaseRepository purchaseRepository;
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    CardRepository cardRepository;
    @Transactional
    public List<Purchase> listAll() {
        List<Purchase> purchases;
        purchases= purchaseRepository.findAll();


        for(Purchase p: purchases){
            p.setCard(null);
            p.setTicketList(null);
            p.getUser().setSocialEventList(null);
            p.getUser().setGroupUserList(null);
            p.getUser().setPurchaseList(null);
            p.getUser().setUserCategoryList(null);
            p.getUser().setAuthorityList(null);
            p.getUser().setCardList(null);
            p.setTotal(null);
            p.getUser().setGroupUserList(null);
            p.getUser().setGroupList(null);

        }
        return purchases;
    }
    @Transactional
    public Purchase listById(Long id) {
        Purchase purchase;
        purchase=purchaseRepository.findById(id).orElseThrow(()->new ResolutionException("Not found an Purchase with id="+id));

        purchase.setTicketList(null);
        return purchase;
    }

    @Transactional
    public Purchase save(Purchase purchase, Long idUser, Long idCard) {

        if (purchase.getDate() == null || purchase.getDate().equals("")) {
            throw new IncompleteDataException("Purchase Date cannot be null or empty");
        }

        if (purchase.getQuantity() == null || purchase.getQuantity() == 0) {
            throw new IncompleteDataException("Purchase Quantity cannot be null or zero");
        }
        User user = userRepository.findById(idUser).get();
        Card card = cardRepository.findById(idCard).get();

        purchase.setUser(user);
        purchase.setCard(card);
        Purchase newPurchase = purchaseRepository.save(purchase);


        newPurchase.getUser().setSocialEventList(null);
        newPurchase.getUser().setGroupUserList(null);
        newPurchase.getUser().setPurchaseList(null);
        newPurchase.getUser().setUserCategoryList(null);
        newPurchase.getUser().setAuthorityList(null);
        newPurchase.getUser().setCardList(null);
        newPurchase.getUser().setGroupUserList(null);
        newPurchase.getUser().setGroupList(null);
        newPurchase.getCard().setPurchaseList(null);
        newPurchase.setTicketList(null);

        return newPurchase;
    }

    @Transactional
    public Purchase purchaseEnd(){
        Purchase purchase = purchaseRepository.purchaseEnd();

        purchase.setTicketList(null);
        purchase.getUser().setSocialEventList(null);
        purchase.getUser().setGroupUserList(null);
        purchase.getUser().setPurchaseList(null);
        purchase.getUser().setUserCategoryList(null);
        purchase.getUser().setAuthorityList(null);
        purchase.getUser().setCardList(null);
        purchase.getCard().setPurchaseList(null);
        purchase.getUser().setGroupList(null);
        return purchase;
    }

    @Transactional
    public void delete(Long id, boolean forced) {
        Purchase purchase = purchaseRepository.findById(id).get();
        if (forced){
            List<Ticket> tickets = ticketRepository.findByPurchase_Id(id);
            for(Ticket t: tickets){
                ticketRepository.delete(t);
            }
        }
        purchaseRepository.delete(purchase);
    }

    @Transactional
    public List<DTOEventsAssistedSummary> listAssistedTicketsSummary(Long id) {
        List<User> userList = userRepository.findUser(id);
        if (userList.isEmpty()) {
            // Manejo de error si el usuario no existe
            return new ArrayList<>();
        }

        User user = userList.get(0);

        List<Purchase> purchaseList = user.getPurchaseList();

        List<DTOEventsAssistedSummary> dtoEventsAssistedSummaryList = new ArrayList<>();

        for (Purchase purchase : purchaseList) {
            List<Ticket> ticketList = purchase.getTicketList();

            for (Ticket ticket : ticketList) {
                ZoneEvent zoneEvent = ticket.getZoneEvent();
                DateSocialEvent dateSocialEvent = zoneEvent.getDateSocialEvent();
                SocialEvent socialEvent = dateSocialEvent.getSocialEvent();

                String eventName = socialEvent.getName();
                Date eventDate = dateSocialEvent.getDate();
                String eventZone = zoneEvent.getName();
                String userName = user.getName() + " " + user.getLastname();

                DTOEventsAssistedSummary dtoEventsAssistedSummary = new DTOEventsAssistedSummary(eventName, eventDate, eventZone, userName);
                dtoEventsAssistedSummaryList.add(dtoEventsAssistedSummary);
            }
        }

        return dtoEventsAssistedSummaryList;
    }
}
