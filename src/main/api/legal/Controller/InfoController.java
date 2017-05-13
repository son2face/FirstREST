package legal.Controller;

import legal.Interface.LegalInfo.ILegalInfoService;
import legal.Model.LegalInfo.SearchLegalInfoModel;
import legal.Service.LegalInfo.LegalInfoService;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.simple.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.sql.Date;

@Path("/legal/info")
public class InfoController {
    private ILegalInfoService legalInfoService;

    public InfoController() {
        legalInfoService = new LegalInfoService();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("")
    public String doSearch(@Context HttpHeaders headers, @DefaultValue("") @QueryParam("number") String number, @DefaultValue("20") @QueryParam("limit") int limit, @QueryParam("date") String dateS) {
        SearchLegalInfoModel searchLegalInfoModel = new SearchLegalInfoModel();
        searchLegalInfoModel.number = number;
        searchLegalInfoModel.take = limit;
        if (dateS == null || "".equals(dateS.trim())) {
//            searchLegalInfoModel.dateCreated
        } else searchLegalInfoModel.dateCreated = Date.valueOf(dateS);
        return legalInfoService.get(searchLegalInfoModel).toString();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("")
    public String post(@Context HttpHeaders headers, @FormDataParam("type") String type, @FormDataParam("title") String title
            , @FormDataParam("dateCreated") String dateCreated, @FormDataParam("dateExecute") String dateExecute
            , @FormDataParam("standing") String standing, @FormDataParam("status") String status, @FormDataParam("number") String number
            , @FormDataParam("confirmation") String confirmation, @FormDataParam("position") String position
            , @FormDataParam("institution") String institution) {
        JSONObject x = new JSONObject();
        x.put("status", "201");
        x.put("data", legalInfoService.create(type, number, title, dateCreated, dateExecute, standing, status, confirmation, position, institution));
        return x.toString();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("")
    public String put(@Context HttpHeaders headers, @FormDataParam("id") int id, @FormDataParam("type") String type, @FormDataParam("title") String title
            , @FormDataParam("dateCreated") String dateCreated, @FormDataParam("dateExecute") String dateExecute
            , @FormDataParam("standing") String standing, @FormDataParam("status") String status, @FormDataParam("number") String number
            , @FormDataParam("confirmation") String confirmation, @FormDataParam("position") String position
            , @FormDataParam("institution") String institution) {
        JSONObject x = new JSONObject();
        x.put("status", "204");
        x.put("data", legalInfoService.update(id, number, type, title, dateCreated, dateExecute, standing, status, confirmation, position, institution));
        return x.toString();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public String delete(@Context HttpHeaders headers, @PathParam("id") int id) {
        JSONObject x = new JSONObject();
        if (legalInfoService.delete(id)) {
            x.put("status", "200");
            return x.toString();
        }
        x.put("status", "404");
        return x.toString();
    }
}