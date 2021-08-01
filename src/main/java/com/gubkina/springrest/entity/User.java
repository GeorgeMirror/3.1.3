package com.gubkina.springrest.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "users")
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;

    @NotEmpty
    @Size(min = 1, max = 30, message = "Name size is incorrect")
    @Column(name = "first_name")
    private String firstname;

    @NotEmpty
    @Size(min = 1, max = 30, message = "Surname size is incorrect")
    @Column(name = "surname")
    private String surname;

    @Min(value = 1, message = "Age must be not less than 1 year")
    @Max(value = 150, message = "Age must be no more than 150 years")
    @Column(name = "age")
    private int age;

    @Email(message = "Email must be valid")
    @Column(name = "user_name")
    private String username;

    @NotEmpty
    @Size(min = 8, message = "Password musn`t be short")
    @Column(name = "password")
    private String password;

    @ManyToMany
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> rolesOfUser;

    public User() {
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstName) {
        this.firstname = firstName;
    }

    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    @JsonManagedReference
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return rolesOfUser;
    }
    public void setRolesOfUser(Set<Role> roles) {
        this.rolesOfUser = roles;
    }
    public Set<Role> getRolesOfUser() {
        return rolesOfUser;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
