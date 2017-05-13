package legal.Entity.LegalRelationship;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Son on 5/13/2017.
 */
@Entity
@Table(name = "legalrelationship")
public class LegalrelationshipEntity {
    private int id;
    private Integer fromlegal;
    private Integer tolegal;
    private Timestamp date;
    private Integer type;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "fromlegal", nullable = true)
    public Integer getFromlegal() {
        return fromlegal;
    }

    public void setFromlegal(Integer fromlegal) {
        this.fromlegal = fromlegal;
    }

    @Basic
    @Column(name = "tolegal", nullable = true)
    public Integer getTolegal() {
        return tolegal;
    }

    public void setTolegal(Integer tolegal) {
        this.tolegal = tolegal;
    }

    @Basic
    @Column(name = "date", nullable = true)
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Basic
    @Column(name = "type", nullable = true)
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LegalrelationshipEntity that = (LegalrelationshipEntity) o;

        if (id != that.id) return false;
        if (fromlegal != null ? !fromlegal.equals(that.fromlegal) : that.fromlegal != null) return false;
        if (tolegal != null ? !tolegal.equals(that.tolegal) : that.tolegal != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (fromlegal != null ? fromlegal.hashCode() : 0);
        result = 31 * result + (tolegal != null ? tolegal.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
