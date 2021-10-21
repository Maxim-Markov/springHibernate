package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car("Chevrolet", "Cruze")));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car("BMW", "e39")));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car("Skoda", "Kodiaq")));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car("Audi", "100")));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            if (user.getCar() != null) {
                System.out.println("Car model = " + user.getCar().getModel());
                System.out.println("Car series = " + user.getCar().getSeries());
            }
            System.out.println();
        }

        System.out.println(userService.getUserbyCar(new Car("Audi", "100")).toString());

        context.close();
    }
}
