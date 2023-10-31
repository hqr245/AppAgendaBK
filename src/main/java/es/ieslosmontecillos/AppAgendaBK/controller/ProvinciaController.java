package es.ieslosmontecillos.AppAgendaBK.controller;
import es.ieslosmontecillos.AppAgendaBK.entity.Provincia;
import es.ieslosmontecillos.AppAgendaBK.services.ProvinciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/api/v1")
public class ProvinciaController {
    @Autowired
    private ProvinciaService provinciaService;
    @GetMapping(value="/PROVINCIA")
    public ResponseEntity<Object> get(){
        Map<String, Object> map = new HashMap<>();
        try {
            List<Provincia> list = provinciaService.findAll();
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
        catch (Exception e) {
            map.put("message", e.getMessage());
            return new ResponseEntity<>( map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value="/PROVINCIA/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id){
        try {
            Provincia data = provinciaService.findById(id);
            return new ResponseEntity<>(data, HttpStatus.OK);
        }
        catch (Exception e) {
            Map<String, Object> map = new HashMap<>();
            map.put("message", e.getMessage());
            return new ResponseEntity<>( map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping(value="/PROVINCIA")
    public ResponseEntity<Object> create(@RequestBody Provincia provincia){
        Map<String, Object> map = new HashMap<>();
        try {
            Provincia res = provinciaService.save(provincia);
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        catch (Exception e) {
            map.put("message", e.getMessage());
            return new ResponseEntity<>( map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/PROVINCIA/{id}")
    public ResponseEntity<Object> update(@RequestBody Provincia provincia,
                                         @PathVariable Long id){
        Map<String, Object> map = new HashMap<>();
        try {
            Provincia currentProvincia = provinciaService.findById(id);
            currentProvincia.setNombre(provincia.getNombre());
            currentProvincia.setCodigo(provincia.getCodigo());
            Provincia res = provinciaService.save(currentProvincia);
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        catch (Exception e) {
            map.put("message", e.getMessage());
            return new ResponseEntity<>( map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/PROVINCIA/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id){
        Map<String, Object> map = new HashMap<>();
        try {Provincia currentProvincia = provinciaService.findById(id);
            provinciaService.delete(currentProvincia);
            map.put("deleted", true);
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
        catch (Exception e) {
            map.put("message", e.getMessage());
            return new ResponseEntity<>( map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

