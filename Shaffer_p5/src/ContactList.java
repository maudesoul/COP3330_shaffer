import java.io.*;
import java.util.ArrayList;

public class ContactList implements Serializable {

    private ArrayList<ContactItem> contacts = new ArrayList<ContactItem>();
    private String fromFile;
    private final String saveDir = "SaveFiles/";

    public ContactList() {}

    public ContactList(String loadFile) {
        fromFile = loadFile;
        loadList(loadFile);
    }

    public boolean outOfBounds(int index) {
        return index < 0 || index >= contacts.size();
    }

    public boolean successfullyLoaded() {
        return contacts.size() > 0;
    }

    public boolean hasDefaultSaveLocation(){
        return fromFile != null;
    }


    public boolean saveList(String fileName){
        if(!fileName.toLowerCase().contains(".txt"))
            fileName += ".txt";
        File directory = new File(saveDir);
        if (!directory.exists()){
            boolean dirSetupSuccess = directory.mkdir();
            if(!dirSetupSuccess) {
                System.out.println("ERROR: failed to create SaveFiles directory. list not saved.");
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
            System.out.println("SUCCESS: saved to " + saveDir + fileName);
            fromFile = fileName;
        } catch (IOException i) {
            System.out.println("ERROR: failed to save this contact list.");
            return false;
        }

        return true;
    }

    public boolean saveList() {
        if (fromFile == null) {
            System.out.println("ERROR: please check the filename and try again.");
            return false;
        }
        return saveList(fromFile);
    }

    public boolean loadList(String fileName) {
        ContactList prototypeList;
        if (!fileName.toLowerCase().contains(".txt"))
            fileName += ".txt";
        try {
            FileInputStream fileIn = new FileInputStream(saveDir + fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Object input = in.readObject();
            if (input instanceof ContactList)
                prototypeList = (ContactList) input;
            else
                throw new ClassCastException();

            contacts = prototypeList.contacts;
            fromFile = fileName;
            in.close();
            fileIn.close();
        } catch (ClassCastException cce){
            System.out.println("ERROR: data conversion unsuccessful. try deleting the file.");
            return false;
        } catch (IOException i) {
            System.out.println("ERROR: file loading failure. file may not exist or is corrupted.");
            return false;
        } catch (ClassNotFoundException c) {
            System.out.println("ERROR: data conversion unsuccessful. try deleting the file.");
            c.printStackTrace();
            return false;
        }

        return true;
    }

    public String viewSubset(boolean complete){
        StringBuilder builder = new StringBuilder();
        builder.append(complete ? "Completed Contacts" : "Incomplete Contacts").append("\n----------------\n");
        int indexCounter = 0;

        for(ContactItem item : contacts){
            if(item.isComplete() == complete)
                builder.append(String.format("%d) %s\n", indexCounter, item.toString()));

            indexCounter++;
        }

        return builder.toString().trim();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Current Contacts").append("\n-------------\n");
        int indexCounter = 0;
        for (ContactItem item : contacts) {
            builder.append(String.format("%d) %s\n", indexCounter++, item.toString()));
        }
        return builder.toString().trim();
    }

    public ContactItem getContact(int index){
        if(outOfBounds(index))
            return null;
        return contacts.get(index);
    }

    public ContactItem getContact(String titleArg){
        for (ContactItem item : contacts) {
            if (item.getFirstName().equalsIgnoreCase(titleArg)) {
                return item;
            }
        }
        return null;
    }

    public boolean addContact(ContactItem newContact) {
        for (ContactItem item : contacts) {
            if(item.getFirstName().equalsIgnoreCase(newContact.getFirstName())) {
                return false;
            }
        }
        contacts.add(newContact);
        return true;
    }

    public boolean removeContact(int index) {
        if (outOfBounds(index)) {
            return false;
        }
        contacts.remove(index);
        return true;
    }

    public boolean removeContact(ContactItem item) {
        return contacts.remove(item);
    }

    public boolean setCompletion(int index, boolean complete) {
        if (outOfBounds(index)) {
            return false;
        }
        contacts.get(index).setComplete(complete);
        return true;
    }

    public int size() {
        return contacts.size();
    }

    public int sizeByStatus(boolean complete){
        int counter = 0;
        for (ContactItem item : contacts)
            counter += item.isComplete() == complete ? 1 : 0;
        return counter;
    }

    public int indexOf(ContactItem item) {
        for (int i = 0; i < contacts.size(); i++) {
            ContactItem current = contacts.get(i);
            if (current.equals(item)) {
                return i;
            }
        }
        return -1;
    }
}