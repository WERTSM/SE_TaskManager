package ru.khmelev.tm.endpoint.util;

import lombok.Getter;
import lombok.Setter;
import ru.khmelev.tm.entity.Entity;
import ru.khmelev.tm.entity.Project;
import ru.khmelev.tm.entity.Task;
import ru.khmelev.tm.entity.User;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "EntitiesPTU")
@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso({Project.class, Task.class, User.class})
public class EntityListJAXB<T extends Entity> {

    @Getter
    @Setter
    @XmlElement(name = "Entity")
    @XmlElementWrapper(name = "listEntities")
    private List<T> list;
}