package com.f1sb.forma1sb.service;

import com.f1sb.forma1sb.model.MessageDTO;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MessageService {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public MessageService(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public int addMessage (MessageDTO messageDTO) {
        String sql = "INSERT INTO uzenet (felhasznalo_id, uzenet) VALUES (:felhasznaloId, :uzenet)";

        Map<String, Object> params = new HashMap<>();
        params.put("felhasznaloId", messageDTO.getUserId());
        params.put("uzenet", messageDTO.getMessage());

        try {
            return namedParameterJdbcTemplate.update(sql, params);
        } catch (Exception e) {
            return 0;
        }
    }

    public List<MessageDTO> showMessages() {
        String sql = "SELECT * FROM uzenet order by id desc";
        MapSqlParameterSource params = new MapSqlParameterSource();
        List<Map<String, Object>> resultList = namedParameterJdbcTemplate.queryForList(sql, params);

        List<MessageDTO> messageDTOList = new ArrayList<>();

        for (Map<String, Object> row : resultList) {
            int id = (int) row.get("id");
            Integer userId = (Integer) row.get("felhasznalo_id");
            String message = (String) row.get("uzenet");
            Timestamp createdDate = (Timestamp) row.get("rogzites");

            MessageDTO messageDTO = new MessageDTO(userId, id, message, createdDate);
            messageDTOList.add(messageDTO);
        }

        return messageDTOList;
    }
}
