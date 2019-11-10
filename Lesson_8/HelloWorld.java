package Les7;

import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.IOException;

public class HelloWorld {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, UserException {
        String date;
        String char_code;
        String file;
        if (args.length == 3) {
            date = UserWork.DateChanger(args[0]);
            char_code = args[1];
            file = args[2];
            EnteredData userData = new EnteredData(date, char_code, file);
//            EnteredData userData = UserWork.collectUserData();
            ResourceWorker resource = new DBResourceWorker();
            CurrencyInfo currencyInfo = resource.getCurrencyByDate(userData.getCharCode(), userData.getDate());
            if (currencyInfo == null) {
                resource = new CBResourceWorker();
                currencyInfo = resource.getCurrencyByDate(userData.getCharCode(), userData.getDate());

            }
            CurrencyProcessor fw = new FileCurrencyProcessor(userData.getOutputPath());
            fw.saveCurrency(currencyInfo, userData.getDate());
        }

        if (args.length == 2 && args[0].equalsIgnoreCase("updateDB")) {
            date = args[1];
            ResourceWorker resource = new CBResourceWorker();
            CurrencyInfo[] currenciesInfo = resource.getCurrenciesByDate(UserWork.DateChanger(date));
            CurrencyProcessor fw = new DataBaseCurrencyProcessor();
            for (CurrencyInfo currency : currenciesInfo) {
                fw.saveCurrency(currency, date);
            }
        }
    }
}
