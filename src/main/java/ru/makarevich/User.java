package ru.makarevich;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Entity
@NoArgsConstructor
@Table(name = "Users", schema = "dbo")
public class User {
    @Id
    @GeneratedValue
    private int id;

    private String name;
}
