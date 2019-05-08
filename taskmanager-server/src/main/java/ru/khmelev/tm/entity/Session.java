package ru.khmelev.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.CreationTimestamp;
import org.jetbrains.annotations.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@javax.persistence.Entity
@Table(name = "session")
@NoArgsConstructor
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Session extends Identifiable implements Serializable {

    @Id
    @MapsId
    private String id;

    @Nullable
    @OneToOne
    @JoinColumn(name = "userId")
    private User user;

    private String signature;

    @Temporal(value = TemporalType.DATE)
    private Date dateCreate;
}