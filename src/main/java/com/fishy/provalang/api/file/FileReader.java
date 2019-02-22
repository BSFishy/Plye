package com.fishy.provalang.api.file;

import com.fishy.provalang.api.ProvalangApi;
import lombok.Data;

import java.io.*;

@Data
public class FileReader
{
    public final String          filename;
    private      DataInputStream stream;

    private boolean eof = false;

    public FileReader(String filename)
    {
        this.filename = filename;

        try
        {
            stream = new DataInputStream(new FileInputStream(filename));
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

        try
        {
            c = stream.readChar();
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
}
