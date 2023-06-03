package main.pe.com.betweenAll.services;

import main.pe.com.betweenAll.entities.Card;

import java.util.List;

public interface CardService {
    public List<Card> listAll();

    public Card listById(Long id);

    public Card save(Card zoneEvent);

    public void delete(Long id, boolean forced);
}
