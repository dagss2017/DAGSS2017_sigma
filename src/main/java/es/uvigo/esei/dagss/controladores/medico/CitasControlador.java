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

    public String atenderCita(Cita cita) {
        citaActual = cita;
        return "atencionCliente";
    }

    public String doCitaAusente() {
        citaActual.setEstado(EstadoCita.AUSENTE);
        citaActual = citaDAO.actualizar(citaActual);
        // Actualiza lista de citas
        citas = citaDAO.buscarPorMedico(medicoActual.getId());
        //Devuelve a la página de listado
        return "listadoCitas";
    }

    public String doFinalizarCita() {
        citaActual.setEstado(EstadoCita.COMPLETADA);
        citaActual = citaDAO.actualizar(citaActual);
        // Actualiza lista de citas
        citas = citaDAO.buscarPorMedico(medicoActual.getId());
        //Devuelve a la página de listado
        return "listadoCitas";
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
