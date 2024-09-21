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

    Relation(String csvfile) {
        this();
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(csvfile)));
            if (br.ready())
                header.addAll(Arrays.asList(br.readLine().split(",")));
            while (br.ready()) {
                rows.add(new Row(Arrays.asList(br.readLine().split(","))));
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addRow(Row row) {
        rows.add(row);
    }

    public void addHeader(String key) {
        header.add(key);
    }

    public Row getRow(int row) {
        return rows.get(row);
    }

    public String getValue(int row, String key) {
        return rows.get(row).getValue(keyIndex(key));
    }

    public void setValue(int row, String key, String value) {
        rows.get(row).setValue(keyIndex(key), value);
    }

    public int keyIndex(String key) {
        return header.indexOf(key);
    }

    public int headers() {
        return header.size();
    }

    public int length() {
        return rows.size();
    }

    public ArrayList<String> getHeader() {
        return header;
    }

    public void setHeader(ArrayList<String> header) {
        this.header = header;
    }

    public ArrayList<Row> getRows() {
        return rows;
    }

    public void setRows(ArrayList<Row> rows) {
        this.rows = rows;
    }

    public Relation selection(Relation relation, String key, String value) {
        Relation r = new Relation();
        int i = keyIndex(key);

        if (i == -1)
            return r;
        r.setHeader(header);

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

        /*
        control equal values

         r.getRows().removeIf(e -> {
         return false;
         });
        */
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
