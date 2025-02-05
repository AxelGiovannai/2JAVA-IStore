package whitelist;

import whitelist.dao.WhitelistDao;
import whitelist.entity.WhitelistedEmailEntity;
import java.util.List;

/**
 * Service class for whitelist operations.
 */
public class WhitelistService {
    private final WhitelistDao whitelistDao;

    /**
     * Constructs a WhitelistService with the specified whitelist DAO.
     *
     * @param whitelistDao the whitelist DAO
     */
    public WhitelistService(WhitelistDao whitelistDao) {
        this.whitelistDao = whitelistDao;
    }

    /**
     * Adds an email to the whitelist.
     *
     * @param email the email to add
     * @throws IllegalArgumentException if the email already exists in the whitelist
     */
    public void addEmailToWhitelist(String email) {
        if (whitelistDao.findByEmail(email) == null) {
            WhitelistedEmailEntity emailEntity = new WhitelistedEmailEntity(email);
            whitelistDao.addEmail(emailEntity);
        } else {
            throw new IllegalArgumentException("Email already exists in the whitelist");
        }
    }

    /**
     * Checks if an email is whitelisted.
     *
     * @param email the email to check
     * @return true if the email is whitelisted, false otherwise
     */
    public boolean isEmailWhitelisted(String email) {
        return whitelistDao.findByEmail(email) != null;
    }

    /**
     * Gets all whitelisted emails.
     *
     * @return the list of whitelisted email entities
     */
    public List<WhitelistedEmailEntity> getAllEmails() {
        return whitelistDao.getAllEmails();
    }

    /**
     * Deletes an email from the whitelist.
     *
     * @param email the email to delete
     * @throws IllegalArgumentException if the email is not found in the whitelist
     */
    public void deleteEmailFromWhitelist(String email) {
        WhitelistedEmailEntity emailEntity = whitelistDao.findByEmail(email);
        if (emailEntity != null) {
            whitelistDao.deleteEmail(emailEntity);
        } else {
            throw new IllegalArgumentException("Email not found in the whitelist");
        }
    }
}