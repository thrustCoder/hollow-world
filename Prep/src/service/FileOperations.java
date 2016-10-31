package service;

import util.FileParser;

import java.io.IOException;

/**
 * Created by rpsin on 10/30/2016.
 */
public class FileOperations {
    public static final void parseFile()
    {
        // Q: https://www.careercup.com/question?id=5695752246394880
        try
        {
            FileParser parser = new FileParser();
            parser.parseFile("D:\\code\\github\\hollow-world\\Prep\\static\\FileParserFile");

            System.out.println("employee.name: " + parser.getElement("employee.name"));
            System.out.println("employee.address.houseno: " + parser.getElement("employee.address.houseno"));
            System.out.println("employee.address.location.state: " + parser.getElement("employee.address.location.state"));
            System.out.println("employee.manager: " + parser.getElement("employee.manager"));
        }
        catch (IOException e)
        {
            // do nothing here
        }
    }
}
