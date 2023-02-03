package io.github.qurben;

import java.io.IOException;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

import com.adyen.Client;
import com.adyen.enums.Environment;
import com.adyen.service.exception.ApiException;
import io.github.qurben.action.ActionHandler;
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

        ArgumentParser parser = ArgumentParsers.newFor("adyen-cli").build()
                .defaultHelp(true)
                .description("Calculate checksum of given files.");
        parser.addArgument("action")
                .help("Action to execute");
        Namespace ns = null;
        try {
            ns = parser.parseArgs(args);
        } catch (ArgumentParserException e) {
            parser.handleError(e);
            System.exit(1);
        }
        
        System.exit(new App().run(ns));
    }

    public int run(Namespace ns) throws ApiException, IOException {
        String action = ns.getString("action");

        System.out.println("Hello World!" + action);

        Map<String, ActionHandler> handlerMap = getHandlers();

        if (!handlerMap.containsKey(action)) {
            System.out.println("Cannot find action: " + action);
            System.exit(1);
            return 1;
        }

        Client client = new Client(System.getenv("ADYEN_MANAGEMENT_API_KEY"), Environment.TEST);
        
        handlerMap.get(action).execute(client);

        return 0;
    }

    public Map<String, ActionHandler> getHandlers() {
        ServiceLoader<ActionHandler> handlers = ServiceLoader.load(ActionHandler.class);

        return handlers.stream().map(e -> e.get()).collect(Collectors.toMap(e -> e.getName(), e -> e));
    }
}
