package newsmagazine.com.newsmagazine.model;

/**
 * Created by lenovo on 10-04-2018.
 */

public class User {
    private String firstname;
    private String lastname;
    private String reference;
    private String phone;

    public User(String firstname, String lastname, String reference, String phone) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.reference = reference;
        this.phone = phone;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
