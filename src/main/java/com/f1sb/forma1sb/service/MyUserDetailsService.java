package com.f1sb.forma1sb.service;

import com.f1sb.forma1sb.model.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Primary
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String sql = "SELECT id, felhasznalo_nev, jelszo, jog FROM felhasznalo WHERE felhasznalo_nev = :username";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("username", username);

        try {
            // Felhasználó betöltése az adatbázisból
            Map<String, Object> result = jdbcTemplate.queryForMap(sql, params);

            int userId = (Integer) result.get("id");
            String felhasznaloNev = (String) result.get("felhasznalo_nev");
            String jelszo = (String) result.get("jelszo");
            String role = ((Integer) result.get("jog")).toString();

            // CustomUserDetails létrehozása a lekérdezett adatok alapján
            return new CustomUserDetails(userId, felhasznaloNev, jelszo, AuthorityUtils.createAuthorityList(role));
        } catch (Exception e) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
    }

    public int registration(String username, String encodedPassword) {
        String sql = "INSERT INTO felhasznalo (felhasznalo_nev, jelszo) VALUES (:username, :password)";
        Map<String, Object> params = new HashMap<>();
        params.put("username", username);
        params.put("password", encodedPassword);

        try {
            return jdbcTemplate.update(sql, params);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public String getUsernameById(int id){
        String sql = "SELECT felhasznalo_nev FROM felhasznalo WHERE id = :id";
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);

        try {
            return jdbcTemplate.queryForObject(sql, params, String.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Integer getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Ellenőrizzük, hogy a hitelesítés érvényes és a felhasználó egy CustomUserDetails típusú
        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

            // Lekérjük a userId-t a CustomUserDetails-ből
            return userDetails.getUserid();
        } else {
            return null; // Ha a felhasználó nincs hitelesítve
        }
    }
}