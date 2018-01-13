/*
 Proyecto Java EE, DAGSS-2014
 */

package es.uvigo.esei.dagss.dominio.daos;

import es.uvigo.esei.dagss.dominio.entidades.Receta;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

@Stateless
@LocalBean
public class RecetaDAO extends GenericoDAO<Receta>{
 
    public List<Receta> buscarRecetasCliente(long id) {
        TypedQuery<Receta> q = em.createQuery("SELECT a FROM Receta AS a "
                                                   + "  WHERE a.prescripcion.paciente.id = :id", Receta.class);
        q.setParameter("id", id);

        return q.getResultList();
    }
    // Completar aqui
}
