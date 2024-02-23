/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.electrosena.facturacion.Persistence.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author ARMAN
 */
@Entity
@Table(name = "ciudad")
public class Ciudad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codigo_dane")
    private String codigoDane;
    @Column(name = "nombre_ciudad")
    private String nombreCiudad;
    @OneToMany(mappedBy = "codigoDane")
    private List<Cliente> clienteList;

    public Ciudad() {
    }

    public Ciudad(String codigoDane) {
        this.codigoDane = codigoDane;
    }

    public String getCodigoDane() {
        return codigoDane;
    }

    public void setCodigoDane(String codigoDane) {
        this.codigoDane = codigoDane;
    }

    public String getNombreCiudad() {
        return nombreCiudad;
    }

    public void setNombreCiudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }

    public List<Cliente> getClienteList() {
        return clienteList;
    }

    public void setClienteList(List<Cliente> clienteList) {
        this.clienteList = clienteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoDane != null ? codigoDane.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ciudad)) {
            return false;
        }
        Ciudad other = (Ciudad) object;
        if ((this.codigoDane == null && other.codigoDane != null) || (this.codigoDane != null && !this.codigoDane.equals(other.codigoDane))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.electrosena.facturacion.Persistence.entities.Ciudad[ codigoDane=" + codigoDane + " ]";
    }
    
}
