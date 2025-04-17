package at.fhv.sys.hotel.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class CustomerQueryModel {

    @Id
    private String userId;
    private String email;

    private String name;



    public CustomerQueryModel() {}

    public CustomerQueryModel(String userId, String email, String name) {
        this.userId = userId;
        this.email = email;
        this.name = name;
    }

    public String getUserId() {
        return "Customer-" + userId;
    }

    public String getEmail() {
        return "Customer Email is: " + email;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
