package live.nerotv.modrinth.resource;

import com.alibaba.fastjson2.JSONObject;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Date;

public final class ModrinthResourceGalleryImage {

    private final JSONObject json;
    private final URL url;
    private final boolean featured;
    private final String title;
    private final String description;
    private final Date dateCreated;
    private final int ordering;

    public ModrinthResourceGalleryImage(JSONObject galleryImageJson) {
        try {
            this.json = galleryImageJson;
            this.url = URI.create(galleryImageJson.getString("url")).toURL();
            this.featured = galleryImageJson.getBoolean("featured");
            this.title = galleryImageJson.getString("title");
            this.description = galleryImageJson.getString("description");
            this.dateCreated = galleryImageJson.getDate("date_created");
            this.ordering = galleryImageJson.getInteger("ordering");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public JSONObject getJson() {
        return json;
    }

    public URL getUrl() {
        return url;
    }

    public boolean isFeatured() {
        return featured;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public int getOrdering() {
        return ordering;
    }
}