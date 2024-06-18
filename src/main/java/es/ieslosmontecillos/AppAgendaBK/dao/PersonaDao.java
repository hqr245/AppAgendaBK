package es.ieslosmontecillos.AppAgendaBK.dao;
import es.ieslosmontecillos.AppAgendaBK.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaDao extends JpaRepository<Persona, Long> {

    @Query("SELECT c FROM Persona c ORDER BY c.apellidos ASC")
    List<Persona> listClient();

}