package legal.Model.LegalInfo;

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

    public LegalInfoModel() {
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
