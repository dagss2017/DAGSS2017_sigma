package es.uvigo.esei.dagss.controladores.medico;

import es.uvigo.esei.dagss.dominio.daos.CitaDAO;
import es.uvigo.esei.dagss.dominio.daos.MedicamentoDAO;
import es.uvigo.esei.dagss.dominio.daos.PrescripcionDAO;
import es.uvigo.esei.dagss.dominio.daos.RecetaDAO;
import es.uvigo.esei.dagss.dominio.entidades.Cita;
import es.uvigo.esei.dagss.dominio.entidades.EstadoCita;
import es.uvigo.esei.dagss.dominio.entidades.EstadoReceta;
import es.uvigo.esei.dagss.dominio.entidades.Medicamento;
import es.uvigo.esei.dagss.dominio.entidades.Medico;
import es.uvigo.esei.dagss.dominio.entidades.Prescripcion;
import es.uvigo.esei.dagss.dominio.entidades.Receta;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
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
    PrescripcionDAO prescripcionDAO;

    @Inject
    MedicamentoDAO medicamentoDAO;
    
    @Inject
    RecetaDAO recetaDAO;

    private List<Prescripcion> prescripciones;
    private List<Cita> citas;
    private Cita citaActual;
    private Medico medicoActual;
    private Prescripcion prescripcionActual;
    private List<Medicamento> medicamentos;
    private Medicamento medicamentoActual;

    public CitasControlador() {
    }

    @PostConstruct
    public void inicializar() {
        medicoActual = medicoControlador.getMedicoActual();
        citas = citaDAO.buscarPorMedico(medicoActual.getId());
        medicamentos = medicamentoDAO.buscarTodos();
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

    public List<Prescripcion> getPrescripciones() {
        return prescripciones;
    }

    public void setPrescripciones(List<Prescripcion> prescripciones) {
        this.prescripciones = prescripciones;
    }

    public Prescripcion getPrescripcionActual() {
        return prescripcionActual;
    }

    public void setPrescripcionActual(Prescripcion prescripcionActual) {
        this.prescripcionActual = prescripcionActual;
    }

    public Medicamento getMedicamentoActual() {
        return medicamentoActual;
    }

    public void setMedicamentoActual(Medicamento medicamentoActual) {
        this.medicamentoActual = medicamentoActual;
    }

    public List<Medicamento> getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(List<Medicamento> medicamentos) {
        this.medicamentos = medicamentos;
    }

    public String atenderCita(Cita cita) {
        this.citaActual = cita;
        prescripciones = prescripcionDAO.buscarPorPaciente(citaActual.getPaciente().getId());
        return "atencionCliente";
    }

    public String doCitaAusente() {
        this.citaActual.setEstado(EstadoCita.AUSENTE);
        this.citaActual = citaDAO.actualizar(citaActual);
        // Actualiza lista de citas
        citas = citaDAO.buscarPorMedico(medicoActual.getId());
        //Devuelve a la página de listado
        return "listadoCitas";
    }

    public String doFinalizarCita() {
        this.citaActual.setEstado(EstadoCita.COMPLETADA);
        this.citaActual = citaDAO.actualizar(citaActual);
        // Actualiza lista de citas
        citas = citaDAO.buscarPorMedico(medicoActual.getId());
        //Devuelve a la página de listado
        return "listadoCitas";
    }

    public String doVolver() {
        return "../index?faces-redirect=true";
    }

    public void doEliminarPrescripcion(Prescripcion prescripcion) {
        prescripcionDAO.eliminar(prescripcion);
        prescripciones = prescripcionDAO.buscarPorPaciente(citaActual.getPaciente().getId());// Actualizar lista 
    }

    public void doVerPrescripcion(Prescripcion prescripcion) {
        prescripcionActual = prescripcion;   // Otra alternativa: volver a refrescarlos desde el DAO
    }

    public void seleccionarMedicamento(Medicamento medicamento) {
        medicamentoActual = medicamento;
    }

    public void doGuardarPrescripcionEditado() {
        // Actualiza un centro de salud
        prescripcionActual.setMedico(medicoActual);
        prescripcionActual.setMedicamento(medicamentoActual);
        prescripcionActual = prescripcionDAO.actualizar(prescripcionActual);
        prescripciones = prescripcionDAO.buscarPorPaciente(citaActual.getPaciente().getId());// Actualizar lista 
    }

    public void doNuevo() {
        prescripcionActual = new Prescripcion(); // Farmacia vacia
        prescripcionActual.setFechaInicio(Calendar.getInstance().getTime());
        prescripcionActual.setMedico(medicoActual);
        prescripcionActual.setPaciente(citaActual.getPaciente());
    }

    public void doGuardarNuevoPrescripcion() {
        // Crea un nuevo centro de salud
        prescripcionActual.setMedicamento(medicamentoActual);
        prescripcionActual = prescripcionDAO.crear(prescripcionActual);
        // Actualiza lista de centros de salud a mostrar
        prescripciones = prescripcionDAO.buscarPorPaciente(citaActual.getPaciente().getId());// Actualizar lista 
        crearPlanRecetas(prescripcionActual);
    }

    public String mostrarAtenderBoton(Cita cita) {
        if (cita.getEstado().getEtiqueta().equals("PLANIFICADA")) {
            return "true";
        } else {
            return "false";
        }
    }

    private void crearPlanRecetas(Prescripcion prescripcion) {
        
        double d = (prescripcion.getDosis() / prescripcion.getMedicamento().getNumeroDosis()) + 1;
        int cajas = (int)d;

        Date fechaInicial = Calendar.getInstance().getTime();
        Date fechaFinal = sumarRestarDiasFecha(fechaInicial, 7);
        
        for (int i = 0; i < cajas; i++) {
            
            if(sumarRestarDiasFecha(fechaFinal,-7).after(prescripcion.getFechaFin())){
                break;
            }
            
            fechaFinal = sumarRestarDiasFecha(fechaFinal, 7);
            
            //Las dos primeras veces no cambia la fecha inicial
            if(i>1){
                fechaInicial = sumarRestarDiasFecha(fechaInicial, 7);
            }
            
            Receta receta = new Receta(prescripcion, 1, fechaInicial, fechaFinal, EstadoReceta.GENERADA);
            recetaDAO.crear(receta);
        }
    }

    // Suma o resta los días recibidos a la fecha  
    public Date sumarRestarDiasFecha(Date fecha, int dias) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha); // Configuramos la fecha que se recibe
        calendar.add(Calendar.DAY_OF_YEAR, dias);  // numero de días a añadir, o restar en caso de días<0

        return calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos

    }
}
