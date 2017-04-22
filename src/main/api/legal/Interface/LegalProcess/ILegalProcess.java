package legal.Interface.LegalProcess;

import legal.Model.LegalInfo.LegalInfoModel;

import java.util.List;

/**
 * Created by Son on 4/8/2017.
 */
public interface ILegalProcess {
    boolean isValid();
    LegalInfoModel getInfo();
    List<String> getLines();
    String getData();
}
