package Les7;

import java.io.FileWriter;
import java.io.IOException;

public class FileCurrencyProcessor implements CurrencyProcessor {
    private String filePath;

    public FileCurrencyProcessor(String filePath){
        this.filePath = filePath;

    }

    @Override
    public void saveCurrency(CurrencyInfo[] currenciesInfo, String date)  throws IOException  {
        for(CurrencyInfo currency :currenciesInfo) {
            try (FileWriter bob = new FileWriter(filePath, true)) {
                bob.write(currency.toString() + ", на " + date.replace("-", ".") + "\n");
            }
        }
    }

    @Override
    public boolean currency_exist(String date) {
        return false;
    }
}
