package Les7;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBResourceWorker implements ResourceWorker {

    private CurrencyInfo currencyInfoResult = null;

    private void getCurrency(String code, String date) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://remotemysql.com:3306/uGTs1iK5gj";
            String username = "uGTs1iK5gj";
            String password = "x1pWjkeSTI";
            Connection con = DriverManager.getConnection(url, username, password);
            try {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM currency_database WHERE char_code = '" + code + "' AND currency_date = '" + date + "'");
                while (rs.next()) {
                    currencyInfoResult = new CurrencyInfo(rs.getString("char_code"), rs.getInt("currency_nominal"),
                            rs.getString("currency_name"), rs.getFloat("currency_value"));
                }
                rs.close();
                stmt.close();
            } finally {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public CurrencyInfo getCurrencyByDate(String code, String date) throws ParserConfigurationException, SAXException, UserException, IOException {
        getCurrency(code, date);
        return currencyInfoResult;
    }

    @Override
    public CurrencyInfo[] getCurrenciesByDate(String date) throws ParserConfigurationException, SAXException, UserException, IOException {
        return new CurrencyInfo[0];
    }
}
