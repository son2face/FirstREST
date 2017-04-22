package test;

import legal.Model.LegalInfo.LegalInfoModel;
import legal.Service.LegalProcess.LinkProcess;

/**
 * Created by Son on 4/12/2017.
 */
public class TestLink {
    public static void main(String[] args) {
        LinkProcess test = new LinkProcess("http://vbpl.vn/botuphap/Pages/vbpq-toanvan.aspx?ItemID=118077&dvid=41");
        LegalInfoModel re = test.getInfo();
        System.out.println(re.toString());
    }
}
