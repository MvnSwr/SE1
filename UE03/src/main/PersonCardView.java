package main;

import java.util.List;

public class PersonCardView {
    //CR3
    public void showContent(List<PersonCard> liste){
        liste.forEach(System.out::println);
    }
}