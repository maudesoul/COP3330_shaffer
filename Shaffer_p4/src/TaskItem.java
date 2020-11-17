import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TaskItem implements Serializable {
    public static final boolean COMPLETE = true;
    public static final boolean INCOMPLETE = false;

    private final String DATE_FORMAT = "yyyy-MM-dd";
    private final SimpleDateFormat formatChecker = new SimpleDateFormat(DATE_FORMAT, Locale.US);

    private String title;
    private String description;
    private String dueDate;
    private boolean complete;

    public TaskItem(String title, String description, String dueDate){
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        formatChecker.setLenient(false);
    }

    public String getTitle(){
        return title;
    }

    public String getDescription(){
        return description;
    }

    public String getDueDate(){
        return dueDate;
    }

    public boolean isComplete(){
        return complete;
    }

    public boolean setTitle(String newTitle){
        if(newTitle.length() > 0){
            title = newTitle;
            return true;
        }
        return false;
    }

    public boolean setDueDate(String newDueDate){
        if(validDate(newDueDate)) {
            dueDate = newDueDate;
            return true;
        }
        return false;
    }

    public void setDescription(String newDescription){
        description = newDescription;
    }

    public void setComplete(boolean newValue){
        complete = newValue;
    }

    @Override
    public String toString(){
        return String.format("%s - [%s] %s: %s", complete ? "COMPLETE" : "INCOMPLETE", dueDate, title, description);
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof TaskItem)) return false;
        TaskItem other = (TaskItem) o;

        return other.getTitle().equalsIgnoreCase(title)
                && other.getDescription().equalsIgnoreCase(description)
                && other.getDueDate().equalsIgnoreCase(dueDate);
    }

    private boolean validTitle(){
        return title.length() > 0;
    }

    public boolean validDate(String dateStr) {
        try {
            Date given = formatChecker.parse(dateStr);
            Date current = formatChecker.parse(formatChecker.format(new Date()));

            if(given.compareTo(current) < 0)
                return false;
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    public boolean validTaskItem() {
        return validTitle() && validDate(dueDate);
    }

}