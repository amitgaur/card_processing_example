package com.amitgaur.braintree.services;

import com.amitgaur.braintree.util.LineSplitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: amitgaur
 * Date: 2/15/13
 * Time: 9:50 AM
 * To change this template use File | Settings | File Templates.
 */
public class FileParser {


    private String fileLocation;
    private LineSplitter lineSplitter;
    private CreditCardManager creditCardManager;
    private static final Logger LOG = LoggerFactory.getLogger(FileParser.class);

    public FileParser(String fileLocation, LineSplitter lineSplitter) {
        this.fileLocation = fileLocation;
        this.lineSplitter = lineSplitter;



    }

    public void process()                       {
        BufferedReader reader = null;
        try{
             reader = new BufferedReader(new FileReader(fileLocation));
             String line = null;
             while ((line = reader.readLine())!= null){
                 lineSplitter.action(line);
             }

        }
        catch (IOException e){
                      LOG.error("Error processing file {} with exception {}", fileLocation, e) ;
        }
        finally {
            if (reader != null) {
                try{
                    reader.close();
                }
                catch(IOException e){
                    LOG.error("Error closing file {} with exception {}", fileLocation, e)  ;
                }

            }
        }
    }



}
