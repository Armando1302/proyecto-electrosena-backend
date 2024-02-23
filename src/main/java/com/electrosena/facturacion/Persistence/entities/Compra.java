/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.electrosena.facturacion.Persistence.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import java.io.Serializable;

/**
 *
 * @author ARMAN
 */
@Entity
@Table(name = "compra")
@NamedQueries({
    @NamedQuery(name = "Compra.findAll", query = "SELECT c FROM Compra c"),
    @NamedQuery(name = "Compra.findByConsecutivoCompra", query = "SELECT c FROM Compra c WHERE c.consecutivoCompra = :consecutivoCompra")})
public class Compra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "consecutivo_compra")
    private Integer consecutivoCompra;
    @JoinColumn(name = "identificacion_usuario", referencedColumnName = "identificacion_usuario")
    @ManyToOne
    private Cliente identificacionUsuario;
    @JoinColumn(name = "sku", referencedColumnName = "sku")
    @ManyToOne
    private Producto sku;

    public Compra() {
    }

    public Compra(Integer consecutivoCompra) {
        this.consecutivoCompra = consecutivoCompra;
    }

    public Integer getConsecutivoCompra() {
        return consecutivoCompra;
    }

    public void setConsecutivoCompra(Integer consecutivoCompra) {
        this.consecutivoCompra = consecutivoCompra;
    }

    public Cliente getIdentificacionUsuario() {
        return identificacionUsuario;
    }

    public void setIdentificacionUsuario(Cliente identificacionUsuario) {
        this.identificacionUsuario = identificacionUsuario;
    }

    public Producto getSku() {
        return sku;
    }

    public void setSku(Producto sku) {
        this.sku = sku;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (consecutivoCompra != null ? consecutivoCompra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Compra)) {
            return false;
        }
        Compra other = (Compra) object;
        if ((this.consecutivoCompra == null && other.consecutivoCompra != null) || (this.consecutivoCompra != null && !this.consecutivoCompra.equals(other.consecutivoCompra))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.electrosena.facturacion.Persistence.entities.Compra[ consecutivoCompra=" + consecutivoCompra + " ]";
    }
    
}
