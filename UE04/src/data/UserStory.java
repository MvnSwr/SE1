package data;
import java.util.ArrayList;
import java.util.stream.*;

public class UserStory extends DatastructImpl{
    private String prio;
    private ArrayList<Task> tasks;

    public UserStory(){
        tasks = new ArrayList<>();
    }

    public String getPrio() {
        return prio;
    }

    public void setPrio(String prio) {
        this.prio = prio;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task){
        tasks.add(task);
    }

    public String toString(){
        String out = "ID: " + this.getID() + ", Beschreibung: " + this.getDescription() + ", Priorität: " + this.getPrio() + ", Zugeordnete Tasks: ";
        if(tasks.size() != 0){
            return out += tasks .stream()
                                .map(Task::toString)
                                .collect(Collectors.joining());
        }
        return out += "keine";
    }

}