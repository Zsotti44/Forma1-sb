package com.f1sb.forma1sb.service;

import com.f1sb.forma1sb.model.UserDTO;
import com.f1sb.forma1sb.utils.PasswordUtils;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public UserService(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public int registration(String userName, String password) {
        String salt = PasswordUtils.generateSalt();
        String hashedPassword = PasswordUtils.hashPassword(password + salt);

        String sql = "INSERT INTO felhasznalo (felhasznalo_nev, jelszo, salt) VALUES (:felhasznalo_nev, :jelszo, :salt)";
        Map<String, Object> params = new HashMap<>();
        params.put("felhasznalo_nev", userName);
        params.put("jelszo", hashedPassword);
        params.put("salt", salt);

        //TODO: valami beszédesebb hibakezelés
        try {
            return namedParameterJdbcTemplate.update(sql, params);
        } catch (Exception e) {
            return 0;
        }
    }

    public UserDTO verifyPassword(String userName, String password) {
        String sql = "SELECT id, jelszo, salt, jog FROM felhasznalo WHERE felhasznalo_nev = :felhasznalo_nev";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("felhasznalo_nev", userName);

        Map<String, Object> result = namedParameterJdbcTemplate.queryForMap(sql, params);

        if (result == null || result.isEmpty()) {
            return null; // A felhasználó nem található
        }

        UserDTO user = new UserDTO(
                (int) result.get("id"),
                userName,
                (String) result.get("jelszo"),
                ((Number) result.get("jog")).byteValue(),
                (String) result.get("salt")
        );

        // Hasheljük a megadott jelszót a tárolt sóval
        String hashedInputPassword = PasswordUtils.hashPassword(password + user.getSalt());

        // Összehasonlítjuk a hashelt jelszót
        if (user.getPassword().equals(hashedInputPassword)) {
            return user;
        } else {
            return null;
        }
    }
}
