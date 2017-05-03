package legal.Controller;

import legal.Entity.LegalInfo.LegalInfoEntity;
import legal.Model.LegalInfo.LegalInfoModel;
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

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getMessage() {
        return "Tổ chức kiểm tra";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/search")
    public String doSearch(@Context HttpHeaders headers, @DefaultValue("") @QueryParam("q") String query, @DefaultValue("20") @QueryParam("limit") int limit, @QueryParam("date") String dateS) throws SQLException {
        JSONObject obj = new JSONObject();
        LegalInfoEntity legalInfoEntity = new LegalInfoEntity();
        int statusCode = 200;
        Date date;
        JSONArray data = new JSONArray();
        if ("".equals(query)) statusCode = 400;
        else {
            try {
                date = Date.valueOf(dateS);
            } catch (Exception e) {
                date = Date.valueOf(LocalDate.now());
            }
            List<LegalInfoModel> t = (List<LegalInfoModel>) (List<?>) legalInfoEntity.selectLimit(query, limit, date);
            for (LegalInfoModel x : t) {
                data.add(x.toJsonObject());
            }
        }
        obj.put("status", statusCode);
        obj.put("data", data);
        return obj.toString();
    }
}