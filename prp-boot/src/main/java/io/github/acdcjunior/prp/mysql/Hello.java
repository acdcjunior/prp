package io.github.acdcjunior.prp.mysql;

import javax.persistence.*;

@Entity
@Table(name = "hello")
public class Hello {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String col;

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getCol() { return col; }
    public void setCol(String col) { this.col = col; }

}