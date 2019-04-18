package ru.khmelev.tm;

import ru.khmelev.tm.bootstrap.Bootstrap;
import ru.khmelev.tm.entity.dto.ProjectDTO;
import ru.khmelev.tm.entity.enumeration.Status;
import ru.khmelev.tm.service.ProjectService;
import ru.khmelev.tm.service.ProjectServiceH;

import java.util.Date;
import java.util.List;

public final class ApplicationServer {
    public static void main(String[] args) throws Exception {
        //Bootstrap bootstrap = new Bootstrap();
        //bootstrap.init();

        ProjectServiceH psh = new ProjectServiceH();

        ProjectDTO projectDTO = new ProjectDTO();

        projectDTO.setId("12345");
        projectDTO.setName("MEDVEDEV");
        projectDTO.setDescription("sdadsa");
        projectDTO.setDateStart(new Date());
        projectDTO.setDateFinish(new Date());
        projectDTO.setDateCreate(new Date());
        projectDTO.setStatus(Status.PLANNED);
        projectDTO.setUserId("11111111-1111-1111-1111-111111111111");
        psh.createEntity(projectDTO.getId(), projectDTO);


        //psh.removeEntity("123456789", projectDTO.getUserId());



        //System.out.println(psh.findEntity("123", "11111111-1111-1111-1111-111111111111").getName());
        //for (ProjectDTO pr : psh.findAll("11111111-1111-1111-1111-111111111111")) {
       //    System.out.println(pr.getName());
       // }


        ProjectDTO projectDTO2 = new ProjectDTO();

        projectDTO2.setId("12345");
        projectDTO2.setName("!!!PUTIN!!!");
        projectDTO2.setDescription("1111111111wwwwwwwwwwwwwwwwwwwww");
        projectDTO2.setDateStart(new Date());
        projectDTO2.setDateFinish(new Date());
        projectDTO2.setDateCreate(new Date());
        projectDTO2.setStatus(Status.PLANNED);
        projectDTO2.setUserId("11111111-1111-1111-1111-111111111111");

        psh.editEntity(projectDTO2.getId(),projectDTO2, projectDTO.getUserId());

       // psh.removeEntity(projectDTO2.getId(), projectDTO2.getUserId());

       // psh.clearEntity(projectDTO2.getUserId());*/
    }
}