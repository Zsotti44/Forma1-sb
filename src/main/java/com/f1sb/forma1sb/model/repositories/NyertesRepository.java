package com.f1sb.forma1sb.model.repositories;

import com.f1sb.forma1sb.model.NationWinsResponse;
import com.f1sb.forma1sb.model.Nyertes;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface NyertesRepository extends CrudRepository<Nyertes, Date> {

    @Query("SELECT new com.f1sb.forma1sb.model.NationWinsResponse(n.nemzet, COUNT(1)) FROM Nyertes n GROUP BY n.nemzet order by count(1) desc")
    List<NationWinsResponse> findNationWins();
}
