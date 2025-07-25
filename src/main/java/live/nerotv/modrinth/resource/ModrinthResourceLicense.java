package live.nerotv.modrinth.resource;

import com.alibaba.fastjson2.JSONObject;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public final class ModrinthResourceLicense {

    private final JSONObject json;
    private final String id;
    private final String name;
    private final URL url;

    public ModrinthResourceLicense(JSONObject licenseJson) {
        try {
            this.json = licenseJson;
            this.id = licenseJson.getString("id");
            this.name = licenseJson.getString("name");
            if(licenseJson.containsKey("url")&&licenseJson.getString("url")!=null) {
                this.url = URI.create(licenseJson.getString("url")).toURL();
            } else {
                this.url = null;
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public JSONObject getJson() {
        return json;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public URL getUrl() {
        return url;
    }
}