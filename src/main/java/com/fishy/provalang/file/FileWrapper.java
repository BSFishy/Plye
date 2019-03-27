package com.fishy.provalang.file;

import lombok.Data;

import java.io.EOFException;
import java.io.IOException;

@Data
public class FileWrapper
{

    public final FileReader reader;

    private String buffer = "";
    private int index = 0;

    public char read(int index) throws IOException
    {
        if(index >= this.index)
        {
            if(reader.eof())
                throw new EOFException("Reached end of file");

            StringBuilder sb = new StringBuilder(buffer);
            while (this.index <= index)
            {
                char c = reader.read();

                sb.append(c);

                this.index++;
            }
            buffer = sb.toString();
        }

        return buffer.charAt(index);
    }

    public String readLength(int length) throws IOException
    {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < length; i++)
        {
            sb.append(read(i));
        }

        return sb.toString();
    }

    public void clean(int length)
    {
        buffer = buffer.substring(length);
        index -= length;
    }
}
