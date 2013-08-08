package com.sblm.modelMP;
// Generated 10-jul-2013 10:36:31 by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Empresa generated by hbm2java
 */
@Entity
@Table(name="EMPRESA"
    ,schema="dbo"
    ,catalog="DBINS"
)
public class Empresa  implements java.io.Serializable {


     private String idemp;
     private String desemp;
     private String sigemp;
     private String rucemp;
     private Boolean opcempext;
     private String diremp;
     private String refemp;
     private String webemp;
     private String conemp;
     private String caremp;
     private String telemp;
     private String anxemp;
     private String faxemp;
     private Boolean habemp;
     private Set<Negociacion> negociacions = new HashSet<Negociacion>(0);

    public Empresa() {
    }

	
    public Empresa(String idemp) {
        this.idemp = idemp;
    }
    public Empresa(String idemp, String desemp, String sigemp, String rucemp, Boolean opcempext, String diremp, String refemp, String webemp, String conemp, String caremp, String telemp, String anxemp, String faxemp, Boolean habemp, Set<Negociacion> negociacions) {
       this.idemp = idemp;
       this.desemp = desemp;
       this.sigemp = sigemp;
       this.rucemp = rucemp;
       this.opcempext = opcempext;
       this.diremp = diremp;
       this.refemp = refemp;
       this.webemp = webemp;
       this.conemp = conemp;
       this.caremp = caremp;
       this.telemp = telemp;
       this.anxemp = anxemp;
       this.faxemp = faxemp;
       this.habemp = habemp;
       this.negociacions = negociacions;
    }
   
     @Id 
    
    @Column(name="IDEMP", unique=true, nullable=false, length=8)
    public String getIdemp() {
        return this.idemp;
    }
    
    public void setIdemp(String idemp) {
        this.idemp = idemp;
    }
    
    @Column(name="DESEMP", length=80)
    public String getDesemp() {
        return this.desemp;
    }
    
    public void setDesemp(String desemp) {
        this.desemp = desemp;
    }
    
    @Column(name="SIGEMP", length=24)
    public String getSigemp() {
        return this.sigemp;
    }
    
    public void setSigemp(String sigemp) {
        this.sigemp = sigemp;
    }
    
    @Column(name="RUCEMP", length=12)
    public String getRucemp() {
        return this.rucemp;
    }
    
    public void setRucemp(String rucemp) {
        this.rucemp = rucemp;
    }
    
    @Column(name="OPCEMPEXT")
    public Boolean getOpcempext() {
        return this.opcempext;
    }
    
    public void setOpcempext(Boolean opcempext) {
        this.opcempext = opcempext;
    }
    
    @Column(name="DIREMP", length=100)
    public String getDiremp() {
        return this.diremp;
    }
    
    public void setDiremp(String diremp) {
        this.diremp = diremp;
    }
    
    @Column(name="REFEMP", length=100)
    public String getRefemp() {
        return this.refemp;
    }
    
    public void setRefemp(String refemp) {
        this.refemp = refemp;
    }
    
    @Column(name="WEBEMP", length=100)
    public String getWebemp() {
        return this.webemp;
    }
    
    public void setWebemp(String webemp) {
        this.webemp = webemp;
    }
    
    @Column(name="CONEMP", length=80)
    public String getConemp() {
        return this.conemp;
    }
    
    public void setConemp(String conemp) {
        this.conemp = conemp;
    }
    
    @Column(name="CAREMP", length=50)
    public String getCaremp() {
        return this.caremp;
    }
    
    public void setCaremp(String caremp) {
        this.caremp = caremp;
    }
    
    @Column(name="TELEMP", length=20)
    public String getTelemp() {
        return this.telemp;
    }
    
    public void setTelemp(String telemp) {
        this.telemp = telemp;
    }
    
    @Column(name="ANXEMP", length=10)
    public String getAnxemp() {
        return this.anxemp;
    }
    
    public void setAnxemp(String anxemp) {
        this.anxemp = anxemp;
    }
    
    @Column(name="FAXEMP", length=20)
    public String getFaxemp() {
        return this.faxemp;
    }
    
    public void setFaxemp(String faxemp) {
        this.faxemp = faxemp;
    }
    
    @Column(name="HABEMP")
    public Boolean getHabemp() {
        return this.habemp;
    }
    
    public void setHabemp(Boolean habemp) {
        this.habemp = habemp;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="empresa")
    public Set<Negociacion> getNegociacions() {
        return this.negociacions;
    }
    
    public void setNegociacions(Set<Negociacion> negociacions) {
        this.negociacions = negociacions;
    }




}


