import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Row {
    private ArrayList<String> values;

    public Row() {
        this.values = new ArrayList<>();
    }

    public Row(List<String> values) {
        this.values = new ArrayList<>();
        this.values.addAll(values);
    }

    public boolean sameValues(Row row) {
        return values.containsAll(row.getValues()) && values.size() == row.getLen();
    }

    public String getValue(int index) {
        return values.get(index);
    }

    public void setValue(int index, String value) {
        values.set(index, value);
    }

    public void addValue(String value) {
        values.add(value);
    }

    public void addValues(Collection<? extends String> value) {
        values.addAll(value);
    }

    public void removeValue(int index) {
        values.remove(index);
    }

    public void removeValue(Object o) {
        values.remove(o);
    }

    public ArrayList<String> getValues() {
        return values;
    }

    public void setValues(ArrayList<String> values) {
        this.values = values;
    }

    public int getLen() {
        return values.size();
    }

    @Override
    public String toString() {
        return values.toString();
    }
}