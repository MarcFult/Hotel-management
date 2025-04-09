package at.fhv.sys.hotel.commands;

import java.time.LocalDate;

public record CreateCustomerCommand(String userId, String name, String email) {

    // private String name;
    // private String email;
    // private String userId;
    // private String address;
    // private LocalDate birthday;

}
