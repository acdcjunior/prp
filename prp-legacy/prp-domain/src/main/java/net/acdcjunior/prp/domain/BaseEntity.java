package net.acdcjunior.prp.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.codehaus.jackson.annotate.JsonIgnore;

@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;


    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    @JsonIgnore
    public boolean isNew() {
        return (this.id == null);
    }
    
    @Override
    public String toString() {
    	return "[#"+getId()+"\t:"+getClass().getSimpleName()+"\t @"+hashCode()+"]";
    }

}