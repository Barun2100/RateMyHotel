package com.hotelratingapp.user.service.userservice.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "micro_users")
public class User {

    @Id
    @Column(name = "ID")
    private String userId;

    @Column(name = "NAME")
    private String userName;

    @Column(name = "EMAIL")
    private String userEmail;

    @Column(name = "BIO")
    private String userBio;
    
    @Transient
    private List<Rating> ratings;
}