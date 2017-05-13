package legal.Model;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

/**
 * Created by Son on 5/13/2017.
 */
public class FilterModel {
    public String sortType;
    public String sortRow;
    public Integer skip, take;

    public FilterModel(String sortType, String sortRow, Integer skip, Integer take) {
        this.sortType = sortType;
        this.sortRow = sortRow;
        this.skip = skip;
        this.take = take;
    }

    public FilterModel() {
    }

    public Criteria skipAndTake(Criteria criteria) {
        if (skip != null)
            criteria.setFirstResult(skip);
        if (take != null)
            criteria.setMaxResults(take);
        return criteria;
    }

    public Criteria sort(Criteria criteria) {
        if (sortRow == null) sortRow = "id";
        String row = sortRow.toLowerCase();
        if (sortType == null) sortType = "none";
        String type = sortType.toLowerCase();
        switch (type) {
            case "asc":
                criteria.addOrder(Order.asc(row));
                break;
            case "desc":
                criteria.addOrder(Order.desc(row));
                break;
            default:
                criteria.addOrder(Order.asc("id"));
        }
        return criteria;
    }
}
