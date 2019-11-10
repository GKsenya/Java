package Les7;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DataBaseCurrencyProcessor implements CurrencyProcessor {

    @Override
    public void saveCurrency(CurrencyInfo currencyInfo, String date) throws IOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://remotemysql.com:3306/uGTs1iK5gj";
            String username = "uGTs1iK5gj";
            String password = "x1pWjkeSTI";
            Connection con = DriverManager.getConnection(url, username, password);
            try {
                Statement stmt = con.createStatement();
                String request = "INSERT INTO currency_database(char_code, currency_nominal, currency_name, currency_value, currency_date) VALUES ('"
                        + currencyInfo.getCharCod()
                        +"'," + currencyInfo.getNominal()
                        +",'" + currencyInfo.getName()
                        +"'," + currencyInfo.getValue()
                        +",'" + date +"')";
                stmt.execute(request);
                stmt.close();
            } finally {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
