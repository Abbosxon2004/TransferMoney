package uz.pdp.online.transfermoney.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.online.transfermoney.Entity.Card;
import uz.pdp.online.transfermoney.Entity.Income;
import uz.pdp.online.transfermoney.Entity.model.TransferingModel;
import uz.pdp.online.transfermoney.Repository.CardRepository;
import uz.pdp.online.transfermoney.Repository.IncomeRepository;

import java.util.Date;
import java.util.List;

@Service
public class IncomeService {

    @Autowired
    IncomeRepository incomeRepository;
    @Autowired
    CardRepository cardRepository;

    public List<Income> getIncomes(String username) {
        List<Card> all = cardRepository.findAll();
        int id = 0;
        for (Card card : all) {
            if (card.getUsername().equals(username)) {
                id = card.getId();
                break;
            }
        }
        List<Income> allByFromCardId = incomeRepository.findAllByToCardId(id);
        return allByFromCardId;
    }

    public void addIncome(String username, TransferingModel transferingModel) {
        int id = 0;
        List<Card> cardList = cardRepository.findAll();
        for (Card card : cardList) {
            if (card.getUsername().equals(username)) {
                id = card.getId();
            }
        }

        if (id != 0) {
            Income income = new Income();
            income.setFromCardId(id);
            income.setToCardId(transferingModel.getToCardId());
            income.setAmount(transferingModel.getAmount());
            income.setDate(new Date());
            incomeRepository.save(income);
        }
    }

}
