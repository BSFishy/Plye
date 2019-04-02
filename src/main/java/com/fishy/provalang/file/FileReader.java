package com.fishy.provalang.file;

import com.fishy.provalang.api.ProvalangApi;
import lombok.Data;

import java.io.*;

@Data
public class FileReader implements Closeable
{
    public final String          filename;
    private      FileInputStream stream;

    private boolean eof = false;

    @SuppressWarnings("FeatureEnvy")
    public FileReader(String filename)
    {
        this.filename = filename;

        try
        {
            File file = new File(filename);
            if (!file.exists())
            {
                ProvalangApi.error("File does not exist: %s", filename);
            }

            if (!(file.isFile() && file.canRead()))
            {
                ProvalangApi.error("Unable to read file: %s", filename);
            }

            stream = new FileInputStream(file);
        }
        catch (FileNotFoundException e)
        {
            ProvalangApi.error("Unable to read file (%s): %s", filename, e.getMessage());
        }
    }

    public boolean eof()
    {
        return eof;
    }

    public char read() throws IOException
    {
        char c = '\u0000';

        if (eof()) return c;
        if (stream.available() <= 0)
        {
            eof = true;
            return c;
        }
        else if (stream.available() == 1)
        {
            eof = true;
            return (char) stream.read();
        }

        try
        {
            c = (char) stream.read();
        }
        catch (EOFException ignored)
        {
            eof = true;
        }
        return c;
    }

    public char lookahead() throws IOException
    {
        stream.mark(2);
        char c = read();
        stream.reset();
        return c;
    }

    @Override
    public void close() throws IOException
    {
        stream.close();
    }
}
