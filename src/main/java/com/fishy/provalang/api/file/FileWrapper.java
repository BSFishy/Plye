package com.fishy.provalang.api.file;

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
        if(index > this.index)
        {
            if(reader.eof())
                throw new EOFException("Reached end of file");

            StringBuilder sb = new StringBuilder(buffer);
            while (this.index < index)
            {
                char c = reader.read();

                sb.append(c);

                this.index++;
            }
            buffer = sb.toString();
        }

        return buffer.charAt(index);
    }

    public void clean(int length)
    {
        buffer = buffer.substring(length);
    }
}
