package Les7;

public class CurrencyInfo {
    private String CharCod;
    private int nominal;
    private String name;
    private float value;

    public CurrencyInfo(String charCod, int nominal, String name, float value) {
        CharCod = charCod;
        this.nominal = nominal;
        this.name = name;
        this.value = (float)((double)value);
    }

    public String getCharCod() {
        return CharCod;
    }

    public int getNominal() {
        return nominal;
    }

    public String getName() {
        return name;
    }

    public float getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "" + nominal +
                " " + name +
                " = " + value +
                " Российских рубля";
    }
}
