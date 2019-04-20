package ru.khmelev.tm.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@javax.persistence.Entity
@Table(name = "session")
public class Session extends Identifiable {

    private String id;

    @JoinColumn(name = "userId")
    @OneToOne
    private User user;

    private String signature;

    private Date dateCreate;
}