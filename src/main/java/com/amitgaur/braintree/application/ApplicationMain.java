
package com.amitgaur.braintree.application;

import com.amitgaur.braintree.model.CreditCard;
import com.amitgaur.braintree.services.*;
import com.amitgaur.braintree.util.LineSplitter;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ApplicationMain {

    private static final String usage = "-filepath [complete path to file path]";
    private static final String FILE_PATH_OPT = "filepath";
    private static final String HELP_OPT = "help";
    private static final Logger LOG = LoggerFactory.getLogger(ApplicationMain.class);

    public static void main(String... args) {
        try {
            processCmdlineArgs(args);
        } catch (ParseException e) {
            System.out.println("Error processing args : " + e.getStackTrace());
            System.exit(1);
        }
    }

    private static void processCmdlineArgs(String[] args) throws ParseException {
        Options commandLineOptions = new Options();
        commandLineOptions.addOption(HELP_OPT, false, "help with command invocation");
        commandLineOptions.addOption(FILE_PATH_OPT, true,
                "Complete file path of file to be parsed");


        GnuParser gnuParser = new GnuParser();
        org.apache.commons.cli.CommandLine cmLine = gnuParser.parse(commandLineOptions, args);
        if (cmLine.hasOption(HELP_OPT)) {
            printUsage(commandLineOptions);
            System.exit(0);
        }


        //Initialize card Manager : ideally use a dependency injection framework like Guice or Spring to initialize
        //these objects
        CreditCardManager cardManager = new CreditCardManagerImpl(new CreditCardFactory(new LuhnValidator()));
        LineSplitter lineSplitter = new LineSplitter(cardManager);

        if (cmLine.hasOption(FILE_PATH_OPT)) {

            String fileName = cmLine.getOptionValue(FILE_PATH_OPT);
            if (fileName == null || fileName.length() == 0) {
                System.out.println("**** Error : Invalid file name please run again**** ");
                return;
            }
            LOG.info("Processing file {}", fileName);
            processFile(lineSplitter, fileName);

        } else {
            try {
                LOG.info("Processing via command line args");
                processViaStdin(lineSplitter);
            } catch (IOException e) {
                LOG.error("Error processing input from stdin");
            }
        }

        System.out.println("*** Creating output *** \n");
        for (CreditCard card : cardManager.getCards()) {
            System.out.println(card.prettyPrint());
        }


    }


    private static void printUsage(Options options) {
        HelpFormatter helpFormatter = new HelpFormatter();
        helpFormatter.setWidth(80);
        helpFormatter.printHelp(usage, options);
    }


    protected static void processFile(LineSplitter lineSplitter, String fileName) {
        FileParser parser = new FileParser(fileName, lineSplitter);
        parser.process();
    }

    protected static void processViaStdin(LineSplitter lineSplitter) throws IOException {
        System.out.println(" *** Please start entering the data,  to Exit type in 'Done' or Enter  on a  new line ***");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        while ((line = reader.readLine()) != null && !line.equalsIgnoreCase("Done") && !line.trim().equals("")) {
            lineSplitter.action(line);
        }
        System.out.println("***  Done processing input *** ");

    }
}
