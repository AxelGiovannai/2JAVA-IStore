package whitelist;

import whitelist.dao.WhitelistDao;
import whitelist.entity.WhitelistedEmailEntity;

public class WhitelistService {
    private final WhitelistDao whitelistDao;

    public WhitelistService(WhitelistDao whitelistDao) {
        this.whitelistDao = whitelistDao;
    }

    public void addEmailToWhitelist(String email) {
        if (whitelistDao.findByEmail(email) == null) {
            WhitelistedEmailEntity emailEntity = new WhitelistedEmailEntity(email);
            whitelistDao.addEmail(emailEntity);
        } else {
            throw new IllegalArgumentException("Email already exists in the whitelist");
        }
    }

    public boolean isEmailWhitelisted(String email) {
        return whitelistDao.findByEmail(email) != null;
    }
}