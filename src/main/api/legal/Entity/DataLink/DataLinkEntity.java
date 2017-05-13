package legal.Entity.DataLink;

import javax.persistence.*;

/**
 * Created by Son on 5/12/2017.
 */
@Entity
@Table(name = "datalink", schema = "test", catalog = "")
public class DatalinkEntity {
    private int id;
    private String link;
    private String data;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "link", nullable = true, length = 1000)
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Basic
    @Column(name = "data", nullable = true, length = -1)
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DatalinkEntity that = (DatalinkEntity) o;

        if (id != that.id) return false;
        if (link != null ? !link.equals(that.link) : that.link != null) return false;
        if (data != null ? !data.equals(that.data) : that.data != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (link != null ? link.hashCode() : 0);
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }
}
