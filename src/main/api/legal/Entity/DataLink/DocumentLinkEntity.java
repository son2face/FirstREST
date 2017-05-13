package legal.Entity.DataLink;

import javax.persistence.*;

/**
 * Created by Son on 5/12/2017.
 */
@Entity
@Table(name = "documentlink", schema = "test", catalog = "")
public class DocumentlinkEntity {
    private int id;
    private Integer link;
    private String name;
    private String documentLink;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "link", nullable = true)
    public Integer getLink() {
        return link;
    }

    public void setLink(Integer link) {
        this.link = link;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 1000)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "documentLink", nullable = true, length = 1000)
    public String getDocumentLink() {
        return documentLink;
    }

    public void setDocumentLink(String documentLink) {
        this.documentLink = documentLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DocumentlinkEntity that = (DocumentlinkEntity) o;

        if (id != that.id) return false;
        if (link != null ? !link.equals(that.link) : that.link != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (documentLink != null ? !documentLink.equals(that.documentLink) : that.documentLink != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (link != null ? link.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (documentLink != null ? documentLink.hashCode() : 0);
        return result;
    }
}
