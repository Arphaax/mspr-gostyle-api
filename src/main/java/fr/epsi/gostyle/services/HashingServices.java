package fr.epsi.gostyle.services;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Random;

@Service
public class HashingServices {
    private static final Random RANDOM = new SecureRandom();

    @Autowired
    Argon2 argon2 = Argon2Factory.create();


    /*
     * Hash un mot de passe utilisant l'algorythme Argon2
     * Le salt est généré automatiquement
     */
    public String hash(String motDePasse) {
        return argon2.hash(10, 65536, 1, motDePasse.toCharArray());
    }

    /*
     * Verifie si le mot de passe donné correspond au hash donné
     *
     * Return :
     * true : si le mot de passe est correct
     * false : si le mot de passe est erroné
     */
    public boolean verifier(String hash, String motDePasse) {
        return argon2.verify(hash, motDePasse.toCharArray());
    }
}
