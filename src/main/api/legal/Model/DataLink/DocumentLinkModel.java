package legal.Model.DataLink;

import legal.Entity.DataLink.DocumentlinkEntity;

/**
 * Created by Son on 4/12/2017.
 */
public class DocumentLinkModel {
    public int id;
    public int linkId;
    public String name;
    public String documentLink;

    public DocumentLinkModel(int id, int linkId, String name, String documentLink) {
        this.id = id;
        this.linkId = linkId;
        this.name = name;
        this.documentLink = documentLink;
    }

    public DocumentlinkEntity toEntity() {
        DocumentlinkEntity documentlinkEntity = new DocumentlinkEntity();
        documentlinkEntity.setDocumentLink(documentLink);
        documentlinkEntity.setId(id);
        documentlinkEntity.setLink(linkId);
        documentlinkEntity.setName(name);
        return documentlinkEntity;
    }
}
