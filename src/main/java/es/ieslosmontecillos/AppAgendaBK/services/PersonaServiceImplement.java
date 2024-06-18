package es.ieslosmontecillos.AppAgendaBK.services;
import es.ieslosmontecillos.AppAgendaBK.dao.PersonaDao;
import es.ieslosmontecillos.AppAgendaBK.entity.Persona;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PersonaServiceImplement implements PersonaService {
    @Autowired
    private PersonaDao personaDao;
    @Override
    public List<Persona> findAll() {
        return  personaDao.findAll();
    }
    @Override
    public Persona save(Persona persona) {
        return personaDao.save(persona);
    }
    @Override
    public Persona findById(Long id) {
        return personaDao.findById(id).orElse(null);
    }
    @Override
    public void delete(Persona persona) {
        personaDao.delete(persona);
    }

    @Override
    public ResponseEntity<Resource> exportContacts() throws JRException, FileNotFoundException {
        try{
        File file = ResourceUtils.getFile("classpath:jrxml/repClientes.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource((Collection<?>) this.todasPersonas());
        Map<String, Object> parameters = new HashMap<>();
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        //La utilidad JasperExportManager exporta a pdf
        byte[] report = JasperExportManager.exportReportToPdf(jasperPrint);
        String sdf = (new SimpleDateFormat("dd/MM/yyyy")).format(new Date());
        StringBuilder stringBuilder = new StringBuilder().append("PDF_");
        ContentDisposition contentDisposition = ContentDisposition.builder("attachment")
                .filename(stringBuilder.append(1)
                        .append("ListadoClientes_")
                        .append(sdf)
                        .append(".pdf")
                        .toString())
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(contentDisposition);
        return ResponseEntity.ok().contentLength((long) report.length)
                .contentType(MediaType.APPLICATION_PDF)
                .headers(headers).body(new ByteArrayResource(report));
    } catch (Exception e) {
        e.printStackTrace();
    }
        return null;
    }

    private List<PersonaReport> todasPersonas() {

        final List<PersonaReport> personasReports = new ArrayList<>();
        final List<Persona> personas = personaDao.listClient();
        personas.forEach(p -> {
            personasReports.add(new PersonaReport(p.getId(),p.getApellidos(),p.getEmail(),p.getEstadoCivil(),p.getFecha(),p.getFoto(),p.getJubilado(),p.getNombre(),p.getNum_hijos(),p.getSalario(),p.getTelefono(),p.getProvincia()));
        });
        return personasReports;

    }
}
