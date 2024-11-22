package ua.vladyslav.jdbc_spring.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    
    int id;

    @NotBlank(message = "The name couldn't be empty")
    @Size(min = 3, max = 30, message = "The name length should be from 3 to 30")
    String name;

    @NotBlank(message = "The username couldn't be empty")
    @Size(min = 3, max = 30, message = "The username length should be from 3 to 30")
    String username;

    @Min(value = 0, message = "The age must not less then 0")
    @Max(value = 110, message = "The age must not more then 110")
    int age;

    @NotBlank(message = "The email couldn't be empty")
    @Size(min = 3, max = 50, message = "The email length should be from 3 to 30")
    String email;

    @NotBlank(message = "The address couldn't be empty")
    @Pattern(regexp = "[A-Z]\\w+, [A-Z]\\w+, \\d{5}", message = "Address should be in format \"Urraine, Chernihiv, 14001\"")
    String address; 

}
