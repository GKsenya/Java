package Les7;

import java.io.IOException;

public interface CurrencyProcessor {

    public void saveCurrency(CurrencyInfo currencyInfo, String date) throws IOException;
    public void saveCurrencies(CurrencyInfo[] currenciesInfo, String date);
    boolean currency_exist(String date);
}
