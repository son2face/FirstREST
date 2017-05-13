package legal.Model.LegalInfo;

import legal.Model.FilterModel;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import java.sql.Date;

/**
 * Created by Son on 5/13/2017.
 */
public class SearchLegalInfoModel extends FilterModel {
    public String number;
    public Date dateCreated;
    public String title;
    public Date dateExecute;
    public String standing;
    public String confirmation;
    public String institution;
    public String type;
    public String status;
    public String position;

    public Criteria filter(Criteria criteria) {
        if (number != null && !"".equals(number)) {
            criteria.add(Restrictions.like("number", number, MatchMode.ANYWHERE));
        }
        if (dateCreated != null) {
            criteria.add(Restrictions.lt("dateCreated", dateCreated));
        }
        if (dateExecute != null) {
            criteria.add(Restrictions.lt("dateExecute", dateExecute));
        }
        if (title != null && !"".equals(title)) {
            criteria.add(Restrictions.like("title", title, MatchMode.ANYWHERE));
        }
        if (standing != null && !"".equals(standing)) {
            criteria.add(Restrictions.like("standing", standing, MatchMode.ANYWHERE));
        }
        if (confirmation != null && !"".equals(confirmation)) {
            criteria.add(Restrictions.like("confirmation", confirmation, MatchMode.ANYWHERE));
        }
        if (institution != null && !"".equals(institution)) {
            criteria.add(Restrictions.like("institution", institution, MatchMode.ANYWHERE));
        }
        if (type != null && !"".equals(type)) {
            criteria.add(Restrictions.like("type", type, MatchMode.ANYWHERE));
        }
        if (status != null && !"".equals(status)) {
            criteria.add(Restrictions.like("status", status, MatchMode.ANYWHERE));
        }
        if (position != null && !"".equals(position)) {
            criteria.add(Restrictions.like("position", position, MatchMode.ANYWHERE));
        }
        return criteria;
    }

    public Criteria apply(Criteria criteria) {
        return skipAndTake(sort(filter(criteria)));
    }
}
