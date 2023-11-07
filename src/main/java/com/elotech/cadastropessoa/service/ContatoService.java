package com.elotech.cadastropessoa.service;

import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ContatoService {
    public static boolean verificarEmail(String email) {
        boolean ehEmailInvalido = false;
        if (email != null && email.length() > 0) {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                ehEmailInvalido = true;
            }
        }
        return ehEmailInvalido;
    }
}
