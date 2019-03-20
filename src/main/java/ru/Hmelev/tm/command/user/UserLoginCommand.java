package ru.Hmelev.tm.command.user;

import com.google.common.hash.Hashing;
import ru.Hmelev.tm.Bootstrap;
import ru.Hmelev.tm.command.Command;
import ru.Hmelev.tm.service.UserService;

import javax.xml.ws.Service;
import java.io.IOException;

public class UserLoginCommand extends Command {
    public UserLoginCommand(Bootstrap bootstrap) {
        super(bootstrap, "user-login", "descriptionCommand");
    }

    @Override
    public void execute() throws IOException {
        System.out.println("!!!Start command!!!");
        System.out.println("Введите логин пользователя");
        String login = reader.readLine();
        System.out.println("Введите пароль пользователя");
        String password = reader.readLine();
        if(userService.userLogin(login, password))
            System.out.println("URAAAAAAAAAA");;
    }
}