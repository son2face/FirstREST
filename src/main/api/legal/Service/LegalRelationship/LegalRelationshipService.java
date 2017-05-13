package legal.Service.LegalRelationship;

import legal.Entity.LegalRelationship.LegalrelationshipEntity;
import legal.Interface.LegalRelationship.ILegalRelationshipService;
import legal.Model.LegalRelationship.LegalRelationshipModel;
import legal.Model.LegalRelationship.SearchLegalRelationshipModel;
import manager.Entity.DatabaseEntity;
import manager.Interface.IDatabaseControllService;
import manager.Interface.IDatabaseService;
import manager.Service.DatabaseControllService;
import manager.Service.DatabaseService;
import org.hibernate.*;
import org.hibernate.criterion.Expression;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Son on 5/13/2017.
 */
public class LegalRelationshipService implements ILegalRelationshipService {
    private static SessionFactory factory;
    private static int currentActive;

    public LegalRelationshipService(SessionFactory factory) {
        this.factory = factory;
    }

    public LegalRelationshipService() {
        if (factory == null || currentActive != DatabaseEntity.Active) {
            IDatabaseService databaseService = new DatabaseService();
            IDatabaseControllService databaseControllService = new DatabaseControllService();
            factory = databaseControllService.createConfiguration(databaseService.get(DatabaseEntity.Active)).buildSessionFactory();
            currentActive = DatabaseEntity.Active;
        }
    }

    public static void setFactory(SessionFactory factory) {
        LegalRelationshipService.factory = factory;
    }

    public LegalRelationshipModel create(Integer fromlegal, Integer tolegal, Timestamp date, Integer type) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            LegalRelationshipModel legalRelationshipModel = new LegalRelationshipModel(0, fromlegal, tolegal, date, type);
            int id = (int) session.save(legalRelationshipModel.toEntity());
            tx.commit();
            LegalRelationshipModel result = get(id);
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
    public LegalRelationshipModel get(int id) {
        Session session = factory.openSession();
        Criteria criteria = session.createCriteria(LegalRelationshipModel.class, "legalinfo");
        criteria.add(Expression.ge("id", id));
        List<LegalrelationshipEntity> legalrelationshipEntities = criteria.list();
        if (!legalrelationshipEntities.isEmpty()) {
            return new LegalRelationshipModel(legalrelationshipEntities.get(0));
        }
        return null;
    }

    @Override
    public JSONObject get(SearchLegalRelationshipModel searchLegalRelationshipModel) {
        Session session = factory.openSession();
        Criteria criteria = session.createCriteria(SearchLegalRelationshipModel.class, "legalrelationship");
        criteria = searchLegalRelationshipModel.apply(criteria);
        List<LegalrelationshipEntity> legalrelationshipEntities = criteria.list();
        List<LegalRelationshipModel> legalRelationshipModels = new ArrayList<>();
        legalrelationshipEntities.forEach(x -> {
            legalRelationshipModels.add(new LegalRelationshipModel(x));
        });
        JSONObject obj = new JSONObject();
        int statusCode = 200;
        JSONArray data = new JSONArray();
        for (LegalRelationshipModel x : legalRelationshipModels) {
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
            LegalrelationshipEntity legalrelationshipEntity = new LegalrelationshipEntity();
            legalrelationshipEntity.setId(id);
            session.delete(legalrelationshipEntity);
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
    public LegalRelationshipModel update(int id, Integer fromlegal, Integer tolegal, Timestamp date, Integer type) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            LegalRelationshipModel legalRelationshipModel = new LegalRelationshipModel(id, fromlegal, tolegal, date, type);
            session.update(legalRelationshipModel.toEntity());
            tx.commit();
            LegalRelationshipModel result = get(id);
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
