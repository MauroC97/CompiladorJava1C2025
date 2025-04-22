package lyc.compiler.symboltable;

//Symbol_lyc para diferenciarlo de java_cup.runtime.Symbol
public class Symbol_lyc {
    public String type;
    public String value;
    public int length;

    public Symbol_lyc(String value, String type) {
        this.type = type;
        this.value = value;
        this.length = value.length();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        String format = "%-10s│%-50s│%-10s";
        return String.format(format,this.type,this.value,this.length);
    }
}
