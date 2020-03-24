import java.sql.*;

/** by Another_finch
 *
 * Простая авторизация через контроллер с использованием БД
 *
 */

public class simpleAuthorization {
    private static Connection connection;
    private static Statement statement;

    public static void authConnect(){
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc.sqlite:mydb.db"); // адрес БД на локальном РС
            statement = connection.createStatement();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String getNikcByLoginAndPass(String login, String pass){
        String sql = String.format("SELECT nickname FROM main WHERE login = '%s' AND password = '%s'", login, pass);
        try {
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()){
                rs.getString(1); // столбец в БД
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void disconnect(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
