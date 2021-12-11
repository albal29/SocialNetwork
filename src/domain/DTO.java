package domain;

import java.time.LocalDateTime;

public class DTO {
    String firstName;
    String lastName;
    LocalDateTime date;


    public DTO(String firstName, String lastName, LocalDateTime date) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.date = date;
    }

    @Override
    public String toString() {
        return "First name: " + firstName + " | " + "Last name: " + lastName + " | " + "Date: " + date.toString();
    }
}

