package legal.Model.LegalRelationship;

import legal.Model.FilterModel;
import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;

import java.sql.Timestamp;

/**
 * Created by Son on 5/13/2017.
 */
public class SearchLegalRelationshipModel extends FilterModel {
    public Integer fromlegal;
    public Integer tolegal;
    public Timestamp date;
    public Integer type;

    public Criteria filter(Criteria criteria) {
        if (fromlegal != null) {
            criteria.add(Expression.ge("fromlegal", fromlegal));
        }
        if (tolegal != null) {
            criteria.add(Expression.ge("tolegal", tolegal));
        }
        if (type != null) {
            criteria.add(Expression.ge("type", type));
        }
        if (date != null) {
            criteria.add(Restrictions.lt("date", date));
        }
        return criteria;
    }

    public Criteria apply(Criteria criteria) {
        return skipAndTake(sort(filter(criteria)));
    }
}
