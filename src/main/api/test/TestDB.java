package test;

import legal.Model.LegalInfo.LegalInfoModel;
import legal.Model.LegalInfo.SearchLegalInfoModel;
import legal.Service.LegalInfo.LegalInfoService;
import manager.Entity.DatabaseEntity;
import manager.Model.DatabaseModel;
import manager.Service.DatabaseControllService;

/**
 * Created by Son on 4/10/2017.
 */
public class TestDB {
    public static void main(String[] args) {
        DatabaseModel databaseModel = new DatabaseModel(0, 0, "localhost", "test", "root", "root");
        DatabaseEntity.setFileDir("H://database.txt");
        DatabaseEntity.loadData();
//        DatabaseControllService databaseControllService = new DatabaseControllService(); // khởi tạo service làm việc
//        LegalInfoService.setFactory(databaseControllService.createConfiguration(databaseModel).buildSessionFactory());
        testGetData();
//        testGetId();
//        testCreateData();
//        testEditData();
//        testDeleteData();
    }

    static void testGetData() {
        SearchLegalInfoModel searchLegalInfoModel = new SearchLegalInfoModel();
        searchLegalInfoModel.number = "QĐ-TTg";
        LegalInfoService legalInfoService = new LegalInfoService();
        System.out.println(legalInfoService.get(searchLegalInfoModel).toString());
    }


    static void testGetId() {
        LegalInfoService legalInfoService = new LegalInfoService();
        System.out.println(legalInfoService.get(47585).toString());
    }

    static void testCreateData() {
        LegalInfoService legalInfoService = new LegalInfoService();
        LegalInfoModel legalInfoModel = legalInfoService.create("a", "a", "", "2017-03-03", "2017-02-02", "", "", "", "", "");
        System.out.println(legalInfoModel.toString());
    }

    static void testEditData() {
        LegalInfoService legalInfoService = new LegalInfoService();
        legalInfoService.update(47585, "a", "a", "", "2014-03-03", "2017-02-02", "", "", "", "", "");
//        System.out.println(legalInfoModel.toString());
        testGetId();
    }

    static void testDeleteData() {
        LegalInfoService legalInfoService = new LegalInfoService();
        legalInfoService.delete(47585);
    }
}
