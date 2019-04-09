package ru.khmelev.tm.api.endpoint;

import ru.khmelev.tm.entity.Project;

import javax.jws.WebService;
import javax.xml.bind.annotation.XmlType;

@WebService
public interface IProjectEndpoint extends IEntityEndpoint<Project> {
}

