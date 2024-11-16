package com.ContactNexus.Forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserForm {
//    @NotBlank
//    private String name;
//    @Email
//    private String email;
//    @NotBlank()
//    private String password;
//    @NotBlank()
//    private String phoneNumber;
//    @NotBlank()
//
@NotBlank(message = "Username is required")
@Size(min = 3, message = "Min 3 Characters is required")
private String name;

    @Email(message = "Invalid Email Address")
    @NotBlank(message = "Email is required")
    private String email;
    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Min 6 Characters is required")
    private String password;

    @NotBlank(message = "About is required")
    private String about;
    @Size(min = 8, max = 12, message = "Invalid Phone Number")
    private String phoneNumber;
}
