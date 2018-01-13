package es.uvigo.esei.dagss.controladores.medico;

import es.uvigo.esei.dagss.dominio.daos.CitaDAO;
import es.uvigo.esei.dagss.dominio.daos.MedicoDAO;
import es.uvigo.esei.dagss.dominio.daos.PacienteDAO;
import es.uvigo.esei.dagss.dominio.entidades.Cita;
import es.uvigo.esei.dagss.dominio.entidades.EstadoCita;
import es.uvigo.esei.dagss.dominio.entidades.Medico;
import es.uvigo.esei.dagss.dominio.entidades.Paciente;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 *
 * @author dagss
 */
@Named(value = "citasControlador")
@SessionScoped
public class CitasControlador implements Serializable {

    @Inject
    MedicoControlador medicoControlador;

    @Inject
    CitaDAO citaDAO;

    @Inject
    MedicoDAO medicoDAO;

    @Inject
    PacienteDAO pacienteDAO;

    private List<Cita> citas;
    private Cita citaActual;
    private Medico medicoActual;

    public CitasControlador() {
    }

    @PostConstruct
    public void inicializar() {
        medicoActual = medicoControlador.getMedicoActual();
        citas = citaDAO.buscarPorMedico(medicoActual.getId());
    }

    /*
       Usado en la lista desplegable con los estados de una cita
     */
    public EstadoCita[] getEstadosCitas() {
        return EstadoCita.values();
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }

    public Cita getCitaActual() {
        return citaActual;
    }

    public void setCitaActual(Cita citaActual) {
        this.citaActual = citaActual;
    }

    public List<Paciente> getListadoPacientes() {
        return pacienteDAO.buscarTodos();
    }

    public List<Medico> getListadoMedicos() {
        return medicoDAO.buscarTodos();
    }

    public void doEliminar(Cita cita) {
        System.out.println(">>> llama a elimina con " + cita);
        citaDAO.eliminar(cita);
        citas = citaDAO.buscarTodos(); // Actualizar lista de centros
    }

    public void doNuevo() {

    }

    public void doVer(Cita cita) {
        citaActual = cita;   // Otra alternativa: volver a refrescarlos desde el DAO
    }

    public String atenderCita(Cita cita) {
        citaActual = cita;
        return "atencionCliente";
    }

    public void doGuardarNuevo() {
        // Crea un nuevo centro de salud
        citaActual = citaDAO.crear(citaActual);
        // Actualiza lista de centros de salud a mostrar
        citas = citaDAO.buscarTodos();

    }

    public void doGuardarEditado() {
        // Actualiza un centro de salud
        citaActual = citaDAO.actualizar(citaActual);
        // Actualiza lista de centros de salud a mostrar
        citas = citaDAO.buscarTodos();
    }

    public String doVolver() {
        return "../index?faces-redirect=true";
    }

    public String mostrarAtenderBoton(Cita cita) {
        if (cita.getEstado().getEtiqueta().equals("PLANIFICADA")) {
            return "true";
        } else {
            return "false";
        }
    }

}