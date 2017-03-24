/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Epulapp
 */
@Entity
@Table(name = "droits")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Droits.findAll", query = "SELECT d FROM Droits d")
    , @NamedQuery(name = "Droits.findByIdAuteur", query = "SELECT d FROM Droits d WHERE d.droitsPK.idAuteur = :idAuteur")
    , @NamedQuery(name = "Droits.findByDateTrimestre", query = "SELECT d FROM Droits d WHERE d.droitsPK.dateTrimestre = :dateTrimestre")
    , @NamedQuery(name = "Droits.findByEtatDroits", query = "SELECT d FROM Droits d WHERE d.etatDroits = :etatDroits")
    , @NamedQuery(name = "Droits.findByMontantsDroits", query = "SELECT d FROM Droits d WHERE d.montantsDroits = :montantsDroits")})
public class Droits implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DroitsPK droitsPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "etat_droits")
    private Character etatDroits;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "montants_droits")
    private BigDecimal montantsDroits;
    @JoinColumn(name = "id_auteur", referencedColumnName = "id_auteur", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Auteur auteur;

    public Droits() {
    }

    public Droits(DroitsPK droitsPK) {
        this.droitsPK = droitsPK;
    }

    public Droits(DroitsPK droitsPK, Character etatDroits) {
        this.droitsPK = droitsPK;
        this.etatDroits = etatDroits;
    }

    public Droits(int idAuteur, Date dateTrimestre) {
        this.droitsPK = new DroitsPK(idAuteur, dateTrimestre);
    }

    public DroitsPK getDroitsPK() {
        return droitsPK;
    }

    public void setDroitsPK(DroitsPK droitsPK) {
        this.droitsPK = droitsPK;
    }

    public Character getEtatDroits() {
        return etatDroits;
    }

    public void setEtatDroits(Character etatDroits) {
        this.etatDroits = etatDroits;
    }

    public BigDecimal getMontantsDroits() {
        return montantsDroits;
    }

    public void setMontantsDroits(BigDecimal montantsDroits) {
        this.montantsDroits = montantsDroits;
    }

    public Auteur getAuteur() {
        return auteur;
    }

    public void setAuteur(Auteur auteur) {
        this.auteur = auteur;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (droitsPK != null ? droitsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Droits)) {
            return false;
        }
        Droits other = (Droits) object;
        if ((this.droitsPK == null && other.droitsPK != null) || (this.droitsPK != null && !this.droitsPK.equals(other.droitsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dal.Droits[ droitsPK=" + droitsPK + " ]";
    }
    
}
