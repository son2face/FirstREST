package legal.Interface.LegalRelationship;

import legal.Model.LegalRelationship.LegalRelationshipModel;
import legal.Model.LegalRelationship.SearchLegalRelationshipModel;
import org.json.simple.JSONObject;

import java.sql.Timestamp;

/**
 * Created by Son on 5/13/2017.
 */
public interface ILegalRelationshipService {
    LegalRelationshipModel update(int id, Integer fromlegal, Integer tolegal, Timestamp date, Integer type);

    boolean delete(int id);

    LegalRelationshipModel get(int id);

    LegalRelationshipModel create(Integer fromlegal, Integer tolegal, Timestamp date, Integer type);

    JSONObject get(SearchLegalRelationshipModel searchLegalRelationshipModel);
}
