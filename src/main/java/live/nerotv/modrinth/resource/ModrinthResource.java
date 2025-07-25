package live.nerotv.modrinth.resource;

import com.alibaba.fastjson2.JSONObject;

import java.awt.*;
import java.net.URL;
import java.util.Date;
import java.util.List;

public interface ModrinthResource {

    // --- Core Identifiers ---
    /**
     * The unique ID of the resource.
     */
    String getId();

    /**
     * The URL-friendly slug of the resource.
     */
    default String getSlug() { return null; }

    /**
     * The ID of the team that owns the resource.
     */
    String getTeamId();

    /**
     * The ID of the organization that owns the resource.
     */
    default String getOrganizationId() { return null; }

    /**
     * The ID of the forum thread associated with the resource.
     */
    default String getThreadId() { return null; }


    // --- Basic Metadata ---
    /**
     * The title or name of the resource.
     */
    default String getTitle() { return null; }

    /**
     * A short description of the resource.
     */
    default String getDescription() { return null; }

    /**
     * The full description of the resource, often in Markdown.
     */
    default String getBody() { return null; }

    /**
     * The type of the resource (e.g., mod, plugin, resourcepack).
     */
    ModrinthResourceType getType();


    // --- Categorization & Compatibility ---
    /**
     * A list of categories the resource belongs to.
     */
    default List<String> getCategories() { return null; }

    /**
     * A list of additional categories the resource belongs to.
     */
    default List<String> getAdditionalCategories() { return null; }

    /**
     * A list of supported mod loaders (e.g., Fabric, Forge).
     */
    default List<String> getLoaders() { return null; }

    /**
     * A list of supported game versions (e.g., 1.19.2, 1.20.1).
     */
    default List<String> getGameVersions() { return null; }

    /**
     * A list of all version IDs associated with this resource.
     */
    default List<String> getVersionIds() { return null; }

    /**
     * The support status for the client side.
     */
    default ModrinthResourcePlatformState isClientSide() { return null; }

    /**
     * The support status for the server side.
     */
    default ModrinthResourcePlatformState isServerSide() { return null; }


    // --- Status & Lifecycle ---
    /**
     * The current status of the resource (e.g., approved, draft).
     */
    default ModrinthResourceStatus getStatus() { return null; }

    /**
     * The requested status for the resource, if any.
     */
    default ModrinthResourceStatus getRequestedStatus() { return null; }

    /**
     * A message from a moderator regarding the resource's status.
     */
    default String getModeratorMessage() { return null; }

    /**
     * The date the resource was published.
     */
    Date getDatePublished();

    /**
     * The date the resource was last updated.
     */
    Date getDateUpdated();

    /**
     * The date the resource was approved.
     */
    default Date getDateApproved() { return null; }

    /**
     * The date the resource was queued for approval.
     */
    default Date getDateQueued() { return null; }


    // --- Metrics ---
    /**
     * The total number of downloads.
     */
    default Integer getDownloads() { return 0; }

    /**
     * The total number of followers.
     */
    default Integer getFollowers() { return 0; }


    // --- Visuals & Media ---
    /**
     * The URL of the resource's icon.
     */
    default URL getIconUrl() { return null; }

    /**
     * A list of gallery images for the resource.
     */
    default List<ModrinthResourceGalleryImage> getGalleryImages() { return null; }

    /**
     * The accent color of the resource.
     */
    default Color getColor() { return null; }


    // --- External Links ---
    /**
     * The public-facing URL of the resource on the Modrinth website.
     */
    default URL getUrl() { return null; }

    /**
     * A URL to the full description body, if it's hosted externally.
     */
    @Deprecated
    default URL getBodyUrl() { return null; }

    /**
     * The URL to the issue tracker.
     */
    default URL getIssuesUrl() { return null; }

    /**
     * The URL to the source code repository.
     */
    default URL getSourceUrl() { return null; }

    /**
     * The URL to the wiki or documentation.
     */
    default URL getWikiUrl() { return null; }

    /**
     * The URL to the Discord server.
     */
    default URL getDiscordUrl() { return null; }

    /**
     * A list of donation URLs.
     */
    default List<ModrinthResourceDonationUrl> getDonationUrls() { return null; }


    // --- Legal ---
    /**
     * The license of the resource.
     */
    default ModrinthResourceLicense getLicense() { return null; }

    /**
     * The monetization status of the resource.
     */
    default ModrinthResourceMonetizationStatus isMonetized() { return null; }


    // --- API & Raw Data ---
    /**
     * The URL to the resource's endpoint in the Modrinth API.
     */
    URL getApiUrl();

    /**
     * The raw JSON object received from the API.
     */
    JSONObject getJson();
}