package com.example.kolo_fortuny.password;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

@Controller
public class PasswordController {

    private final PasswordRepository passwordRepository;

    public PasswordController(PasswordRepository passwordRepository) {
        this.passwordRepository = passwordRepository;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/password")
    @ResponseBody
    public Password getPassword() throws NoSuchAlgorithmException {
        Random rand = SecureRandom.getInstanceStrong();
        List<String> passwords = WordList.getWORDS();
        int index = rand.nextInt(passwords.size());
        String passwordText = passwords.get(index);
        System.out.println("Ustawiam haslo na " + passwordText);

        Password password = new Password(passwordText.replaceAll("(\\w)", "*"));
        passwordRepository.setPassword(password);

        return password;
    }

}
