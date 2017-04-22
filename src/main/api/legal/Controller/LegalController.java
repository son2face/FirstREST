package legal.Controller;

import legal.Entity.LegalInfo.LegalInfoEntity;
import legal.Interface.DatabaseConnection.IDatabaseConnection;
import legal.Interface.LegalProcess.ILegalProcess;
import legal.Model.LegalInfo.LegalInfoModel;
import legal.Service.DatabaseConnection.DatabaseConnectionFactory;
import legal.Service.DatabaseConnection.SQLSeverConnection;
import legal.Service.LegalProcess.FileTextProcessService;
import legal.Service.LegalProcess.LinkProcess;
import legal.Service.LegalProcess.NativeTextProcess;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.simple.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.io.*;
import java.sql.SQLException;
import java.util.List;

@Path("/legal/detect")
public class LegalController {
    static {
        int idConnection = DatabaseConnectionFactory.addDatabaseConnection(new SQLSeverConnection("42.112.212.163:1433","Test", "test","123456a@"));
        IDatabaseConnection connection = DatabaseConnectionFactory.getDatabaseConnection(idConnection);
        LegalInfoEntity.setDatabaseConnection(connection);
    }
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getMessage() {
        return "Tổ chức kiểm tra";
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/text")
    public String doText(@Context HttpHeaders headers, @FormParam("text") String text) throws SQLException {
        JSONObject obj = new JSONObject();
        ILegalProcess processLegal = new NativeTextProcess(text);
        LegalInfoModel res = processLegal.getInfo();
        LegalInfoEntity test = new LegalInfoEntity(res);
        test.insert();
        List<LegalInfoModel> t = (List<LegalInfoModel>)(List<?>)test.select(res.number);
        for(LegalInfoModel x : t){
            obj.put("data", x.toJsonObject());
        }
        return obj.toString();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/link")
    public String doLink(@Context HttpHeaders headers, @FormParam("link") String link) throws SQLException {
        JSONObject obj = new JSONObject();
        ILegalProcess processLegal = new LinkProcess(link);
        LegalInfoModel res = processLegal.getInfo();
        LegalInfoEntity test = new LegalInfoEntity(res);
        test.insert();
        List<LegalInfoModel> t = (List<LegalInfoModel>)(List<?>)test.select(res.number);
        for(LegalInfoModel x : t){
            obj.put("data", x.toJsonObject());
        }
        return obj.toString();
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Path("/file")
    public String uploadPdfFile(@FormDataParam("file") InputStream fileInputStream, @FormDataParam("file") FormDataContentDisposition fileMetaData) throws Exception {
        JSONObject obj = new JSONObject();
        String[] temp = fileMetaData.getFileName().split("\\.");
        String fileExtension = temp[temp.length-1].toLowerCase();
        ILegalProcess processLegal = new FileTextProcessService(fileInputStream,fileExtension);
        obj.put("data", processLegal.getInfo().toJsonObject());
        return obj.toString();
    }


}