package util;

import java.io.*;
import java.util.HashMap;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.Map;

/**
 * Created by rpsin on 7/3/2016.
 */
public class FileParser
{
    public FileParser()
    {
        mParseMap = new HashMap<>();
    }

    public String getElement(final String input)
    {
        if (input == null || input.equals(""))
        {
            throw new IllegalArgumentException("input should not be null or empty string");
        }

        if (mParseMap.containsKey(input))
        {
            return mParseMap.get(input);
        }

        return "NOT_FOUND";
    }

    public void parseFile(final String filePath) throws FileNotFoundException, IOException
    {
        if (filePath == null || filePath.equals(""))
        {
            throw new IllegalArgumentException("filePath should not be null or empty string");
        }

        if (mParseMap.size() > 0)
        {
            // reset parse map
            mParseMap = new HashMap<>();
        }

        File file = new File(filePath);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = br.readLine();

        StringBuilder keyPrefix = new StringBuilder("");

        while (line != null && !line.equals(""))
        {
            if (line.contains("[/"))
            {
                // end of tag
                int startIndexForRemoval = (keyPrefix.lastIndexOf(".") > 0) ? keyPrefix.lastIndexOf(".") : 0;
                keyPrefix.replace(startIndexForRemoval, (keyPrefix.length() - 1), "");
            }
            else if (line.contains("["))
            {
                // start of tag
                if (keyPrefix.length() > 0)
                {
                    keyPrefix.append(".");
                }
                keyPrefix.append(line.substring(1, line.length() -1));
            }
            else
            {
                // key value pair
                final String[] keyVal = line.split("=");
                if ((keyVal.length < 2) || keyVal[0].equals(""))
                {
                    throw new IllegalArgumentException("Key value pair not formatted correctly");
                }

                final String key = keyPrefix + "." + keyVal[0];
                mParseMap.put(key, keyVal[1]);
            }

            line = br.readLine();
        }

        br.close();
    }

    private Map<String, String> mParseMap;
}
