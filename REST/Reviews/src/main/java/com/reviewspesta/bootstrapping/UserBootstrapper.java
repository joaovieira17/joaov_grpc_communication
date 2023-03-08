package com.reviewspesta.bootstrapping;

import com.reviewspesta.model.Role;
import com.reviewspesta.model.User;
import com.reviewspesta.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
@Profile("bootstrap")
public class UserBootstrapper implements CommandLineRunner {

    @Autowired
    private UserRepository userRepo;


    @Autowired
    private PasswordEncoder encoder;

    @Override
    public void run(String... args) {

        if (userRepo.findByUsername("Andre") == null) {
            User u1 = new User("Andre", encoder.encode("passdoandre"),"andre@gmail.com","");
            u1.addAuthority(new Role(Role.REG_USER));
            userRepo.save(u1);
        }


        if (userRepo.findByUsername("Bruno") == null) {
            User u2 = new User("Bruno", encoder.encode("passdobruno"),"bruno@gmail.com","");
            u2.addAuthority(new Role(Role.REG_USER));
            userRepo.save(u2);
        }

        if (userRepo.findByUsername("Joao") == null) {
            User u3 = new User("Joao", encoder.encode("passdojoao"),"joao@gmail.com","");
            u3.addAuthority(new Role(Role.REG_USER));
            userRepo.save(u3);
        }

        if (userRepo.findByUsername("Joana") == null) {
            User u4 = new User("Joana", encoder.encode("passdajoana"),"joana@gmail.com","");
            u4.addAuthority(new Role(Role.REG_USER));
            userRepo.save(u4);
        }

        if (userRepo.findByUsername("Albert") == null) {
            User u5 = new User("Albert", encoder.encode("passdoalbert"),"albert@gmail.com","");
            u5.addAuthority(new Role(Role.MODERATOR));
            userRepo.save(u5);
        }

        if (userRepo.findByUsername("Quim") == null) {
            User u6 = new User("Quim", encoder.encode("passdoquim"),"quim@gmail.com","");
            u6.addAuthority(new Role(Role.REG_USER));
            userRepo.save(u6);
        }
    }

}
