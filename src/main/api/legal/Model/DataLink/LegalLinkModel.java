package legal.Model.DataLink;

import legal.Entity.DataLink.LegallinkEntity;

/**
 * Created by Son on 4/12/2017.
 */
public class LegalLinkModel {
    public int id;
    public int idLink;
    public int idLegal;

    private LegalLinkModel() {
    }

    public LegalLinkModel(int id, int idLink, int idLegal) {
        this.id = id;
        this.idLink = idLink;
        this.idLegal = idLegal;
    }

    public LegallinkEntity toEntity() {
        LegallinkEntity legallinkEntity = new LegallinkEntity();
        legallinkEntity.setId(id);
        legallinkEntity.setIdLegal(idLegal);
        legallinkEntity.setIdLink(idLink);
        return legallinkEntity;
    }
}
