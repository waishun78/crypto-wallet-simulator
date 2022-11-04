package org.app.dao;

import org.app.domain.Account;
import org.app.domain.Portfolio;
import org.app.domain.Transaction;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

public interface PortfolioRepository extends CrudRepository<Portfolio>{
    // Find the portoflios that own a certain cruptocurrency ID
    Collection<Long> findPortfolioOwningID(Long currencyID);
}
