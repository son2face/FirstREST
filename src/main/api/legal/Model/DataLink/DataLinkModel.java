package legal.Model.DataLink;

import legal.Entity.DataLink.DatalinkEntity;
import legal.Entity.LegalInfo.LegalinfoEntity;

/**
 * Created by Son on 4/11/2017.
 */
public class DataLinkModel {
    public int id;
    public String link;
    public String data;

    public DataLinkModel(int id, String link, String data) {
        this.id = id;
        this.link = link;
        this.data = data;
    }

    public DatalinkEntity toEntity() {
        DatalinkEntity datalinkEntity = new DatalinkEntity();
        datalinkEntity.setId(id);
        datalinkEntity.setData(data);
        datalinkEntity.setLink(link);
        return datalinkEntity;
    }
}
