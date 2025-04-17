package at.fhv.sys.hotel.commands.shared.events;

public class CustomerCreated {

    private String userId;
    private String email;

    private String name;

    public CustomerCreated() {}

    public CustomerCreated(String userId, String email, String name) {
        this.userId = userId;
        this.email = email;
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {this.name = name;}

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "CustomerCreated{" + "userId='" + userId + '\'' + ", email='" + email + '\'' + "userName='" + name +'}';
    }
}
