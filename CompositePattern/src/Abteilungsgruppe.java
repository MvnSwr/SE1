import java.util.List;
import java.util.ArrayList;

public class Abteilungsgruppe extends Abteilung{
    private List<Abteilung> list;
    
    public Abteilungsgruppe(String name){
        super(name);
        list = new ArrayList<>();
    }

    @Override
    public void printName(){
        super.printName();
        list.forEach(Abteilung::printName);
    }

    public void addAbteilung(Abteilung a){
        list.add(a);
    }

    public void removeAbteilung(Abteilung a){
        list.remove(a);
    }
}