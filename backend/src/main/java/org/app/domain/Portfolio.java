package org.app.domain;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Portfolio extends DomainEntity{

    public Portfolio(Map<Long, Number> owned, Map<Date, Number> portfolioValueHistory) {
        this.owned = owned;
        this.portfolioValueHistory = portfolioValueHistory;
    }

    // Long corresponds to the cryptocurrency id
    private Map<Long, Number> owned;
    // A list of computed sum of market value of assets at time of calculation
    private Map<Date, Number> portfolioValueHistory;

    public Map<Long, Number> getOwned() {
        return owned;
    }

    public void setOwned(Map<Long, Number> owned) {
        this.owned = owned;
    }

    public Map<Date, Number> getPortfolioValueHistory() {
        return portfolioValueHistory;
    }

    public void setPortfolioValueHistory(Map<Date, Number> portfolioValueHistory) {
        this.portfolioValueHistory = portfolioValueHistory;
    }
}
