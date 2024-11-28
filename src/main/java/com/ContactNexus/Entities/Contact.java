package com.ContactNexus.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Contact {
    @Id
    private String id;
    @NotBlank(message = "invalid name")
    private String name;

    @Email(message = "invalid email")
    private String email;
    @NotBlank(message = "phonenumber is required")
    @Pattern(regexp = "^[0-9]{10}$",message = "invalid phone number")
    private String phoneNumber;

    @NotBlank(message = "address is required")
    private String address;

    private String picture;
    @Column(length = 1000)
    @NotBlank(message = "description is required")
    private String description;
    private boolean favorite = false;
    private String websiteLink;
    private String linkedInLink;
    // private List<String> socialLinks=new ArrayList<>();
    private String cloudinaryImagePublicId;
    @ManyToOne
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<SocialLink> links = new ArrayList<>();

}