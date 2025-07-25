package live.nerotv.modrinth.project;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import live.nerotv.modrinth.resource.*;

import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ModrinthProject implements ModrinthResource {

    // Core Properties
    private final String id;
    private final String slug;
    private final String teamId;
    private final ModrinthResourceType type;
    private final String title;
    private final String description;
    private final String body;

    // Metadata & Categorization
    private final String organizationId;
    private final String threadId;
    private final List<String> categories;
    private final List<String> additionalCategories;
    private final List<String> loaders;
    private final List<String> gameVersions;
    private final List<String> versionIds;

    // Status
    private final ModrinthResourcePlatformState clientSide;
    private final ModrinthResourcePlatformState serverSide;
    private final ModrinthResourceStatus status;
    private final ModrinthResourceStatus requestedStatus;
    private final ModrinthResourceMonetizationStatus monetizationStatus;
    private final String moderatorMessage;

    // Timestamps
    private final Date datePublished;
    private final Date dateUpdated;
    private final Date dateApproved;
    private final Date dateQueued;

    // Metrics
    private final int downloads;
    private final int followers;

    // Visuals & URLs
    private final URL url;
    private final URL iconUrl;
    private final URL bodyUrl;
    private final URL discordUrl;
    private final URL issuesUrl;
    private final URL sourceUrl;
    private final URL wikiUrl;
    private final URL apiUrl;
    private final List<ModrinthResourceGalleryImage> galleryImages;
    private final List<ModrinthResourceDonationUrl> donationUrls;
    private final Color color;

    // License
    private final ModrinthResourceLicense license;

    // The original JSON object for direct access
    private final JSONObject json;

    public ModrinthProject(String slug_or_id) {
        Objects.requireNonNull(slug_or_id, "Project ID or slug cannot be null.");
        try {
            URL constructedApiUrl = URI.create("https://api.modrinth.com/v2/project/" + slug_or_id).toURL();
            this.apiUrl = constructedApiUrl;

            try (InputStream stream = constructedApiUrl.openStream()) {
                this.json = JSON.parseObject(stream);
            }

            // Required fields
            this.id = json.getString("id");
            this.teamId = json.getString("team");
            this.datePublished = json.getDate("published");
            this.dateUpdated = json.getDate("updated");
            this.downloads = json.getIntValue("downloads");
            this.followers = json.getIntValue("followers");

            // Optional fields
            this.slug = json.getString("slug");
            this.organizationId = json.getString("organization");
            this.threadId = json.getString("thread_id");
            this.title = json.getString("title");
            this.description = json.getString("description");
            this.body = json.getString("body");
            this.moderatorMessage = json.getString("moderator_message");
            this.dateApproved = json.getDate("approved");
            this.dateQueued = json.getDate("queued");

            // Enums
            this.type = parseEnum(json.getString("project_type"), ModrinthResourceType.class);
            this.clientSide = parseEnum(json.getString("client_side"), ModrinthResourcePlatformState.class);
            this.serverSide = parseEnum(json.getString("server_side"), ModrinthResourcePlatformState.class);
            this.status = parseEnum(json.getString("status"), ModrinthResourceStatus.class);
            this.requestedStatus = parseEnum(json.getString("requested_status"), ModrinthResourceStatus.class);
            this.monetizationStatus = parseEnum(json.getString("monetization_status"), ModrinthResourceMonetizationStatus.class);

            // URLs
            this.url = parseUrl("https://modrinth.com/project/" + this.slug);
            this.iconUrl = parseUrl(json.getString("icon_url"));
            this.bodyUrl = parseUrl(json.getString("body_url"));
            this.discordUrl = parseUrl(json.getString("discord_url"));
            this.issuesUrl = parseUrl(json.getString("issues_url"));
            this.sourceUrl = parseUrl(json.getString("source_url"));
            this.wikiUrl = parseUrl(json.getString("wiki_url"));

            // Lists
            this.categories = json.getList("categories", String.class);
            this.additionalCategories = json.getList("additional_categories", String.class);
            this.loaders = json.getList("loaders", String.class);
            this.gameVersions = json.getList("game_versions", String.class);
            this.versionIds = json.getList("versions", String.class);

            JSONArray galleryArray = json.getJSONArray("gallery");
            this.galleryImages = (galleryArray == null) ? Collections.emptyList() :
                    galleryArray.stream()
                            .map(item -> new ModrinthResourceGalleryImage((JSONObject) item))
                            .collect(Collectors.toList());

            JSONArray donationUrlsArray = json.getJSONArray("donation_urls");
            this.donationUrls = (donationUrlsArray == null) ? Collections.emptyList() :
                    donationUrlsArray.stream()
                            .map(item -> new ModrinthResourceDonationUrl((JSONObject) item))
                            .collect(Collectors.toList());

            // Complex Objects
            JSONObject licenseObject = json.getJSONObject("license");
            this.license = (licenseObject == null) ? null : new ModrinthResourceLicense(licenseObject);

            String colorString = json.getString("color");
            this.color = (colorString == null) ? null : Color.decode(colorString);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private URL parseUrl(String urlString) {
        if (urlString == null || urlString.isEmpty()) {
            return null;
        }
        try {
            return URI.create(urlString).toURL();
        } catch (MalformedURLException | IllegalArgumentException e) {
            return null;
        }
    }

    private <T extends Enum<T>> T parseEnum(String value, Class<T> enumClass) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        try {
            return Enum.valueOf(enumClass, value.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getSlug() {
        return slug;
    }

    @Override
    public String getTeamId() {
        return teamId;
    }

    @Override
    public ModrinthResourceType getType() {
        return type;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getBody() {
        return body;
    }

    @Override
    public String getOrganizationId() {
        return organizationId;
    }

    @Override
    public String getThreadId() {
        return threadId;
    }

    @Override
    public List<String> getCategories() {
        return categories;
    }

    @Override
    public List<String> getAdditionalCategories() {
        return additionalCategories;
    }

    @Override
    public List<String> getLoaders() {
        return loaders;
    }

    @Override
    public List<String> getGameVersions() {
        return gameVersions;
    }

    @Override
    public List<String> getVersionIds() {
        return versionIds;
    }

    @Override
    public ModrinthResourcePlatformState isClientSide() {
        return clientSide;
    }

    @Override
    public ModrinthResourcePlatformState isServerSide() {
        return serverSide;
    }

    @Override
    public ModrinthResourceStatus getStatus() {
        return status;
    }

    @Override
    public ModrinthResourceStatus getRequestedStatus() {
        return requestedStatus;
    }

    @Override
    public ModrinthResourceMonetizationStatus isMonetized() {
        return monetizationStatus;
    }

    @Override
    public String getModeratorMessage() {
        return moderatorMessage;
    }

    @Override
    public Date getDatePublished() {
        return datePublished;
    }

    @Override
    public Date getDateUpdated() {
        return dateUpdated;
    }

    @Override
    public Date getDateApproved() {
        return dateApproved;
    }

    @Override
    public Date getDateQueued() {
        return dateQueued;
    }

    @Override
    public Integer getDownloads() {
        return downloads;
    }

    @Override
    public Integer getFollowers() {
        return followers;
    }

    @Override
    public URL getUrl() {
        return url;
    }

    @Override
    public URL getIconUrl() {
        return iconUrl;
    }

    @Override @Deprecated
    public URL getBodyUrl() {
        return bodyUrl;
    }

    @Override
    public URL getDiscordUrl() {
        return discordUrl;
    }

    @Override
    public URL getIssuesUrl() {
        return issuesUrl;
    }

    @Override
    public URL getSourceUrl() {
        return sourceUrl;
    }

    @Override
    public URL getWikiUrl() {
        return wikiUrl;
    }

    @Override
    public URL getApiUrl() {
        return apiUrl;
    }

    @Override
    public List<ModrinthResourceGalleryImage> getGalleryImages() {
        return galleryImages;
    }

    @Override
    public List<ModrinthResourceDonationUrl> getDonationUrls() {
        return donationUrls;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public ModrinthResourceLicense getLicense() {
        return license;
    }

    @Override
    public JSONObject getJson() {
        return json;
    }
}