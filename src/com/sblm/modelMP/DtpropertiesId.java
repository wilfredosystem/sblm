package com.sblm.modelMP;
// Generated 10-jul-2013 10:36:31 by Hibernate Tools 3.2.1.GA


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * DtpropertiesId generated by hbm2java
 */
@Embeddable
public class DtpropertiesId  implements java.io.Serializable {


     private int id;
     private String property;

    public DtpropertiesId() {
    }

    public DtpropertiesId(int id, String property) {
       this.id = id;
       this.property = property;
    }
   

    @Column(name="id", nullable=false)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    @Column(name="property", nullable=false, length=64)
    public String getProperty() {
        return this.property;
    }
    
    public void setProperty(String property) {
        this.property = property;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof DtpropertiesId) ) return false;
		 DtpropertiesId castOther = ( DtpropertiesId ) other; 
         
		 return (this.getId()==castOther.getId())
 && ( (this.getProperty()==castOther.getProperty()) || ( this.getProperty()!=null && castOther.getProperty()!=null && this.getProperty().equals(castOther.getProperty()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getId();
         result = 37 * result + ( getProperty() == null ? 0 : this.getProperty().hashCode() );
         return result;
   }   


}


