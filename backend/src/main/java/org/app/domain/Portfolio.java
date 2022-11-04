package org.app.domain;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Portfolio extends DomainEntity{

    private final Account account;

    public Portfolio(Map<Long, Long> owned, Map<Date, Long> portfolioValueHistory, Account account) {
        this.owned = owned;
        this.portfolioValueHistory = portfolioValueHistory;
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }

    // Long corresponds to the cryptocurrency id
    private Map<Long, Long> owned;
    // A list of computed sum of market value of assets at time of calculation
    private Map<Date, Long> portfolioValueHistory;

    public Map<Long, Long> getOwned() {
        return owned;
    }

    public void setOwned(Map<Long, Long> owned) {
        this.owned = owned;
    }

    public Map<Date, Long> getPortfolioValueHistory() {
        return portfolioValueHistory;
    }

    public void setPortfolioValueHistory(Map<Date, Long> portfolioValueHistory) {
        this.portfolioValueHistory = portfolioValueHistory;
    }
}
