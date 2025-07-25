package live.nerotv.modrinth.resource;

import com.alibaba.fastjson2.JSONObject;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public final class ModrinthResourceDonationUrl  {

    private final JSONObject json;
    private final String id;
    private final String platform;
    private final URL url;

    public ModrinthResourceDonationUrl(JSONObject donationUrlJson) {
        try {
            this.json = donationUrlJson;
            this.id = donationUrlJson.getString("id");
            this.platform = donationUrlJson.getString("platform");
            this.url = URI.create(donationUrlJson.getString("url")).toURL();
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

    public String getPlatform() {
        return platform;
    }

    public URL getUrl() {
        return url;
    }
}