package uz.pdp.online.transfermoney.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.online.transfermoney.Entity.Card;
import uz.pdp.online.transfermoney.Entity.model.ApiResponse;
import uz.pdp.online.transfermoney.Entity.model.TransferingModel;
import uz.pdp.online.transfermoney.Repository.CardRepository;

import java.util.Date;
import java.util.List;

@Service
public class CardService {
    @Autowired
    CardRepository cardRepository;

    public List<Card> getCardsInformation() {
        List<Card> cardList = cardRepository.findAll();
        return cardList;
    }

    public void addCardDefaults() {
        cardRepository.save(new Card(1, "anvar", "123456", 500000, new Date(System.currentTimeMillis() + 36000000), true));
        cardRepository.save(new Card(2, "salim", "654321", 500000, new Date(System.currentTimeMillis() + 36000000), true));
        cardRepository.save(new Card(3, "karim", "147258", 500000, new Date(System.currentTimeMillis() + 36000000), true));
        cardRepository.save(new Card(4, "nodir", "369258", 500000, new Date(System.currentTimeMillis() + 36000000), true));
    }

    public ApiResponse sendMoneyEditCardInformation(String username, TransferingModel transferingModel) {
        int id = 0;
        List<Card> cardList = cardRepository.findAll();
        for (Card card : cardList) {
            if (username.equals(card.getUsername())) {
                id = card.getId();
                float amountNeed = (float) (transferingModel.getAmount()) + (float) (transferingModel.getAmount() * 0.01);
                if (card.getBalance() < amountNeed) {
                    return new ApiResponse("Your balance is not enough to send", false);
                }
                card.setBalance(card.getBalance() - amountNeed);
                cardRepository.save(card);
                for (Card cardDto : cardList) {
                    if (transferingModel.getToCardId() == cardDto.getId()) {
                        cardDto.setBalance(cardDto.getBalance() + amountNeed);
                        cardRepository.save(cardDto);
                        return new ApiResponse("Money transfered", true);
                    }
                }
            }
        }
        return new ApiResponse("There is an error during sending", false);
    }
}