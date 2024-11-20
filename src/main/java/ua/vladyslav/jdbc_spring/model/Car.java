package ua.vladyslav.jdbc_spring.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Car {
    Integer id;
    String producer;
    String model;
    int manufactureYear;
    String origin;
}
