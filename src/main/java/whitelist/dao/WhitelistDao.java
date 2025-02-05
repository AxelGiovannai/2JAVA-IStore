package whitelist.dao;

import whitelist.entity.WhitelistedEmailEntity;
import java.util.List;

/**
 * Interface for whitelist data access operations.
 */
public interface WhitelistDao {

    /**
     * Adds an email to the whitelist.
     *
     * @param emailEntity the email entity to add
     */
    void addEmail(WhitelistedEmailEntity emailEntity);

    /**
     * Finds a whitelisted email entity by email.
     *
     * @param email the email to find
     * @return the whitelisted email entity
     */
    WhitelistedEmailEntity findByEmail(String email);

    /**
     * Gets all whitelisted email entities.
     *
     * @return the list of whitelisted email entities
     */
    List<WhitelistedEmailEntity> getAllEmails();

    /**
     * Deletes a whitelisted email entity.
     *
     * @param emailEntity the email entity to delete
     */
    void deleteEmail(WhitelistedEmailEntity emailEntity);
}