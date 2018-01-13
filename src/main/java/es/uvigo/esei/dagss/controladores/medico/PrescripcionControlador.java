package es.uvigo.esei.dagss.controladores.medico;

import es.uvigo.esei.dagss.dominio.daos.PrescripcionDAO;
import es.uvigo.esei.dagss.dominio.entidades.Cita;
import es.uvigo.esei.dagss.dominio.entidades.EstadoCita;
import es.uvigo.esei.dagss.dominio.entidades.Medico;
import es.uvigo.esei.dagss.dominio.entidades.Paciente;
import es.uvigo.esei.dagss.dominio.entidades.Prescripcion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 *
 * @author Silvia
 */
@Named(value = "prescripcionControlador")
@SessionScoped
public class PrescripcionControlador implements Serializable {

    @Inject
    MedicoControlador medicoControlador;
    
    @Inject
    CitasControlador citasControlador;

    @Inject
    PrescripcionDAO prescripcionDAO;


    private List<Prescripcion> prescripciones;
    private Cita citaActual;
    private Medico medicoActual;

    public PrescripcionControlador() {
    }

    @PostConstruct
    public void inicializar() {
        medicoActual = medicoControlador.getMedicoActual();
        citaActual = citasControlador.getCitaActual();
        prescripciones = prescripcionDAO.buscarPorPaciente(citaActual.getPaciente().getId());
        System.out.println(prescripciones.size());
    }

    public Cita getCitaActual() {
        return citaActual;
    }

    public void setCitaActual(Cita citaActual) {
        this.citaActual = citaActual;
    }

   public List<Prescripcion> getPrescripciones() {
        return prescripciones;
    }

    public void setPrescripciones(List<Prescripcion> prescripciones) {
        this.prescripciones = prescripciones;
    }
    
    public Medico getMedicoActual() {
        return medicoActual;
    }

    public void setMedicoActual(Medico medicoActual) {
        this.medicoActual = medicoActual;
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
