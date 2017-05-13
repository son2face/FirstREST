package legal.Model.LegalInfo;

import legal.Entity.LegalInfo.LegalinfoEntity;
import org.json.simple.JSONObject;

import java.sql.Date;

public class LegalInfoModel {
    public int id;
    public String number = "";
    public Date dateCreated = Date.valueOf("2100-01-01");
    public String title = "";
    public Date dateExecute = Date.valueOf("2100-01-01");
    public String standing = "";
    public String confirmation = "";
    public String institution = "";
    public String type = "";
    public String status = "";
    public String position = "";

    public LegalInfoModel(int id, String number, Date dateCreated, String title, Date dateExecute, String standing, String confirmation, String institution, String type, String status, String position) {
        this.id = id;
        this.number = number;
        this.dateCreated = dateCreated;
        this.title = title;
        this.dateExecute = dateExecute;
        this.standing = standing;
        this.confirmation = confirmation;
        this.institution = institution;
        this.type = type;
        this.status = status;
        this.position = position;
    }

    public LegalInfoModel(int id, String number, String dateCreated, String title, String dateExecute, String standing, String confirmation, String institution, String type, String status, String position) {
        this.id = id;
        this.number = number;
        this.dateCreated = Date.valueOf(dateCreated);
        this.title = title;
        this.dateExecute = Date.valueOf(dateExecute);
        this.standing = standing;
        this.confirmation = confirmation;
        this.institution = institution;
        this.type = type;
        this.status = status;
        this.position = position;
    }

    public LegalInfoModel(LegalinfoEntity legalinfoEntity) {
        this.id = legalinfoEntity.getId();
        this.number = legalinfoEntity.getNumber();
        this.dateCreated = legalinfoEntity.getDateCreated();
        this.title = legalinfoEntity.getTitle();
        this.dateExecute = legalinfoEntity.getDateExecute();
        this.standing = legalinfoEntity.getStanding();
        this.confirmation = legalinfoEntity.getConfirmation();
        this.institution = legalinfoEntity.getInstitution();
        this.type = legalinfoEntity.getType();
        this.status = legalinfoEntity.getStatus();
        this.position = legalinfoEntity.getPosition();
    }

    public LegalInfoModel() {
    }

    public LegalinfoEntity toEntity() {
        LegalinfoEntity legalinfoEntity = new LegalinfoEntity();
        legalinfoEntity.setId(id);
        legalinfoEntity.setConfirmation(confirmation);
        legalinfoEntity.setDateCreated(dateCreated);
        legalinfoEntity.setDateExecute(dateExecute);
        legalinfoEntity.setInstitution(institution);
        legalinfoEntity.setNumber(number);
        legalinfoEntity.setType(type);
        legalinfoEntity.setTitle(title);
        legalinfoEntity.setStatus(status);
        legalinfoEntity.setStanding(standing);
        legalinfoEntity.setPosition(position);
        return legalinfoEntity;
    }

    public String toJSon() {
        return toJsonObject().toString();
    }

    public JSONObject toJsonObject() {
        JSONObject obj = new JSONObject();
        obj.put("id", id);
        obj.put("number", number);
        obj.put("dateCreated", dateCreated.getTime());
        obj.put("title", title);
        obj.put("dateExecute", dateExecute.getTime());
        obj.put("standing", standing);
        obj.put("confirmation", confirmation);
        obj.put("institution", institution);
        obj.put("type", type);
        obj.put("status", status);
        obj.put("position", position);
        return obj;
    }

    public String toString() {
        return "id: " + id + "\n" +
                "number: " + number + "\n" +
                "dateCreated: " + dateCreated.toString() + "\n" +
                "title: " + title + "\n" +
                "dateExecute: " + dateExecute.toString() + "\n" +
                "standing: " + standing + "\n" +
                "confirmation: " + confirmation + "\n" +
                "status: " + status + "\n" +
                "institution: " + institution + "\n" +
                "position: " + position + "\n" +
                "type: " + type + "\n";
    }
}
