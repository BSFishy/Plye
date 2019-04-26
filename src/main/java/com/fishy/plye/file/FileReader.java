package com.fishy.plye.file;

import com.fishy.plye.api.PlyeApi;
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
                PlyeApi.error("File does not exist: %s", filename);
            }

            if (!(file.isFile() && file.canRead()))
            {
                PlyeApi.error("Unable to read file: %s", filename);
            }

            stream = new FileInputStream(file);
        }
        catch (FileNotFoundException e)
        {
            PlyeApi.error("Unable to read file (%s): %s", filename, e.getMessage());
        }
    }

    public char lookahead() throws IOException
    {
        stream.mark(2);
        char c = read();
        stream.reset();
        return c;
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

    public boolean eof()
    {
        return eof;
    }

    @Override
    public void close() throws IOException
    {
        stream.close();
    }
}
