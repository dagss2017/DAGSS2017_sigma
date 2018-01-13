/*
 Proyecto Java EE, DAGSS-2014
 */

package es.uvigo.esei.dagss.dominio.daos;

import es.uvigo.esei.dagss.dominio.entidades.Cita;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;


@Stateless
@LocalBean
public class CitaDAO  extends GenericoDAO<Cita>{    
    
    public List<Cita> buscarPorMedico(long id) {
        Date fechaActual = Calendar.getInstance().getTime();
        TypedQuery<Cita> q = em.createQuery("SELECT c FROM Cita AS c "
                + "  WHERE (c.medico.id = :id) AND (c.fecha = :fechaActual)", Cita.class);
        q.setParameter("id",id);
        q.setParameter("fechaActual", fechaActual);
        return q.getResultList();
    }
    // Completar aqui
}
