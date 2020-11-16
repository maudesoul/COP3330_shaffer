import java.io.*;
import java.util.ArrayList;

public class TaskList implements Serializable {

    private ArrayList<TaskItem> tasks = new ArrayList<TaskItem>();
    private String fromFile; //If file has been saved, holds that save name
    private final String saveDir = "C:/This PC/Desktop/SaveFiles/";

    //Constructor for brand new lists
    public TaskList() {}

    //Constructor for new list from existing file
    public TaskList(String loadFile){
        fromFile = loadFile;
        loadList(loadFile); //Return value checked in unit tests
    }

    //Checks if this file has already been saved and has a default location
    public boolean hasDefaultSaveLocation(){
        return fromFile != null;
    }

    public String getDefaultSavePath(){
        if(!hasDefaultSaveLocation()) return "";
        return saveDir + fromFile;
    }

    //Save task list to a new file
    //Returns false if any errors occur
    //PRECONDITION: fileName has already been checked for pre-existence by App.java
    //Overwrites any existing save files under that fileName
    public boolean saveList(String fileName){
        if(!fileName.toLowerCase().contains(".ser"))
            fileName += ".ser"; //appends .ser to the filename if the user didn't include the extension

        File directory = new File(saveDir);
        if (!directory.exists()) {
            boolean dirSetupSuccess = directory.mkdir();
            if(!dirSetupSuccess) {
                System.out.println("Failed to create SaveFiles directory. Task list was not saved.");
                return false;
            }
        }

        try {
            FileOutputStream fileOut = new FileOutputStream(saveDir + fileName, false);
            OutputStream buffer = new BufferedOutputStream(fileOut);
            ObjectOutput output = new ObjectOutputStream(buffer);
            output.writeObject(this);
            output.close();
            fileOut.close();
            System.out.println("Successfully saved to " + saveDir + fileName);
            fromFile = fileName;
        } catch (IOException i) {
            System.out.println("Failed to save this task list.");
            return false;
        }

        return true;
    }

    //Saves task list to the stored name fromFile
    //Returns false if any errors occur or if no fileName (can't do anything)
    public boolean saveList(){
        if(fromFile == null){ //Should never happen, but not taking any chances. App class should be checking for defaults before calling either method
            System.out.println("Tried to save file to default location, but no default location is specified for this list!\n"
                    + "Make sure to provide a file name while asking the program to save this list.");
            return false;
        }
        return saveList(fromFile);
    }

    //Loads save file to this object
    //Returns false if that file couldn't be found or an error occurred (PRINT WHY HERE)
    public boolean loadList(String fileName){
        if(!fileName.toLowerCase().contains(".ser"))
            fileName += ".ser"; //appends .ser to the filename if the user didn't include the extension

        TaskList dummyList;
        try {
            FileInputStream fileIn = new FileInputStream(saveDir + fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Object input = in.readObject();
            if (input instanceof TaskList)
                dummyList = (TaskList) input;
            else
                throw new ClassCastException(); //Data is corrupted, don't proceed any further

            tasks = dummyList.tasks;
            fromFile = fileName;
            in.close();
            fileIn.close();
        } catch (ClassCastException cce){
            System.out.println("We couldn't convert the data in this save file to a task list. You may need to delete this file and start over.");
            return false;
        } catch (IOException i) {
            System.out.println("We couldn't load that file. Make sure the file you want to load exists and is not corrupted.");
            return false;
        } catch (ClassNotFoundException c) {
            System.out.println("We couldn't convert the data in this save file to a task list. You may need to delete this file and start over.");
            c.printStackTrace();
            return false;
        }

        return true;
    }

    //Check that can be used to determine if constructor load succeeded.
    public boolean successfullyLoaded(){
        return tasks.size() > 0;
    }

    //Returns list of all tasks based on matching completion to input value
    public String viewSubset(boolean complete){
        StringBuilder builder = new StringBuilder();
        builder.append(complete ? "Completed Tasks" : "Incomplete Tasks").append("\n------------------------\n");
        int indexCounter = 0;

        for(TaskItem item : tasks){
            if(item.isComplete() == complete)
                builder.append(String.format("%d) %s\n", indexCounter, item.toString()));

            indexCounter++;
        }

        return builder.toString().trim();
    }

    //Returns task list in text form, used to view tasks
    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("All Tasks").append("\n------------------------\n");
        int indexCounter = 0;

        for(TaskItem item : tasks)
            builder.append(String.format("%d) %s\n", indexCounter++, item.toString()));

        return builder.toString().trim();
    }

    //Add new task to list (validation in App class)
    //Returns false if it fails by finding an existing tasks with the same title
    public boolean addTask(TaskItem newTask){
        for(TaskItem item : tasks){
            if(item.getTitle().equalsIgnoreCase(newTask.getTitle()))
                return false;
        }
        tasks.add(newTask);
        return true;
    }

    //Returns a reference to the task at the given index, or null if that index is out of bounds
    public TaskItem getTask(int index){
        if(outOfBounds(index))
            return null;
        return tasks.get(index);
    }

    public TaskItem getTask(String title){
        for(TaskItem item : tasks){
            if(item.getTitle().equalsIgnoreCase(title)){
                return item;
            }
        }
        return null;
    }

    //Removes the task at the given index from the list
    //Return true if the action was successful, false if the index is out of bounds
    public boolean removeTask(int index){
        if(outOfBounds(index))
            return false;
        tasks.remove(index);
        return true;
    }

    public boolean removeTask(TaskItem item){
        return tasks.remove(item);
    }

    //Replaces old task with the edited version
    public boolean replaceTask(int oldTaskIndex, TaskItem newTask){
        if(outOfBounds(oldTaskIndex)) {
            System.out.println("The provided index is not in the list, nothing changed!");
            return false;
        } else {
            removeTask(oldTaskIndex);
            tasks.add(oldTaskIndex, newTask);
            return true;
        }
    }

    //Sets the completion field of the task at the given index to the be given completion status
    //Returns true if the action was successful, false if the index is out of bounds
    public boolean setCompletion(int index, boolean complete){
        if(outOfBounds(index))
            return false;
        tasks.get(index).setComplete(complete);
        return true;
    }

    //Get the total number of tasks in the list
    public int size(){
        return tasks.size();
    }

    //Get the number of tasks in the list that have a matching completion status
    public int sizeByStatus(boolean complete){
        int counter = 0;
        for(TaskItem item : tasks)
            counter += item.isComplete() == complete ? 1 : 0;
        return counter;
    }

    public int indexOf(TaskItem item){
        for(int i = 0; i < tasks.size(); i++){
            TaskItem current = tasks.get(i);
            if(current.equals(item))
                return i;
        }
        return -1;
    }

    public boolean outOfBounds(int index){
        return index < 0 || index >= tasks.size();
    }

}