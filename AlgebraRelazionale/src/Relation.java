import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Relation {
    private ArrayList<String> header;
    private ArrayList<Row> rows;

    Relation() {
        header = new ArrayList<>();
        rows = new ArrayList<>();
    }

    /**
     * Makes a Relation using the given CSV file, cuts all values outside of the headers length
     * @param csvfile
     */
    Relation(String csvfile) {
        this();
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(csvfile)));
            if (br.ready())
                header.addAll(Arrays.asList(br.readLine().split(",")));
            while (br.ready()) {
                rows.add(new Row(Arrays.asList(br.readLine().split(",")).subList(0, headersLen())));
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a row and removes duplicates if any
     * @param row
     */
    public void addRow(Row row) {
        rows.add(row);
        removeDuplicates();
    }

    /**
     * Unused
     * @param key
     */
    public void addHeader(String key) {
        header.add(key);
    }

    /**
     * Unused
     * @param key
     */
    public void removeHeader(int key) {
        header.remove(key);
    }

    /**
     * Unused
     * @param key
     */
    public void removeHeader(String key) {
        header.remove(key);
    }

    public Row getRow(int row) {
        return rows.get(row);
    }

    public void removeRow(int row) {
        rows.remove(row);
    }

    public void removeRow(Row row) {
        rows.remove(row);
    }

    public String getValue(int row, String key) {
        return rows.get(row).getValue(keyIndex(key));
    }

    public void setValue(int row, String key, String value) {
        getRow(row).setValue(keyIndex(key), value);
    }

    public int keyIndex(String key) {
        return header.indexOf(key);
    }

    public int headersLen() {
        return header.size();
    }

    public int rowsLen() {
        return rows.size();
    }

    public ArrayList<String> getHeaders() {
        return header;
    }

    public void setHeaders(ArrayList<String> header) {
        this.header = header;
    }

    public ArrayList<Row> getRows() {
        return rows;
    }

    /**
     * Unused
     * @param rows
     */
    public void setRows(ArrayList<Row> rows) {
        this.rows = rows;
    }

    public void removeDuplicates() {
        ArrayList<Row> rows = new ArrayList<>();
        for (Row row : getRows()) {
            boolean tmp = false;
            for (Row row2 : rows) {
                if (row.sameValues(row2)) {
                    tmp = true;
                    break;
                }
            }
            if (!tmp)
                rows.add(row);
        }
        setRows(rows);
    }

    public Relation selection(Relation relation, String key, String value) {
        Relation r = new Relation();
        int i = keyIndex(key);

        if (i == -1)
            return r;
        r.setHeaders(header);

        for (Row row : relation.getRows()) {
            if (row.getValue(i).equals(value))
                r.addRow(row);
        }

        return r;
    }

    public Relation projection(Relation relation, ArrayList<String> keys) {
        Relation r = new Relation();
        ArrayList<Integer> indexes = new ArrayList<>();

        for (String key : keys) {
            if (header.contains(key)) {
                r.addHeader(key);
                indexes.add(keyIndex(key));
            }
        }

        for (Row row : relation.getRows()) {
            ArrayList<String> values = new ArrayList<>();
            for (int i : indexes) {
                values.add(row.getValue(i));
            }
            r.addRow(new Row(values));
        }

        r.removeDuplicates();

        return r;
    }

    public Relation union(Relation relation1, Relation relation2) {
        Relation r = new Relation();
        if (relation1.headersLen() != relation2.headersLen())
            return r;
        if (!relation1.getHeaders().containsAll(relation2.getHeaders()))
            return r;

        r.setHeaders(relation1.getHeaders());

        for (Row row : relation1.getRows()) {
            r.addRow(row);
        }
        for (Row row : relation2.getRows()) {
            r.addRow(row);
        }

        r.removeDuplicates();

        return r;
    }

    public Relation difference(Relation relation1, Relation relation2) {
        Relation r = new Relation();
        if (relation1.headersLen() != relation2.headersLen())
            return r;
        if (!relation1.getHeaders().containsAll(relation2.getHeaders()))
            return r;

        r.setHeaders(relation1.getHeaders());

        for (Row row1 : relation1.getRows()) {
            for (Row row2 : relation2.getRows()) {
                if (row1.sameValues(row2)) break;
                r.addRow(row1);
            }
        }

        return r;
    }

    @Override
    public String toString() {
        String s = "";
        s += header.toString() + "\n";
        for (Row row : rows) {
            s += row.toString() + "\n";
        }
        s = s.substring(0, s.length() - 1);
        return s;
    }
}
