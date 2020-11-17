import java.util.Scanner;
import java.io.File;

public class App {
    private static final Scanner scan = new Scanner(System.in);

    public static void main(String [] args) {
        mainMenu();
    }

    public static void mainMenu() {
        while(true) {
            System.out.print("\nMain Menu\n");
            System.out.print("---------\n\n");
            System.out.println("1) create a new list");
            System.out.println("2) load an existing list");
            System.out.println("3) quit");

            String choice = scan.nextLine();

            switch(choice) {
                case "1" -> listOperationMenu(new TaskList());
                case "2" -> {
                    TaskList retrieved = loadList();
                    if (retrieved != null) {
                        listOperationMenu(retrieved);
                    }
                }
                case "3" -> System.exit(0);
                default -> System.out.println("\nNot a valid selection. Please try again.\n");
            }
        }
    }

    public static TaskList loadList(){
        System.out.print("\nSaved lists by save file name:\n");
        File saveDir = new File("SaveFiles/");
        String[] savedFileNames = saveDir.list();
        if(savedFileNames != null) {
            for (String name : savedFileNames)
                System.out.println(name);
        } else {
            System.out.print("No lists saved yet! You must start and save a list before you can load an existing one.\n");
            return null;
        }
        System.out.print("\nEnter the name of the desired save file, or CANCEL to go back to the main menu.\n");
        TaskList newLoad = new TaskList();

        while(true){
            String fileName = scan.nextLine();
            if(fileName.equalsIgnoreCase("CANCEL"))
                return null;
            else if(newLoad.loadList(fileName))
                return newLoad;
            else
                System.out.print("Enter an another file name:\n");
        }
    }

    public static void listOperationMenu(TaskList taskList) {
        while(true) {
            System.out.print("\nList Operation Menu\n");
            System.out.print("-------------------\n\n");
            System.out.println("1) view the list");
            System.out.println("2) add an item");
            System.out.println("3) edit an item");
            System.out.println("4) remove an item");
            System.out.println("5) mark an item as completed");
            System.out.println("6) unmark an item as completed");
            System.out.println("7) save the current list");
            System.out.println("8) quit to the main menu\n");

            String choice = scan.nextLine();
            switch(choice) {
                case "1" -> System.out.println(taskList.toString());
                case "2" -> addItem(taskList);
                case "3" -> editItem(taskList);
                case "4" -> removeItem(taskList);
                case "5" -> markCompleted(taskList);
                case "6" -> markIncompleted(taskList);
                case "7" -> saveList(taskList);
                case "8" -> {
                    return;
                }
                default -> System.out.println("\nNot a valid selection. Please try again.\n");
            }
        }
    }

    public static TaskItem getTaskItemIdentifiersFromInput(String action) {
        System.out.printf("\ntask title:\n", action.equals("edit") ? "New " : "");
        String title = scan.nextLine();
        while(title.length() < 1) {
            System.out.print("WARNING: title must be at least 1 character long; task not created. please type in new title\n");
            title = scan.nextLine();
        }

        System.out.printf("\ntask description:\n", action.equals("edit") ? "New " : "");
        String description = scan.nextLine();

        System.out.printf("\ntask due date (yyyy-mm-dd):\n", action.equals("edit") ? "New " : "");
        String date = scan.nextLine();
        TaskItem item = new TaskItem(title, description, date);
        while(!item.validTaskItem()){
            System.out.print("\nWARNING: invalid due date; task not created. please enter new date (yyyy-mm-dd)\n");
            date = scan.nextLine();
            item.setDueDate(date);
        }
        return item;
    }

    public static TaskItem getTaskItemFromInput(TaskList taskList){
        String input = scan.nextLine();
        try {
            int taskNumber = Integer.parseInt(input);
            TaskItem item = taskList.getTask(taskNumber);
            if(item != null) {
                System.out.printf("Selected task '%s' from index %d\n", item.toString(), taskNumber);
                return item;
            } else {
                System.out.printf("Couldn't find a task at index %d, trying by title '%s'\n", taskNumber, input);
                item = taskList.getTask(input);
                if(item == null)
                    System.out.printf("Couldn't find a task with title '%s'\n", input);
                else
                    System.out.printf("Selected task '%s'\n", item.toString());
                return item;
            }
        } catch(NumberFormatException error){
            System.out.printf("Searching for task with title '%s'\n", input);
            TaskItem item = taskList.getTask(input);
            if(item == null)
                System.out.printf("Couldn't find a task with title '%s'\n", input);
            else
                System.out.printf("Selected task '%s'\n", item.toString());

            return item;
        }
    }

    public static void addItem(TaskList taskList) {
        TaskItem newItem = getTaskItemIdentifiersFromInput("add");
        if (taskList.getTask(newItem.getTitle()) != null) {
            System.out.println("\nWARNING: a task with that title already exists. no changes made\n");
        } else {
            taskList.addTask(newItem);
            System.out.println("SUCCESS: added task to list.");
        }
    }

    public static void editItem(TaskList taskList) {
        System.out.print("\n" + taskList.toString() + "\n");
        System.out.print("\nwhich task will you edit?\n");
        TaskItem oldTask = getTaskItemFromInput(taskList);
        if (oldTask == null) {
            System.out.print("\nWARNING: no task was found by that name or index. no changes made to list.");
        } else {
            TaskItem editedTask = getTaskItemIdentifiersFromInput("edit");
            oldTask.setTitle(editedTask.getTitle());
            oldTask.setDescription(editedTask.getDescription());
            oldTask.setDueDate(editedTask.getDueDate());
            System.out.print("SUCCESS: edited task.");
        }

    }

    public static void removeItem(TaskList taskList) {
        System.out.print("\n" + taskList.toString() + "\n");
        System.out.print("\nwhich task will you remove?\n");
        TaskItem task = getTaskItemFromInput(taskList);
        if (task == null) {
            System.out.print("\nWARNING: no task found by that name or index. no changes made to list.");
        } else {
            boolean success = taskList.removeTask(task);
            if (success) {
                System.out.print("\nSUCCESS: removed task from list.\n");
            } else {
                System.out.print("\nERROR: cannot remove task from list.\n");
            }
        }
    }

    public static void markCompleted(TaskList taskList) {
        System.out.print("\n" + taskList.viewSubset(TaskItem.INCOMPLETE) + "\n");

        if (taskList.sizeByStatus(TaskItem.INCOMPLETE) == 0) {
            System.out.println("WARNING: no incomplete tasks on this list.");
            return;
        }
        System.out.print("\nwhich task will you mark as completed?\n");
        TaskItem task = getTaskItemFromInput(taskList);
        if (task == null) {
            System.out.print("\nWARNING: no task found by that name or index. no changes made to list.");
        } else {
            boolean success = taskList.setCompletion(taskList.indexOf(task), TaskItem.COMPLETE);
            if (success) {
                System.out.print("\nSUCCESS: marked task as complete.\n");
            } else {
                System.out.print("\nERROR: cannot mark task as complete.\n");
            }
        }
    }

    public static void markIncompleted(TaskList taskList) {
        System.out.print("\n" + taskList.viewSubset(TaskItem.COMPLETE) + "\n");

        if (taskList.sizeByStatus(TaskItem.COMPLETE) == 0) {
            System.out.println("WARNING: no complete tasks on this list.");
            return;
        }
        System.out.print("\nwhich task will you mark as completed?\n");
        TaskItem task = getTaskItemFromInput(taskList);
        if (task == null) {
            System.out.print("\nWARNING: no task found by that name or index. no changes made to list.");
        } else {
            boolean success = taskList.setCompletion(taskList.indexOf(task), TaskItem.INCOMPLETE);
            if (success) {
                System.out.print("\nSUCCESS: marked task as incomplete.\n");
            } else {
                System.out.print("\nERROR: cannot mark task as incomplete.\n");
            }
        }
    }

    public static void saveList(TaskList taskList) {
        boolean hasDefault = taskList.hasDefaultSaveLocation();
        if(hasDefault){
            System.out.print("\nthis list has a default save location. save to this location? (Y/N)\n");
            String yn = scan.nextLine();
            if(yn.equalsIgnoreCase("y") || yn.equalsIgnoreCase("yes")) {
                taskList.saveList();
            } else if(yn.equalsIgnoreCase("n") || yn.equalsIgnoreCase("no")) {
                saveToNewFile(taskList);
            } else {
                System.out.print("\nERROR: invalid choice. no changes made.\n");
            }
        } else {
            saveToNewFile(taskList);
        }
    }

    public static void saveToNewFile(TaskList taskList) {
        System.out.print("\nenter the filename to save as: ");
        String name = scan.nextLine();
        if (!name.toLowerCase().contains(".txt"))
            name += ".txt";

        File file = new File("SaveFiles/" + name);
        if (file.exists() && !file.isDirectory()) {
            taskList.saveList(name);
        } else {
            taskList.saveList(name);
        }
    }
}
