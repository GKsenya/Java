package Les7;

public class EnteredData {
    private String charCode;
    private String date;
    private String outputPath;

    public EnteredData(String date, String charCode, String outputPath) {
        this.charCode = charCode;
        this.date = date;
        this.outputPath = outputPath;
    }

    public String getCharCode() {
        return charCode;
    }

    public String getDate() {
        return date;
    }

    public String getOutputPath() {
        return outputPath;
    }
}
