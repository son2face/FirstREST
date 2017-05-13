package legal.Model.LegalRelationship;

import legal.Entity.LegalRelationship.LegalrelationshipEntity;
import org.json.simple.JSONObject;

import java.sql.Timestamp;

public class LegalRelationshipModel {
    public int id;
    public Integer fromlegal;
    public Integer tolegal;
    public Timestamp date;
    public Integer type;

    public LegalRelationshipModel(int id, Integer fromlegal, Integer tolegal, Timestamp date, Integer type) {
        this.id = id;
        this.fromlegal = fromlegal;
        this.tolegal = tolegal;
        this.date = date;
        this.type = type;
    }

    public LegalRelationshipModel() {
    }

    public LegalRelationshipModel(LegalrelationshipEntity legalrelationshipEntity) {
        this.id = legalrelationshipEntity.getId();
        this.fromlegal = legalrelationshipEntity.getFromlegal();
        this.tolegal = legalrelationshipEntity.getTolegal();
        this.date = legalrelationshipEntity.getDate();
        this.type = legalrelationshipEntity.getType();
    }

    public LegalrelationshipEntity toEntity() {
        LegalrelationshipEntity legalrelationshipEntity = new LegalrelationshipEntity();
        legalrelationshipEntity.setId(id);
        legalrelationshipEntity.setDate(date);
        legalrelationshipEntity.setFromlegal(fromlegal);
        legalrelationshipEntity.setTolegal(tolegal);
        return legalrelationshipEntity;
    }

    public String toJSon() {
        return toJsonObject().toString();
    }

    public JSONObject toJsonObject() {
        JSONObject obj = new JSONObject();
        obj.put("id", id);
        obj.put("fromlegal", fromlegal);
        obj.put("tolegal", tolegal);
        obj.put("date", date.getTime());
        obj.put("type", type);
        return obj;
    }

    public String toString() {
        return "id: " + id + "\n" +
                "fromlegal: " + fromlegal + "\n" +
                "tolegal: " + tolegal + "\n" +
                "date: " + date.toString() + "\n" +
                "type: " + type + "\n";
    }
}
