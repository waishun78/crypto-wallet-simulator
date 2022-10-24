package org.app.dao;

import org.app.domain.Account;
import org.app.domain.Portfolio;
import org.app.domain.Transaction;

import java.util.Date;
import java.util.Map;

public interface PortfolioRepository extends CrudRepository<Portfolio>{
    Number findQuantityOwnedbyID(Long currencyID);
    Map<Date, Number> findPortfolioHistory(Date startDate, Date endDate);
}
