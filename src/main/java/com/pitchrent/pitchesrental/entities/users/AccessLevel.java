package com.pitchrent.pitchesrental.entities.users;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "access_level", discriminatorType = DiscriminatorType.STRING)
@Entity(name = "access_levels")
public abstract class AccessLevel {
    @Getter
    @Id
    @SequenceGenerator(name = "ACCESS_LEVEL_SEQ_GEN", sequenceName = "access_level_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACCESS_LEVEL_SEQ_GEN")
    @Column(name = "id")
    @ToString.Exclude
    private long id;

    @JoinColumn(name = "account_id", referencedColumnName = "id", updatable = false)
    @OneToOne(optional = false, cascade = CascadeType.PERSIST)
    @Getter
    @Setter
    private Account account;

    public abstract String getAccessLevelType();
}
