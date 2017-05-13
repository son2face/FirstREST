package legal.Controller;

import legal.Interface.LegalProcess.ILegalProcess;
import legal.Model.LegalInfo.LegalInfoModel;
import legal.Model.LegalInfo.SearchLegalInfoModel;
import legal.Service.LegalInfo.LegalInfoService;
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
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

@Path("/legal/detect")
public class LegalController {

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getMessage() {
        return "Tổ chức kiểm tra";
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/text")
    public String doText(@Context HttpHeaders headers, @FormDataParam("text") String text) {
        JSONObject obj = new JSONObject();
        ILegalProcess processLegal = new NativeTextProcess(text);
        LegalInfoModel res = processLegal.getInfo();
        LegalInfoService legalInfoService = new LegalInfoService();
        legalInfoService.create(res.number, res.type, res.title, res.dateCreated.toString(), res.dateExecute.toString(), res.standing, res.status, res.confirmation, res.position, res.institution);
        SearchLegalInfoModel searchLegalInfoModel = new SearchLegalInfoModel();
        searchLegalInfoModel.number = res.number;
        List<LegalInfoModel> t = (List<LegalInfoModel>) (List<?>) legalInfoService.get(searchLegalInfoModel);
        for (LegalInfoModel x : t) {
            obj.put("data", x.toJsonObject());
        }
        return obj.toString();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/link")
    public String doLink(@Context HttpHeaders headers, @FormDataParam("link") String link) {
        JSONObject obj = new JSONObject();
        ILegalProcess processLegal = new LinkProcess(link);
        LegalInfoModel res = processLegal.getInfo();
        LegalInfoService legalInfoService = new LegalInfoService();
        legalInfoService.create(res.number, res.type, res.title, res.dateCreated.toString(), res.dateExecute.toString(), res.standing, res.status, res.confirmation, res.position, res.institution);
        SearchLegalInfoModel searchLegalInfoModel = new SearchLegalInfoModel();
        searchLegalInfoModel.number = res.number;
        List<LegalInfoModel> t = (List<LegalInfoModel>) (List<?>) legalInfoService.get(searchLegalInfoModel);
        for (LegalInfoModel x : t) {
            obj.put("data", x.toJsonObject());
        }
        return obj.toString();
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Path("/file")
    public String uploadPdfFile(@FormDataParam("file") InputStream fileInputStream, @FormDataParam("file") FormDataContentDisposition fileMetaData) {
        JSONObject obj = new JSONObject();
        String[] temp = fileMetaData.getFileName().split("\\.");
        String fileExtension = temp[temp.length - 1].toLowerCase();
        ILegalProcess processLegal = new FileTextProcessService(fileInputStream, fileExtension);
        obj.put("data", processLegal.getInfo().toJsonObject());
        return obj.toString();
    }


}