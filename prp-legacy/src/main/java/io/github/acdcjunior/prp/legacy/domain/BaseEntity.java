package io.github.acdcjunior.prp.legacy.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

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