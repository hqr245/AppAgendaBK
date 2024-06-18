package es.ieslosmontecillos.AppAgendaBK.controller;
import es.ieslosmontecillos.AppAgendaBK.entity.Persona;
import es.ieslosmontecillos.AppAgendaBK.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/api/v1")
public class PersonaController {
    @Autowired
    private PersonaService personaService;
    @GetMapping(value="/PERSONA")
    public ResponseEntity<Object> get(){
        Map<String, Object> map = new HashMap<>();
        try {
            List<Persona> list = personaService.findAll();
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
        catch (Exception e) {
            map.put("message", e.getMessage());
            return new ResponseEntity<>( map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value="/PERSONA/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id){
        try {
            Persona data = personaService.findById(id);
            return new ResponseEntity<>(data, HttpStatus.OK);
        }
        catch (Exception e) {
            Map<String, Object> map = new HashMap<>();
            map.put("message", e.getMessage());
            return new ResponseEntity<>( map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping(value="/PERSONA")
    public ResponseEntity<Object> create(@RequestBody Persona persona){
        Map<String, Object> map = new HashMap<>();
        try {
            Persona res = personaService.save(persona);
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        catch (Exception e) {
            map.put("message", e.getMessage());
            return new ResponseEntity<>( map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/PERSONA/{id}")
    public ResponseEntity<Object> update(@RequestBody Persona persona,
                                         @PathVariable Long id){
        Map<String, Object> map = new HashMap<>();
        try {
            Persona currentPersona = personaService.findById(id);
            currentPersona.setNombre(persona.getNombre());
            currentPersona.setApellidos(persona.getApellidos());
            currentPersona.setTelefono(persona.getApellidos());
            currentPersona.setEmail(persona.getEmail());
            currentPersona.setProvincia(persona.getProvincia());
            currentPersona.setFecha(persona.getFecha());
            currentPersona.setNum_hijos(persona.getNum_hijos());
            currentPersona.setEstadoCivil(persona.getEstadoCivil());
            currentPersona.setSalario(persona.getSalario());
            currentPersona.setJubilado(persona.getJubilado());
            currentPersona.setFoto(persona.getFoto());


            Persona res = personaService.save(currentPersona);
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        catch (Exception e) {
            map.put("message", e.getMessage());
            return new ResponseEntity<>( map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/PERSONA/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id){
        Map<String, Object> map = new HashMap<>();
        try {
            Persona currentPersona = personaService.findById(id);
            personaService.delete(currentPersona);
            map.put("deleted", true);
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
        catch (Exception e) {
            map.put("message", e.getMessage());
            return new ResponseEntity<>( map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

