package ru.khmelev.tm.service.util;

import lombok.Getter;
import lombok.Setter;
import ru.khmelev.tm.entity.*;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "EntitiesPTU")
@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso({Project.class, Task.class, User.class})
public class EntityListJAXB<T extends Identifiable> {

    @Getter
    @Setter
    @XmlElement(name = "Entity")
    @XmlElementWrapper(name = "listEntities")
    private List<T> list;
}