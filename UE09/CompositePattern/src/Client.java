public class Client {
    public static void main(String[] args){
        Abteilungsgruppe ab = new Abteilungsgruppe("AB");
        Abteilungsgruppe ab3 = new Abteilungsgruppe("AB-3");
        Abteilungsgruppe ab4 = new Abteilungsgruppe("AB-4");

        Einzelabteilung ab30 = new Einzelabteilung("AB-30");
        Einzelabteilung ab31 = new Einzelabteilung("AB-31");
        Einzelabteilung ab40 = new Einzelabteilung("AB-40");
        Einzelabteilung ab41 = new Einzelabteilung("AB-41");

        ab.addAbteilung(ab3);
        ab.addAbteilung(ab4);

        ab3.addAbteilung(ab30);
        ab3.addAbteilung(ab31);

        ab4.addAbteilung(ab40);
        ab4.addAbteilung(ab41);

        ab.printName();
    }
}