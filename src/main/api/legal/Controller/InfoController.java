package legal.Controller;

import legal.Entity.LegalInfo.LegalInfoEntity;
import legal.Interface.LegalInfo.ILegalInfoService;
import legal.Model.LegalInfo.LegalInfoModel;
import legal.Service.LegalInfo.LegalInfoService;
import manager.Interface.IDatabaseService;
import manager.Service.DatabaseService;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Path("/legal/info")
public class InfoController {
    private ILegalInfoService legalInfoService;

    public InfoController() {
        legalInfoService = new LegalInfoService();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("")
    public String doSearch(@Context HttpHeaders headers, @DefaultValue("") @QueryParam("q") String query, @DefaultValue("20") @QueryParam("limit") int limit, @QueryParam("date") String dateS) throws SQLException {
        return legalInfoService.getLimit(query,limit,dateS).toString();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("")
    public String post(@Context HttpHeaders headers, @FormParam("type") String type, @FormParam("title") String title
            , @FormParam("dateCreated") String dateCreated, @FormParam("dateExecute") String dateExecute
            , @FormParam("standing") String standing, @FormParam("status") String status,@FormParam("number") String number
            , @FormParam("confirmation") String confirmation, @FormParam("position") String position
            , @FormParam("institution") String institution) {
        JSONObject x = new JSONObject();
        if (legalInfoService.create(type,number,title,dateCreated,dateExecute,standing,status,confirmation,position,institution)) {
            x.put("status", "201");
            return x.toString();
        }
        x.put("status", "404");
        return x.toString();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("")
    public String put(@Context HttpHeaders headers, @FormDataParam("id") int id, @FormParam("type") String type, @FormParam("title") String title
            , @FormParam("dateCreated") String dateCreated, @FormParam("dateExecute") String dateExecute
            , @FormParam("standing") String standing, @FormParam("status") String status,@FormParam("number") String number
            , @FormParam("confirmation") String confirmation, @FormParam("position") String position
            , @FormParam("institution") String institution ) {
        JSONObject x = new JSONObject();
        if (legalInfoService.update(id,number,type,title,dateCreated,dateExecute,standing,status,confirmation,position,institution)) {
            x.put("status", "204");
            return x.toString();
        }
        x.put("status", "404");
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