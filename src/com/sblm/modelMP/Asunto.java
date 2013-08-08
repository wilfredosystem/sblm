package com.sblm.modelMP;
// Generated 10-jul-2013 10:36:31 by Hibernate Tools 3.2.1.GA


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Asunto generated by hbm2java
 */
@Entity
@Table(name="ASUNTO"
    ,schema="dbo"
    ,catalog="DBINS"
)
public class Asunto  implements java.io.Serializable {


     private String idasn;
     private String desasn;

    public Asunto() {
    }

	
    public Asunto(String idasn) {
        this.idasn = idasn;
    }
    public Asunto(String idasn, String desasn) {
       this.idasn = idasn;
       this.desasn = desasn;
    }
   
     @Id 
    
    @Column(name="IDASN", unique=true, nullable=false, length=4)
    public String getIdasn() {
        return this.idasn;
    }
    
    public void setIdasn(String idasn) {
        this.idasn = idasn;
    }
    
    @Column(name="DESASN", length=100)
    public String getDesasn() {
        return this.desasn;
    }
    
    public void setDesasn(String desasn) {
        this.desasn = desasn;
    }




}

