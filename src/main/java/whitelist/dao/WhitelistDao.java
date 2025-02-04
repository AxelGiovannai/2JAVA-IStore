package whitelist.dao;

import whitelist.entity.WhitelistedEmailEntity;
import java.util.List;

public interface WhitelistDao {
    void addEmail(WhitelistedEmailEntity emailEntity);
    WhitelistedEmailEntity findByEmail(String email);
    List<WhitelistedEmailEntity> getAllEmails();
    void deleteEmail(WhitelistedEmailEntity emailEntity);
}