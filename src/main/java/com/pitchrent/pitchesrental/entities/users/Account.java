package com.pitchrent.pitchesrental.entities.users;

import com.pitchrent.pitchesrental.validators.Login;
import com.pitchrent.pitchesrental.validators.Password;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.PositiveOrZero;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor

public class Account {
    @Id
    @SequenceGenerator(name = "ACCOUNT_SEQ_GEN", sequenceName = "account_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACCOUNT_SEQ_GEN")
    @Column(name = "id", nullable = false)
    private Long id;
    @Login
    @Column(unique=true)
    private String login;
    @Password
    private String password;
    @Email
    private String email;
    private Boolean active;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.REMOVE})
    private AccessLevel accessLevel;
    @Version
    @PositiveOrZero
    private Long version;

    public Account(String login, String password, String email, Boolean active) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.active = active;
    }
    public Account(String login,  String email, Boolean active) {
        this.login = login;
        this.email = email;
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Account account = (Account) o;
        return id != null && Objects.equals(id, account.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
