package legal.Controller;

import legal.Interface.LegalRelationship.ILegalRelationshipService;
import legal.Model.LegalRelationship.SearchLegalRelationshipModel;
import legal.Service.LegalRelationship.LegalRelationshipService;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.simple.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.sql.Timestamp;

@Path("/legal/relationship")
public class RelationshipController {
    private ILegalRelationshipService legalRelationshipService;

    public RelationshipController() {
        legalRelationshipService = new LegalRelationshipService();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("")
    public String doSearch(@Context HttpHeaders headers, @QueryParam("fromlegal") int fromlegal,
                           @QueryParam("tolegal") int tolegal, @QueryParam("type") int type,
                           @QueryParam("date") String date) {
        SearchLegalRelationshipModel searchLegalRelationshipModel = new SearchLegalRelationshipModel();
        searchLegalRelationshipModel.tolegal = tolegal;
        searchLegalRelationshipModel.fromlegal = fromlegal;
        searchLegalRelationshipModel.type = type;
        if (date != null) searchLegalRelationshipModel.date = Timestamp.valueOf(date);
        return legalRelationshipService.get(searchLegalRelationshipModel).toString();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("")
    public String post(@Context HttpHeaders headers, @FormDataParam("fromlegal") int fromlegal, @FormDataParam("tolegal") int tolegal
            , @FormDataParam("type") int type, @FormDataParam("date") String dateS) {
        JSONObject x = new JSONObject();
        x.put("status", "201");
        Timestamp date = null;
        if (dateS != null) date = Timestamp.valueOf(dateS);
        x.put("data", legalRelationshipService.create(fromlegal, tolegal, date, type));
        return x.toString();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("")
    public String put(@Context HttpHeaders headers, @FormDataParam("id") int id, @FormDataParam("fromlegal") int fromlegal,
                      @FormDataParam("tolegal") int tolegal, @FormDataParam("type") int type,
                      @FormParam("date") String dateS) {
        JSONObject x = new JSONObject();
        x.put("status", "204");
        Timestamp date = null;
        if (dateS != null) date = Timestamp.valueOf(dateS);
        x.put("data", legalRelationshipService.update(id, fromlegal, tolegal, date, type));
        return x.toString();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public String delete(@Context HttpHeaders headers, @PathParam("id") int id) {
        JSONObject x = new JSONObject();
        if (legalRelationshipService.delete(id)) {
            x.put("status", "200");
            return x.toString();
        }
        x.put("status", "404");
        return x.toString();
    }
}