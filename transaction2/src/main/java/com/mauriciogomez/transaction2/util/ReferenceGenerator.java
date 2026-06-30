package com.mauriciogomez.transaction2.util;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class ReferenceGenerator {

    private static final SecureRandom RANDOM = new SecureRandom();

    public String generateReference() {
        int reference = 100000 + RANDOM.nextInt(900000);
        return String.valueOf(reference);
    }
}
