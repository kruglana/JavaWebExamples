package accounts;

import dbService.DBException;
import dbService.DBService;
import dbService.dataSets.UsersDataSet;

/**
 * Created by kruglana on 10.02.2017.
 */
public class AccountService {
    private DBService dbService;

    public AccountService() {
    }

    public void addNewUser(String login, String password) throws DBException {
        try {
            //create connection to DB
            DBService dbService = new DBService();
            long userId = dbService.addUser(login, password);
            System.out.println("Added user id: " + userId);

         /*   UsersDataSet dataSet = dbService.getUserByLogin(login);
            System.out.println("User data set: " + dataSet);*/

        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    public UsersDataSet getUserByLogin(String login) {
        UsersDataSet dataSet = null;
        //create connection to DB
        DBService dbService = new DBService();
        try{
            dataSet = dbService.getUserByLogin(login);
            System.out.println("User data set: " + dataSet);
            dbService.printConnectInfo();

        } catch (DBException e) {
            e.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return dataSet;
    }
}
