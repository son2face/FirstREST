package legal.Entity.DataLink;

import javax.persistence.*;

/**
 * Created by Son on 5/12/2017.
 */
@Entity
@Table(name = "legallink", schema = "test", catalog = "")
public class LegallinkEntity {
    private int id;
    private Integer idLegal;
    private Integer idLink;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "idLegal", nullable = true)
    public Integer getIdLegal() {
        return idLegal;
    }

    public void setIdLegal(Integer idLegal) {
        this.idLegal = idLegal;
    }

    @Basic
    @Column(name = "idLink", nullable = true)
    public Integer getIdLink() {
        return idLink;
    }

    public void setIdLink(Integer idLink) {
        this.idLink = idLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LegallinkEntity that = (LegallinkEntity) o;

        if (id != that.id) return false;
        if (idLegal != null ? !idLegal.equals(that.idLegal) : that.idLegal != null) return false;
        if (idLink != null ? !idLink.equals(that.idLink) : that.idLink != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (idLegal != null ? idLegal.hashCode() : 0);
        result = 31 * result + (idLink != null ? idLink.hashCode() : 0);
        return result;
    }
}
