package Les7;

import java.io.IOException;

public interface CurrencyProcessor {

    public void saveCurrency(CurrencyInfo currencyInfo, String date) throws IOException;
}
