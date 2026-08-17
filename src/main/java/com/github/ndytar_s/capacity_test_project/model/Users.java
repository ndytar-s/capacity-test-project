package com.github.ndytar_s.capacity_test_project.model;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id")
    )
    @Column(name = "role_name")
    private Set<String> role;

    // constructeurs
    public Users() {}

    public Users(String username, String password, Set<String> role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // getters setters
    public Long   getId()       { return id; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }

    public Set<String> getRole() {
        return role;
    }
}
