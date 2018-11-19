package actions;

import java.util.List;

import database.DB_Query;

public class ReviewAction {
    private DB_Query userDao = new DB_Query();

    public List<Object> Reviewfindall() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        // TODO Auto-generated method stub
        return userDao.Reviewfindall();
    }
}
