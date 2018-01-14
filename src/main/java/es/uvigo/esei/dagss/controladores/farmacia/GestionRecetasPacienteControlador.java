/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uvigo.esei.dagss.controladores.farmacia;

import es.uvigo.esei.dagss.dominio.daos.PacienteDAO;
import es.uvigo.esei.dagss.dominio.daos.RecetaDAO;
import es.uvigo.esei.dagss.dominio.entidades.EstadoReceta;
import es.uvigo.esei.dagss.dominio.entidades.Paciente;
import es.uvigo.esei.dagss.dominio.entidades.Prescripcion;
import es.uvigo.esei.dagss.dominio.entidades.Receta;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Silvia
 */
@Named(value = "gestionRecetasPacienteControlador")
@SessionScoped
public class GestionRecetasPacienteControlador implements Serializable {

    private String numTarjeta;
    private Paciente pacienteActual;
    private List<Receta> recetas;
    private Receta recetaActual;

    @Inject
    private PacienteDAO pacienteDAO;

    @Inject
    private RecetaDAO recetaDAO;

    public GestionRecetasPacienteControlador() {
    }

    public Paciente getPacienteActual() {
        return pacienteActual;
    }

    public void setPacienteActual(Paciente pacienteActual) {
        this.pacienteActual = pacienteActual;
    }

    public String getNumTarjeta() {
        return numTarjeta;
    }

    public void setNumTarjeta(String nss) {
        this.numTarjeta = nss;
    }

    public List<Receta> getRecetas() {
        return recetas;
    }

    public void setRecetas(List<Receta> recetas) {
        this.recetas = recetas;
    }

    private boolean parametrosAccesoInvalidos() {
        return this.numTarjeta.equals("");
    }

    public String searchPaciente() {
        String destino = null;

        if (parametrosAccesoInvalidos()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "No se ha indicado un Nº de tarjeta sanitaria", ""));
        } else {
            Paciente paciente = pacienteDAO.buscarPorTarjetaSanitaria(numTarjeta);
            if (paciente == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "No existe el usuario con el número de tarjeta sanitaria " + numTarjeta, ""));
            } else {
                pacienteActual = paciente;
                recetas = recetaDAO.buscarRecetasCliente(paciente.getId());
                destino = "recetas/listadoRecetasPaciente";
            }
        }
        return destino;
    }

    public String doVolver() {
        return "../login?faces-redirect=true";
    }
    
    public String subministrar(Receta receta) {
        receta.setEstadoReceta(EstadoReceta.SERVIDA);
        this.recetaActual = receta;
        recetaDAO.actualizar(receta);
        recetas = recetaDAO.buscarRecetasCliente(pacienteActual.getId());
        return "listadoRecetasPaciente";
    }
    
    public String mostrarSubministrarBoton(Receta receta) {
        if (receta.getEstado().getEtiqueta().equals("GENERADA")) {
            return "true";
        } else {
            return "false";
        }
    }
}
