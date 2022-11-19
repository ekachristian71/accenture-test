package com.h2.demo.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String socialSecurityNumber;

    @Column(nullable = false)
    private String dateOfBirth;

    @Column(nullable = false)
    private long createdDate = new Date().getTime();

    @Column(nullable = false)
    private long updatedDate = new Date().getTime();

    @Column(nullable = false)
    private String updatedBy = "SPRING_BOOT_TEST";

    @Column(nullable = false)
    private String createdBy = "SPRING_BOOT_TEST";

    @Column(nullable = false)
    private boolean isDeleted = false;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
