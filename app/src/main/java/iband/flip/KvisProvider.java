package iband.flip;

/**
 * Created by dell on 26-11-2017.
 */

public class KvisProvider {
    public KvisProvider(String name, String value) {
        this.name = name;
        this.value = value;
    }

    String name,value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
