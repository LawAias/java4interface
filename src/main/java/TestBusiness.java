import org.omg.CORBA.PUBLIC_MEMBER;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestBusiness {


    public List<Test> getAll() {
        List<Test> list = new ArrayList<>();
        String sql = "select * from test";
        DBHelper dbHelper = new DBHelper(sql);
        ResultSet resultSet = null;
        try {
            resultSet = dbHelper.preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String avatar = resultSet.getString(3);
                Test test = new Test();
                test.setId(id);
                test.setName(name);
                test.setAvatar(avatar);
                list.add(test);
            }
            resultSet.close();
            dbHelper.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Test getTest(String qid) {
        String sql = "select * from test where id = " + qid;
        DBHelper dbHelper = new DBHelper(sql);
        Test test = null;
        try {
            ResultSet resultSet = dbHelper.preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(0);
                String name = resultSet.getString(1);
                String avatar = resultSet.getString(3);
                test = new Test();
                test.setId(id);
                test.setName(name);
                test.setAvatar(avatar);
            }
            resultSet.close();
            dbHelper.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return test;
    }

    public boolean insertTest(Test test) {
        String sql = "INSERT INTO test(name,avatar) VALUES(" +
                "'" + test.getName() + "'," +
                "'" + test.getAvatar() + "'" + ");";
        System.out.print("sql = " + sql);
        DBHelper dbHelper = new DBHelper(sql);
        return execute(dbHelper);
    }


    public boolean updateTest(String id, String name) {
        String sql = "UPDATE test SET name='" + name + "' WHERE id=" + id;
        System.out.println("sql=" + sql);
        DBHelper dbHelper = new DBHelper(sql);
        return execute(dbHelper);
    }

    public boolean deleteDeveloper(String id) {
        String sql = "DELETE FROM test WHERE id=" + id;
        System.out.println("sql=" + sql);
        DBHelper dbHelper = new DBHelper(sql);
        return execute(dbHelper);
    }


    private boolean execute(DBHelper dbHelper) {
        try {
            dbHelper.preparedStatement.execute();
            dbHelper.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
