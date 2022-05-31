package uz.pdp.online.transfermoney.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.online.transfermoney.Entity.Card;
import uz.pdp.online.transfermoney.Entity.Outcome;
import uz.pdp.online.transfermoney.Entity.model.TransferingModel;
import uz.pdp.online.transfermoney.Repository.CardRepository;
import uz.pdp.online.transfermoney.Repository.OutcomeRepository;

import java.util.Date;
import java.util.List;

@Service
public class OutcomeService {

    @Autowired
    OutcomeRepository outcomeRepository;
    @Autowired
    CardRepository cardRepository;

    public List<Outcome> getOutcomes(String username) {
        List<Card> all = cardRepository.findAll();
        int id = 0;
        for (Card card : all) {
            if (card.getUsername().equals(username)) {
                id = card.getId();
                break;
            }
        }
        List<Outcome> allByFromCardId = outcomeRepository.findAllByFromCardId(id);
        return allByFromCardId;
    }

    public void addOutcome(String username, TransferingModel transferingModel) {
        int fromId = 0;
        List<Card> cardList = cardRepository.findAll();
        for (Card card : cardList) {
            if (card.getUsername().equals(username)) {
                fromId = card.getId();
            }
        }

        if (fromId != 0) {
            Outcome outcome = new Outcome();
            outcome.setFromCardId(fromId);
            outcome.setToCardId(transferingModel.getToCardId());
            outcome.setAmount(transferingModel.getAmount());
            outcome.setCommisionAmount((float) (transferingModel.getAmount() * 0.01));
            outcome.setDate(new Date());
            outcomeRepository.save(outcome);
        }
    }

}
