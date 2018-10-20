package client.loader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by: Tommi
 * Date: 27.10.2017
 * Time: 13.42
 */

public class ConfigReader {
    String url;
    public ConfigReader(String url) {
        this.url = url;
    }

    private String[] readConfig() {
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        List<String> lines = new ArrayList<>();
        try {
            URL configUrl = new URL(url);
            inputStream = configUrl.openStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) inputStream.close();
                if (bufferedReader != null) bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return lines.toArray(new String[lines.size()]);
    }

    public Map<String, String> read() {
        // Read the config file
        String[] page = readConfig();
        // Create the parameter map we want to return
        HashMap<String, String> map = new HashMap<>();
        for (String parameter : page) {
            // Cleanse the string as we don't need "param=" or "msg="
            parameter = parameter.replace("param=", "").replace("msg=", "");
            // Split the string on the "=" sign and limit the split to 2 in case some of the parameters use the "=" sign
            String[] splitParameter = parameter.split("=", 2);
            // Check if the value is empty and add an empty parameter with the name
            if (splitParameter.length == 1)
                map.put(splitParameter[0], "");
            // Check there is a value and add the parameter with the value
            if (splitParameter.length == 2)
                map.put(splitParameter[0], splitParameter[1]);
        }
        // return our parameters
        return map;
    }
}
