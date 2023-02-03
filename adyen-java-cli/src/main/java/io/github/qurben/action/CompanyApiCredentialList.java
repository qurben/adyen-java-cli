package io.github.qurben.action;

import java.io.IOException;

import com.adyen.Client;
import com.adyen.model.management.ApiCredential;
import com.adyen.model.management.ListMerchantApiCredentialsResponse;
import com.adyen.service.exception.ApiException;
import com.adyen.service.management.ApiCredentialsMerchantLevel;
import com.google.auto.service.AutoService;

import net.sourceforge.argparse4j.inf.Argument;
import net.sourceforge.argparse4j.inf.ArgumentParser;

@AutoService(ActionHandler.class)
public class CompanyApiCredentialList implements ActionHandler {

    @Override
    public void configure(ArgumentParser argumentParser) {
        argumentParser.addArgument("company:apicredential:list").nargs("?");
    }

    @Override
    public String getName() {
        return "company:apicredential:list";
    }

    @Override
    public String getHelp() {
        return "List company api credentials";
    }

    @Override
    public String execute(Client client, String[] args) throws ApiException, IOException {
        ApiCredentialsCompanyLevel apiCredentialsMerchantLevel = new ApiCredentialsCompanyLevel(client);

        ListMerchantApiCredentialsResponse apiCredentials = apiCredentialsMerchantLevel
                .listApiCredentials(args[1], null);

        for (ApiCredential apiCredential : apiCredentials.getData()) {
            System.out.println(apiCredential.getDescription());
        }

        return "";
    }

}
