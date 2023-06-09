package main.pe.com.betweenAll.servicesimpl;

import main.pe.com.betweenAll.entities.Purchase;
import main.pe.com.betweenAll.entities.Ticket;
import main.pe.com.betweenAll.repositories.PurchaseRepository;
import main.pe.com.betweenAll.repositories.TicketRepository;
import main.pe.com.betweenAll.services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service

public class PurchaseServiceImpl implements PurchaseService {
    @Autowired
    PurchaseRepository purchaseRepository;
    TicketRepository ticketRepository;
    @Transactional
    public List<Purchase> listAll() {
        List<Purchase> purchases;
        purchases= purchaseRepository.findAll();

        for(Purchase p: purchases){
            p.setTicketList(null);
        }

        return purchases;
    }
    @Transactional
    public Purchase listById(Long id) {
        Purchase purchase;
        purchase=purchaseRepository.findById(id).get();
        purchase.setTicketList(null);
        return purchase;
    }

    @Transactional
    public Purchase save(Purchase purchase) {
        Purchase newPurchase = purchaseRepository.save(new Purchase(purchase.getQuantity(), purchase.getDate(), purchase.getUser(), purchase.getCard()));
        return newPurchase;
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
}
