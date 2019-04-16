package ru.khmelev.tm;

import ru.khmelev.tm.entity.Status;
import ru.khmelev.tm.entity.Task;
import ru.khmelev.tm.service.ProjectServiceMyBatis;
import ru.khmelev.tm.service.TaskServiceMyBatis;

import java.util.Date;

public final class ApplicationServer {
    public static void main(String[] args) throws Exception {

        // Bootstrap bootstrap = new Bootstrap();
        //bootstrap.init();


//        SqlSessionFactory sqlSessionFactory;
//        SubscriberMapper subscriberMapper;
//        Reader reader = null;
//        try {
//            reader = Resources
//                    .getResourceAsReader("mybatis-config.xml"); //Читаем файл с настройками подключения и настройками MyBatisConfig
//            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
//            subscriberMapper = sqlSessionFactory.openSession().getMapper(SubscriberMapper.class); //Создаем маппер, из которого и будем вызывать методы getSubscriberById и getSubscribers
//            List<Subscriber> subscribers = subscriberMapper.getSubscribers();
//            Subscriber subscriber = subscriberMapper.getSubscriberById(101);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


//        Project pr = new Project();
//        pr.setName("PUTIN");
//        ProjectServiceMyBatis ps = new ProjectServiceMyBatis();
//        ps.editEntity("2ab64f9c-e77c-40f7-8818-45e9bd57f7b5", pr, "11111111-1111-1111-1111-111111111111");
//        Project pr2 = ps.findEntity("2ab64f9c-e77c-40f7-8818-45e9bd57f7b5", "11111111-1111-1111-1111-111111111111");
//        System.out.println(pr2.getName());
//
//
//        for (Project pr3 :
//                ps.findAll("11111111-1111-1111-1111-111111111111")) {
//            System.out.println(pr3.getName());
//
//        }
//        //ps.removeEntity("92b8c7a4-4aab-4ce3-8877-6f8547e4aa37","11111111-1111-1111-1111-111111111111" );
//        ps.clearEntity("a89691e7-8f23-4b47-8a21-9b99bfe52dd3");

//   Project project = new Project();
//   project.setName("www");
//   project.setId("111");
//   project.setDateCreate(new Date());
//   project.setDateStart(new Date());
//   project.setDateFinish(new Date());
//   project.setStatus(Status.PLANNED);
//   project.setUserId("11111111-1111-1111-1111-111111111111");
//



        TaskServiceMyBatis ts = new TaskServiceMyBatis();


        Task task = new Task();
        task.setName("www");
        task.setId("111");
        task.setDateCreate(new Date());
        task.setDateStart(new Date());
        task.setDateFinish(new Date());
        task.setStatus(Status.PLANNED);
        task.setUserId("11111111-1111-1111-1111-111111111111");
        task.setProjectId("9448968b-d070-481c-a672-9d077f54d089");

        //ts.createEntity(task.getId(), task);

        // System.out.println(ts.findEntity(task.getId(), task.getUserId()).getName());
//
//
        // for (Task tss : ts.findAll(task.getUserId())) {
        //   System.out.println(tss.getName());


//
        //task.setName("www222222222222222");
        //ts.editEntity(task.getId(), task, task.getUserId());
//
//
        // ts.removeEntity(task.getId(), task.getUserId());
//
//        for (Task tss :
//                ts.listTaskFromProject(task.getProjectId(), task.getUserId())) {
//            System.out.println(tss.getName());
//
        ProjectServiceMyBatis ps = new ProjectServiceMyBatis();
        ps.removeEntity(task.getProjectId(),"11111111-1111-1111-1111-111111111111");

       // ts.removeAllTaskFromProject(task.getProjectId(), task.getUserId());
//        ts.clearEntity(task.getProjectId());
    }


}