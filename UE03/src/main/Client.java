package main;

import repo.CardboxException;

public class Client {
    
    //CR3
    public static void main(String[] args){
        CardBoxUtil.createCardBox();
        PersonCardView pcv = new PersonCardView();
        try{
            CardBoxUtil.getCardBox().addPersonCard(new EnduserCard("Marc", "Mueller", false, 0));
            CardBoxUtil.getCardBox().addPersonCard(new EnduserCard("Hans", "Freier", false, 1));
            CardBoxUtil.getCardBox().addPersonCard(new EnduserCard("Nico", "Meyer", true, 2));
            CardBoxUtil.getCardBox().addPersonCard(new DeveloperCard("Jonas", "Schueren", true, 3));
            CardBoxUtil.getCardBox().addPersonCard(new DeveloperCard("Frida", "Faula", true, 4));
        }catch(CardboxException e){}
        pcv.showContent(CardBoxUtil.getCardBox().getCurrentList());
    }
}