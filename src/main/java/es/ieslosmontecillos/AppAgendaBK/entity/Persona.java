package es.ieslosmontecillos.AppAgendaBK.entity;
import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;
@Entity
@Table(name="PERSONA")
public class Persona implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @Basic
    @Column(name = "NOMBRE", nullable = false, length = 40)
    private String nombre;


    @Basic
    @Column(name = "APELLIDOS", nullable = false, length = 40)
    private String apellidos;


    @Basic
    @Column(name = "TELEFONO", nullable = true, length = 15)
    private String telefono;

    @Basic
    @Column(name = "EMAIL", nullable = true, length = 30)
    private String email;

    @ManyToOne
    @JoinColumn (name = "PROVINCIA", nullable = false)
    private Provincia provincia;

    @Basic
    @Column(name = "FECHA", nullable = true, length = 10)
    private Date fecha;

    @Basic
    @Column(name = "NUM_HIJOS", nullable = true)
    private short num_hijos;

    @Basic
    @Column(name = "ESTADO_CIVIL", nullable = false, length = 1)
    private  char estadoCivil;

    @Basic
    @Column(name = "SALARIO", nullable = true, precision = 9, scale = 2)
    private BigDecimal salario;


    @Basic
    @Column(name = "JUBILADO", nullable = true)
    private short jubilado;

    @Basic
    @Column(name = "FOTO", nullable = true, length = 30)
    private String foto;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public short getNum_hijos() {
        return num_hijos;
    }

    public void setNum_hijos(short num_hijos) {
        this.num_hijos = num_hijos;
    }

    public char getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(char estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public short getJubilado() {
        return jubilado;
    }

    public void setJubilado(short jubilado) {
        this.jubilado = jubilado;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return getProvincia() == persona.getProvincia() && getNum_hijos() == persona.getNum_hijos() && getEstadoCivil() == persona.getEstadoCivil() && getJubilado() == persona.getJubilado() && Objects.equals(getId(), persona.getId()) && Objects.equals(getNombre(), persona.getNombre()) && Objects.equals(getApellidos(), persona.getApellidos()) && Objects.equals(getTelefono(), persona.getTelefono()) && Objects.equals(getEmail(), persona.getEmail()) && Objects.equals(getFecha(), persona.getFecha()) && Objects.equals(getSalario(), persona.getSalario()) && Objects.equals(getFoto(), persona.getFoto());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNombre(), getApellidos(), getTelefono(), getEmail(), getProvincia(), getFecha(), getNum_hijos(), getEstadoCivil(), getSalario(), getJubilado(), getFoto());
    }
}
