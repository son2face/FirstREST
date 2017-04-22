package legal.Model.DataLink;

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
}
