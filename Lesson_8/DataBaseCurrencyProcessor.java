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
    public void saveCurrency(CurrencyInfo currencyInfo, String date) throws IOException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, username, password);
            try {
                Statement stmt = con.createStatement();
                String request = "INSERT INTO currency_database(char_code, currency_nominal, currency_name, currency_value, currency_date) VALUES ('"
                        + currencyInfo.getCharCod()
                        + "'," + currencyInfo.getNominal()
                        + ",'" + currencyInfo.getName()
                        + "'," + currencyInfo.getValue()
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

    public void saveCurrencies(CurrencyInfo[] currencyInfo, String date) {
        for (CurrencyInfo currency : currencyInfo) {
            try {
                saveCurrency(currency, date);
            } catch (IOException e) {
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
