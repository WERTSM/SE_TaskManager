package ru.khmelev.tm.entity.dto;

import lombok.Getter;
import lombok.Setter;
import ru.khmelev.tm.api.dto.IdentifiableDTO;

import java.util.Date;

@Getter
@Setter
public class SessionDTO implements IdentifiableDTO {

    public SessionDTO() {
        this.id = "";
        this.signature = "";
        this.userId = "";
        this.dateCreate = new Date();
    }

    private String id;

    private String userId;

    private String signature;

    private Date dateCreate;
}
