package es.ieslosmontecillos.AppAgendaBK.services;
import es.ieslosmontecillos.AppAgendaBK.entity.Persona;
import net.sf.jasperreports.engine.JRException;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import java.io.FileNotFoundException;
import java.util.List;
public interface PersonaService {
    public List<Persona> findAll();

    public Persona save(Persona persona);

    public Persona findById(Long id);

    public void delete(Persona persona);

    ResponseEntity<Resource> exportContacts() throws JRException, FileNotFoundException;
}