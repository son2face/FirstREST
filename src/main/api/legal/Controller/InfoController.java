package legal.Controller;

import legal.Entity.DataLink.DataLinkEntity;
import legal.Entity.DataLink.DocumentLinkEntity;
import legal.Entity.DataLink.LegalLinkEntity;
import legal.Entity.LegalInfo.LegalInfoEntity;
import legal.Interface.DatabaseConnection.IDatabaseConnection;
import legal.Model.LegalInfo.LegalInfoModel;
import legal.Service.DatabaseConnection.DatabaseConnectionFactory;
import legal.Service.DatabaseConnection.MySQLConnection;
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

    static {
        int idConnection = DatabaseConnectionFactory.addDatabaseConnection(new MySQLConnection("localhost", "Test", "root", "root"));
        IDatabaseConnection connection = DatabaseConnectionFactory.getDatabaseConnection(idConnection);
        LegalInfoEntity.setDatabaseConnection(connection);
        DataLinkEntity.setDatabaseConnection(connection);
        LegalLinkEntity.setDatabaseConnection(connection);
        DocumentLinkEntity.setDatabaseConnection(connection);
    }

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
        Date date = null;
        JSONArray data = new JSONArray();
        if ("".equals(query)) statusCode = 400;
        else {
            try {
                date = Date.valueOf(dateS);
            } catch (Exception e) {
                System.out.println("loi dinh dang");
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