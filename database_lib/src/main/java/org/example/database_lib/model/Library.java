package org.example.database_lib.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Library {
    private Long id;        // Corresponds to the SERIAL PRIMARY KEY
    private String name;    // Corresponds to VARCHAR(100)
    private String address; // Corresponds to VARCHAR(200)
    private String phone;   // Corresponds to VARCHAR(20), can be null
    private String email;   // Corresponds to VARCHAR(100), can be null

    @Override
    public String toString() {
        return "Library{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
