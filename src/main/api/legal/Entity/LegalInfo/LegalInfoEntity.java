package legal.Entity.LegalInfo;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Son on 5/12/2017.
 */
@Entity
@Table(name = "legalinfo")
public class LegalinfoEntity {

    private int id;
    private String number;
    private Date dateCreated;
    private String title;
    private Date dateExecute;
    private String standing;
    private String confirmation;
    private String institution;
    private String type;
    private String status;
    private String position;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "number", nullable = true, length = 100)
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Basic
    @Column(name = "dateCreated", nullable = true)
    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Basic
    @Column(name = "title", nullable = true, length = 2000)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "dateExecute", nullable = true)
    public Date getDateExecute() {
        return dateExecute;
    }

    public void setDateExecute(Date dateExecute) {
        this.dateExecute = dateExecute;
    }

    @Basic
    @Column(name = "standing", nullable = true, length = 1000)
    public String getStanding() {
        return standing;
    }

    public void setStanding(String standing) {
        this.standing = standing;
    }

    @Basic
    @Column(name = "confirmation", nullable = true, length = 1000)
    public String getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(String confirmation) {
        this.confirmation = confirmation;
    }

    @Basic
    @Column(name = "institution", nullable = true, length = 1000)
    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    @Basic
    @Column(name = "type", nullable = true, length = 1000)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "status", nullable = true, length = 1000)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "position", nullable = true, length = 1000)
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LegalinfoEntity that = (LegalinfoEntity) o;

        if (id != that.id) return false;
        if (number != null ? !number.equals(that.number) : that.number != null) return false;
        if (dateCreated != null ? !dateCreated.equals(that.dateCreated) : that.dateCreated != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (dateExecute != null ? !dateExecute.equals(that.dateExecute) : that.dateExecute != null) return false;
        if (standing != null ? !standing.equals(that.standing) : that.standing != null) return false;
        if (confirmation != null ? !confirmation.equals(that.confirmation) : that.confirmation != null) return false;
        if (institution != null ? !institution.equals(that.institution) : that.institution != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (position != null ? !position.equals(that.position) : that.position != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (dateCreated != null ? dateCreated.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (dateExecute != null ? dateExecute.hashCode() : 0);
        result = 31 * result + (standing != null ? standing.hashCode() : 0);
        result = 31 * result + (confirmation != null ? confirmation.hashCode() : 0);
        result = 31 * result + (institution != null ? institution.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        return result;
    }
}
