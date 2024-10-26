package com.f1sb.forma1sb.model.repositories;

import com.f1sb.forma1sb.model.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface NyertesRepository extends CrudRepository<Nyertes, Date> {

    @Query("SELECT new com.f1sb.forma1sb.model.NationWinsResponse(n.nemzet, COUNT(1)) FROM Nyertes n GROUP BY n.nemzet order by count(1) desc")
    List<NationWinsResponse> findNationWins();

    @Query("SELECT new com.f1sb.forma1sb.model.TeamWinsResponse(n.csapat, COUNT(1)) FROM Nyertes n GROUP BY n.csapat order by count(1) desc")
    List<TeamWinsResponse> findTeamWins();

    @Query("SELECT new com.f1sb.forma1sb.model.PilotaWinsReponse(n.nev, COUNT(1)) FROM Nyertes n GROUP BY n.nev order by count(1) desc")
    List<PilotaWinsReponse> findPilotaWins();

    @Query("SELECT new com.f1sb.forma1sb.model.GPReponse(n.helyszin, COUNT(1)) FROM Nyertes n GROUP BY n.helyszin order by count(1) desc")
    List<GPReponse> findGPs();
}
