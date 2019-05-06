package ru.khmelev.tm;

import ru.khmelev.tm.bootstrap.Bootstrap;

import javax.enterprise.inject.se.SeContainerInitializer;

public final class ApplicationServer {
    public static void main(String[] args) {
        SeContainerInitializer.newInstance()
                .addPackages(ApplicationServer.class).initialize().select(Bootstrap.class).get().init();
    }
}

//        taskDTO.setDescription("sdadsa");
//        taskDTO.setDateStart(new Date());
//        taskDTO.setDateFinish(new Date());
//        taskDTO.setDateCreate(new Date());
//        taskDTO.setStatus(Status.PLANNED);
//        taskDTO.setProjectId(projectDTO2.getId());
//        taskDTO.setUserId("11111111-1111-1111-1111-111111111111");
//
// tsh.createEntity(taskDTO.getId(), taskDTO);


//        System.out.println(tsh.findEntity("123456789", "11111111-1111-1111-1111-111111111111").getName());
//        for (TaskDTO taskDTO1 : tsh.findAll("11111111-1111-1111-1111-111111111111")) {
//            System.out.println(taskDTO1.getName());
//        }
//
//        TaskDTO taskDTO2 = new TaskDTO();
//
//        taskDTO2.setId("123456789");
//        taskDTO2.setName("PUTIN");
//        taskDTO2.setDescription("sdadsa");
//        taskDTO2.setDateStart(new Date());
//        taskDTO2.setDateFinish(new Date());
//        taskDTO2.setDateCreate(new Date());
//        taskDTO2.setStatus(Status.PLANNED);
//        taskDTO2.setProjectId(projectDTO2.getId());
//        taskDTO2.setUserId("11111111-1111-1111-1111-111111111111");

//tsh.editEntity(taskDTO2.getId(), taskDTO2, taskDTO2.getUserId());

//tsh.removeEntity(taskDTO.getId(), taskDTO.getUserId());

//psh.clearEntity(taskDTO2.getUserId());

//
//        UserService ush = new UserService();
//
//        UserDTO userDTO = new UserDTO();
//
//        userDTO.setId("12345");
//        userDTO.setHashPassword("wswddw");
//        userDTO.setLogin("MEDVEDEV");
//        userDTO.setRole(Role.USER);
//
//        ush.createEntity("12345", userDTO);

//        for (UserDTO userDTO1 : ush.findAll()) {
//            System.out.println(userDTO1.getLogin());
//        }

//        UserDTO userDTO2 = new UserDTO();
//
//        userDTO2.setId("123");
//        userDTO2.setHashPassword("wswddw");
//        userDTO2.setLogin("PUTIN");
//        userDTO2.setRole(Role.USER);

//        ush.editEntity(userDTO2.getId(), userDTO2);

//System.out.println(ush.findEntity("123").getLogin());
//ush.removeEntity("12345");


//        SessionService ssh = new SessionService();
//
//        SessionDTO sessionDTO = new SessionDTO();
//
//        sessionDTO.setId("123");
//        sessionDTO.setSignature("wswddw");
//        sessionDTO.setDateCreate(new Date());
//        sessionDTO.setUserId("11111111-1111-1111-1111-111111111111");

// ssh.createEntity("123", sessionDTO);

//System.out.println(ssh.findEntity("123").getSignature());

//  for (SessionDTO sessionDTO1 : ssh.findAll()) {
//        System.out.println(sessionDTO1.getSignature());
//    }

//        SessionDTO sessionDTO2 = new SessionDTO();
//
//        sessionDTO2.setId("1232");
//        sessionDTO2.setSignature("!!!PUTIN!!!");
//        sessionDTO2.setDateCreate(new Date());
//        sessionDTO2.setUserId("11111111-1111-1111-1111-111111111111");
//
//ssh.editEntity("123", sessionDTO2);
//
//System.out.println(ssh.findEntity("123").getSignature());
//        ush.removeEntity("12345");


//ssh.removeEntity("123");

//ssh.clearEntity();
//
//
//
//