package main;

public abstract class CardBoxUtil{
    private static CardBox cb;


    public static CardBox getCardBox(){
        return cb;
    }

    /*CR1
    * durch ein statisches CardBox-Element wird gesichert, das die CardBox cb nur ein einziges mal im
    * Speicher liegt. Bei erneutem aufrufen von createCardBox() wird cb einfach überschrieben
    * Da CardBoxUtil abstract ist, lässt sich pauschal ohne anonyme Klasse kein Objekt erzeugen
    */
    public static void createCardBox(){
        cb = new CardBox();
    }
}