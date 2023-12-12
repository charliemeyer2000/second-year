package org.example;
import java.util.List;

public class Contact {
    public String firstName;

    public String lastName;

    public List<String> emails;

    public Contact(String firstName, String lastName, List<String> emails) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emails = emails;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("Contact(");
        sb.append("firstName=");
        sb.append(firstName);
        sb.append(", ");
        sb.append("lastName=");
        sb.append(lastName);
        sb.append(", ");
        sb.append("emails=");
        sb.append(emails);
        sb.append(")");
        return sb.toString();
    }


    
}
