package Les7;

import java.io.FileWriter;
import java.io.IOException;

public class FileCurrencyProcessor implements CurrencyProcessor {
    private String filePath;

    public FileCurrencyProcessor(String filePath){
        this.filePath = filePath;

    }

    @Override
    public void saveCurrency(CurrencyInfo currencyInfo, String date)  throws IOException  {
        try(FileWriter bob = new FileWriter(filePath, true)){
            bob.write(currencyInfo.toString()+ ", на " + date.replace("-", ".") + "\n");
        }
    }

    @Override
    public void saveCurrencies(CurrencyInfo[] currenciesInfo, String date) {
        for(CurrencyInfo currency:currenciesInfo){
            try {
                saveCurrency(currency, date);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean currency_exist(String date) {
        return false;
    }
}
