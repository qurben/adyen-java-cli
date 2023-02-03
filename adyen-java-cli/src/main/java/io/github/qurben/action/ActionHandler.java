package io.github.qurben.action;

import java.io.IOException;

import com.adyen.Client;
import com.adyen.service.exception.ApiException;

import net.sourceforge.argparse4j.inf.Argument;
import net.sourceforge.argparse4j.inf.ArgumentParser;

public interface ActionHandler {
    default void configure(ArgumentParser argumentParser) {
        argumentParser.addArgument(getName()).help(getHelp());
    }

    String getName();
    String getHelp();

    String execute(Client client, String... args) throws ApiException, IOException;
}
