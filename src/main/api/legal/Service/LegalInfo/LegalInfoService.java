package legal.Service.LegalInfo;

import legal.Entity.LegalInfo.LegalinfoEntity;
import legal.Interface.LegalInfo.ILegalInfoService;
import legal.Model.LegalInfo.LegalInfoModel;
import legal.Model.LegalInfo.SearchLegalInfoModel;
import manager.Entity.DatabaseEntity;
import manager.Interface.IDatabaseControllService;
import manager.Interface.IDatabaseService;
import manager.Service.DatabaseControllService;
import manager.Service.DatabaseService;
import org.hibernate.*;
import org.hibernate.criterion.Expression;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Son on 5/5/2017.
 */
public class LegalInfoService implements ILegalInfoService {
    private static SessionFactory factory;
    private static int currentActive;

    public LegalInfoService(SessionFactory factory) {
        this.factory = factory;
    }

    public LegalInfoService() {
        if (factory == null || currentActive != DatabaseEntity.Active) {
            IDatabaseService databaseService = new DatabaseService();
            IDatabaseControllService databaseControllService = new DatabaseControllService();
            factory = databaseControllService.createConfiguration(databaseService.get(DatabaseEntity.Active)).buildSessionFactory();
            currentActive = DatabaseEntity.Active;
        }
    }

    public static void setFactory(SessionFactory factory) {
        LegalInfoService.factory = factory;
    }

    public LegalInfoModel create(String number, String type, String title, String dateCreated, String dateExecute, String standing, String status, String confirmation, String position, String institution) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            LegalInfoModel legalInfoModel = new LegalInfoModel(0, number, dateCreated, title, dateExecute, standing, confirmation, institution, type, status, position);
            int id = (int) session.save(legalInfoModel.toEntity());
            tx.commit();
            LegalInfoModel result = get(id);
            return result;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public LegalInfoModel get(int id) {
        Session session = factory.openSession();
        Criteria criteria = session.createCriteria(LegalinfoEntity.class, "legalinfo");
        criteria.add(Expression.ge("id", id));
        List<LegalinfoEntity> legalinfoEntities = criteria.list();
        if (!legalinfoEntities.isEmpty()) {
            return new LegalInfoModel(legalinfoEntities.get(0));
        }
        return null;
    }

    @Override
    public JSONObject get(SearchLegalInfoModel searchLegalInfoModel) {
        Session session = factory.openSession();
        Criteria criteria = session.createCriteria(LegalinfoEntity.class, "legalinfo");
        criteria = searchLegalInfoModel.apply(criteria);
        List<LegalinfoEntity> legalinfoEntities = criteria.list();
        List<LegalInfoModel> legalInfoModels = new ArrayList<>();
        legalinfoEntities.forEach(x -> {
            legalInfoModels.add(new LegalInfoModel(x));
        });
        JSONObject obj = new JSONObject();
        int statusCode = 200;
        JSONArray data = new JSONArray();
        for (LegalInfoModel x : legalInfoModels) {
            data.add(x.toJsonObject());
        }
        obj.put("status", statusCode);
        obj.put("data", data);
        return obj;
    }

    @Override
    public boolean delete(int id) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            LegalinfoEntity legalinfoEntity = new LegalinfoEntity();
            legalinfoEntity.setId(id);
            session.delete(legalinfoEntity);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public LegalInfoModel update(int id, String number, String type, String title, String dateCreated, String dateExecute, String standing, String status, String confirmation, String position, String institution) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            LegalInfoModel legalInfoModel = new LegalInfoModel(id, number, dateCreated, title, dateExecute, standing, confirmation, institution, type, status, position);
            session.update(legalInfoModel.toEntity());
            tx.commit();
            LegalInfoModel result = get(id);
            return result;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }
}
