package data;
import java.util.ArrayList;

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

}