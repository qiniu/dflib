package com.nhl.dflib.csv;

import com.nhl.dflib.DataFrame;
import com.nhl.dflib.Index;
import com.nhl.dflib.row.RowProxy;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Path;

public class CsvSaver {

    private CSVFormat format;
    private boolean createMissingDirs;
    private boolean printHeader;

    public CsvSaver() {
        this.format = CSVFormat.DEFAULT;
        this.printHeader = true;
    }

    /**
     * Optionally sets the style or format of the imported CSV. CSVFormat comes from "commons-csv" library and
     * contains a number of predefined formats, such as CSVFormat.MYSQL, etc. It also allows to customize the format
     * further, by defining custom delimiters, line separators, etc.
     *
     * @param format a format object defined in commons-csv library
     * @return this saver instance
     */
    public CsvSaver format(CSVFormat format) {
        this.format = format;
        return this;
    }

    /**
     * Instructs the saver to create any missing directories in the file path.
     *
     * @return this saver instance
     * @since 0.6
     */
    public CsvSaver createMissingDirs() {
        this.createMissingDirs = true;
        return this;
    }   

    /**
     * Instructs the saver to omit saving the Index of a DataFrame. By default, the Index will be saved as a first row
     * in a file.
     *
     * @return this saver instance
     * @since 0.8
     */
    public CsvSaver noHeader() {
        this.printHeader = false;
        return this;
    }

    public void save(DataFrame df, File file) {

        if (createMissingDirs) {
            File dir = file.getParentFile();
            if (dir != null) {
                dir.mkdirs();
            }
        }

        try (FileWriter out = new FileWriter(file)) {
            save(df, out);
        } catch (IOException e) {
            throw new RuntimeException("Error writing CSV to " + file + ": " + e.getMessage(), e);
        }
    }

    /**
     * @since 0.11
     */
    public void save(DataFrame df, Path filePath) {
        save(df, filePath.toFile());
    }

    public void save(DataFrame df, String fileName) {
        save(df, new File(fileName));
    }

    public void save(DataFrame df, Appendable out) {

        try {
            CSVPrinter printer = new CSVPrinter(out, format);
            if (printHeader) {
                printHeader(printer, df.getColumnsIndex());
            }

            int len = df.width();
            for (RowProxy r : df) {
                printRow(printer, r, len);
            }

        } catch (IOException e) {
            throw new RuntimeException("Error writing CSV: " + e.getMessage(), e);
        }
    }

    public String saveToString(DataFrame df) {

        StringWriter out = new StringWriter();
        save(df, out);
        return out.toString();
    }

    private void printHeader(CSVPrinter printer, Index index) throws IOException {
        for (String label : index.getLabels()) {
            printer.print(label);
        }
        printer.println();
    }

    private void printRow(CSVPrinter printer, RowProxy row, int len) throws IOException {
        for (int i = 0; i < len; i++) {
            printer.print(row.get(i));
        }
        printer.println();
    }
}
