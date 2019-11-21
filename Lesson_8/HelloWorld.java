package Les7;

import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import java.io.IOException;

public class HelloWorld {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, UserException {

        String date;
        String char_code;
        String file;

        if (!args[0].equalsIgnoreCase("updateDB")) {

            date = UserWork.DateChanger(args[0]);
            char_code = args[1];
            file = args[2];

            EnteredData userData = new EnteredData(date, char_code, file);
            ResourceWorker resource = new DBResourceWorker();
            CurrencyInfo[] currencyInfo = resource.getCurrenciesByDate(userData.getCharCode(), userData.getDate());

            if (currencyInfo[0] == null) {
                resource = new CBResourceWorker();
                currencyInfo = resource.getCurrenciesByDate(userData.getCharCode(), userData.getDate());
            }
            CurrencyProcessor fw = new FileCurrencyProcessor(userData.getOutputPath());
            fw.saveCurrency(currencyInfo, userData.getDate());
            System.out.println("Запрашиваемая котировка успешно записана в файл " + userData.getOutputPath());
        }

//----------------------------------------DataBaseUpdating-------------------------------------------//

        if (args[0].equalsIgnoreCase("updateDB")) {
            date = args[1];
            CurrencyProcessor dbcp = new DataBaseCurrencyProcessor();
            if(!dbcp.currency_exist(date)) {
                ResourceWorker resource = new CBResourceWorker();
                CurrencyInfo[] currenciesInfo = resource.getCurrenciesByDate("all", UserWork.DateChanger(date));

                dbcp.saveCurrency(currenciesInfo, date);
                if (args.length == 3) {
                    file = args[2];
                    CurrencyProcessor fcp = new FileCurrencyProcessor(file);
                    fcp.saveCurrency(currenciesInfo, date);
                    System.out.println("Котировки успешно записаны в файл " + file);
                }
                System.out.println("В базу данных успешно добавлены котировки на " + date);
            }else{
                System.out.println("База данных уже содержит котировки на указанную дату");
            }
        }
    }
}
