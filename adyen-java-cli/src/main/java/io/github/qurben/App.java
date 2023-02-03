package io.github.qurben;

import java.io.IOException;

import com.adyen.Client;
import com.adyen.enums.Environment;
import com.adyen.model.management.ApiCredential;
import com.adyen.model.management.ListMerchantApiCredentialsResponse;
import com.adyen.service.exception.ApiException;
import com.adyen.service.management.ApiCredentialsMerchantLevel;

import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) throws ApiException, IOException {

        ArgumentParser parser = ArgumentParsers.newFor("Checksum").build()
                .defaultHelp(true)
                .description("Calculate checksum of given files.");
        parser.addArgument("-t", "--type")
                .choices("SHA-256", "SHA-512", "SHA1").setDefault("SHA-256")
                .help("Specify hash function to use");
        parser.addArgument("file").nargs("*")
                .help("File to calculate checksum");
        Namespace ns = null;
        try {
            ns = parser.parseArgs(args);
        } catch (ArgumentParserException e) {
            parser.handleError(e);
            System.exit(1);
        }
        
        System.out.println("Hello World!");

        Client client = new Client(System.getenv("ADYEN_MANAGEMENT_API_KEY"), Environment.TEST);

        ApiCredentialsMerchantLevel apiCredentialsMerchantLevel = new ApiCredentialsMerchantLevel(client);

        ListMerchantApiCredentialsResponse apiCredentials = apiCredentialsMerchantLevel.listApiCredentials("AdyenDevExECOM", null);

        for (ApiCredential apiCredential : apiCredentials.getData()) {
            System.out.println(apiCredential.getDescription());
        }
    }
}
