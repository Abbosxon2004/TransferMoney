package uz.pdp.online.transfermoney.Controller;


import org.springframework.http.ResponseEntity;
import uz.pdp.online.transfermoney.playload.CardDto;
import uz.pdp.online.transfermoney.playload.IncomeDto;
import uz.pdp.online.transfermoney.playload.OutcomeDto;
import uz.pdp.online.transfermoney.playload.model.ApiResponse;
import uz.pdp.online.transfermoney.playload.model.TransferingModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ControlAll {


    static List<CardDto> cardDtoList = new ArrayList<>();
    static List<IncomeDto> incomeDtoList = new ArrayList<>();
    static List<OutcomeDto> outcomeDtoList = new ArrayList<>();

    public List<CardDto> getCardDtoList() {
        cardDtoList.add(new CardDto(1, "anvar", "123456", 500000, new Date(System.currentTimeMillis() + 36000000), true));
        cardDtoList.add(new CardDto(2, "salim", "654321", 500000, new Date(System.currentTimeMillis() + 36000000), true));
        cardDtoList.add(new CardDto(3, "karim", "147258", 500000, new Date(System.currentTimeMillis() + 36000000), true));
        cardDtoList.add(new CardDto(4, "nodir", "369258", 500000, new Date(System.currentTimeMillis() + 36000000), true));
        return cardDtoList;
    }


    public ApiResponse editCardInformation(String username, TransferingModel transferingModel) {
        int fromCardId = 0;
        for (CardDto card : cardDtoList) {
            if (username.equals(card.getUsername())) {
                fromCardId = card.getId();
                float amountNeed = (float) (transferingModel.getAmount()) + (float) (transferingModel.getAmount() * 0.01);
                if (card.getBalance() < amountNeed) {
                    return new ApiResponse("Your balance is not enough to send", false);
                }
                card.setBalance(card.getBalance() - amountNeed);
                for (CardDto cardDto : cardDtoList) {
                    if (transferingModel.getToCardId() == cardDto.getId()) {
                        cardDto.setBalance(cardDto.getBalance() + amountNeed);
                        return new ApiResponse("Money transfered", true);
                    }
                }
            }
        }


        return new ApiResponse("There is an error during sending", false);
    }


    //################################################################################################################//
    public List<IncomeDto> getIncomeList(String username) {
        int id = 0;
        for (CardDto cardDto : cardDtoList) {
            if (cardDto.getUsername().equals(username)) {
                id = cardDto.getId();
                break;
            }
        }
        List<IncomeDto> listIncome = new ArrayList<>();
        for (IncomeDto incomeDto : incomeDtoList) {
            if (incomeDto.getToCardId() == id) {
                listIncome.add(incomeDto);
            }
        }
        return listIncome;
    }

    public void addIncome(String username, TransferingModel transferingModel) {
        int fromId = 0;

        for (CardDto card : cardDtoList) {
            if (card.getUsername().equals(username)) {
                fromId = card.getId();
                break;
            }
        }
        if (fromId != 0)
            incomeDtoList.add(new IncomeDto(fromId, transferingModel.getToCardId(), transferingModel.getAmount(), new Date()));
    }
    //################################################################################################################//


    //################################################################################################################//
    public List<OutcomeDto> getOutcomeList(String username) {
        int id = 0;
        for (CardDto cardDto : cardDtoList) {
            if (cardDto.getUsername().equals(username)) {
                id = cardDto.getId();
                break;
            }
        }
        List<OutcomeDto> listOutcome = new ArrayList<>();
        for (OutcomeDto outcomeDto : outcomeDtoList) {
            if (outcomeDto.getFromCardId() == id) {
                listOutcome.add(outcomeDto);
            }
        }
        return listOutcome;
    }

    public void addOutcome(String username, TransferingModel transferingModel) {
        int fromId = 0;

        for (CardDto card : cardDtoList) {
            if (card.getUsername().equals(username)) {
                fromId = card.getId();
                break;
            }
        }
        if (fromId != 0)
            outcomeDtoList.add(new OutcomeDto(fromId, transferingModel.getToCardId(), transferingModel.getAmount(), new Date(), (float) (transferingModel.getAmount() * 0.01)));
    }
    //################################################################################################################//
}
