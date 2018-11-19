package actions;

import java.util.List;

import database.DB_Query;

public class AuthorAction {
    private DB_Query userDao = new DB_Query();

    public List<Object> Authorfindall() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        // TODO Auto-generated method stub
        return userDao.Authorfindall();
    }

}
