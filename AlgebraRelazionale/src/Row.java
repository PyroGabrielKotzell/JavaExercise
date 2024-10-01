import java.util.ArrayList;
import java.util.List;

public class Row {
    private ArrayList<String> values;

    public Row(List<String> values) {
        this.values = new ArrayList<>();
        this.values.addAll(values);
    }

    public boolean sameValues(Row row) {
        return values.containsAll(row.getValues()) && values.size() == row.getValues().size();
    }

    public String getValue(int index) {
        return values.get(index);
    }

    public void setValue(int index, String value) {
        values.set(index, value);
    }

    public ArrayList<String> getValues() {
        return values;
    }

    public void setValues(ArrayList<String> values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return values.toString();
    }
}