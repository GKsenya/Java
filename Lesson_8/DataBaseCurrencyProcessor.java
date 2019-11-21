package Les7;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataBaseCurrencyProcessor implements CurrencyProcessor {

    private String url = "jdbc:mysql://remotemysql.com:3306/uGTs1iK5gj";
    private String username = "uGTs1iK5gj";
    private String password = "x1pWjkeSTI";

    @Override
    public void saveCurrency(CurrencyInfo[] currencyInfo, String date) throws IOException {
        for(CurrencyInfo currency : currencyInfo) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(url, username, password);
                try {
                    Statement stmt = con.createStatement();
                    String request = "INSERT INTO currency_database(char_code, currency_nominal, currency_name, currency_value, currency_date) VALUES ('"
                            + currency.getCharCod()
                            + "'," + currency.getNominal()
                            + ",'" + currency.getName()
                            + "'," + currency.getValue()
                            + ",'" + date + "')";
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

    public boolean currency_exist(String date) {
        boolean result = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, username, password);
            try {
                Statement stmt = con.createStatement();
                ResultSet request = stmt.executeQuery("SELECT * FROM currency_database WHERE currency_date = '" + date + "'");;
                result = request.first();
                stmt.close();
            } finally {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
