import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ContactItem implements Serializable {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailAddress;
    private boolean complete;

    public ContactItem(String firstName, String lastName, String phoneNumber, String emailAddress){
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setFirstName(String newFirstName) {
        firstName = newFirstName;
    }

    public void setLastName(String newLastName) {
        lastName = newLastName;
    }

    public void setPhoneNumber(String newPhoneNumber) {
        phoneNumber = newPhoneNumber;
    }

    public void setEmailAddress(String newEmailAddress) {
        emailAddress = newEmailAddress;
    }

    public void setComplete(boolean newValue) {
        complete = newValue;
    }

    @Override
    public boolean equals(Object objectArg) {
        if(!(objectArg instanceof ContactItem)) return false;
        ContactItem other = (ContactItem) objectArg;

        return other.getFirstName().equalsIgnoreCase(firstName)
                && other.getLastName().equalsIgnoreCase(lastName)
                && other.getPhoneNumber().equalsIgnoreCase(phoneNumber)
                && other.getEmailAddress().equalsIgnoreCase(emailAddress);
    }

    @Override
    public String toString() {
        return String.format("Name: %s %s\nPhone: %s\nEmail: %s\n", firstName, lastName, phoneNumber, emailAddress);
    }

    public boolean validContactItem() {
        if (getFirstName().isBlank() && getLastName().isBlank() && getPhoneNumber().isBlank() && getEmailAddress().isBlank()) {
            return false;
        } else {
            return true;
        }
    }
}