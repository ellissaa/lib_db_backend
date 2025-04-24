package org.example.database_lib.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PublicationType {
    private Long id;        // Corresponds to the SERIAL PRIMARY KEY
    private String name;    // Corresponds to VARCHAR(50)

    @Override
    public String toString() {
        return "PublicationType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
