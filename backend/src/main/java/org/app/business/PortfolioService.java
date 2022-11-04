package org.app.business;

import org.app.dao.PortfolioRepository;
import org.app.domain.Account;
import org.app.domain.Portfolio;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PortfolioService extends AbstractCrudService<Portfolio>{
    protected PortfolioService(PortfolioRepository repository) {
        super(repository);
    }

    /**
     * Buy Currency.
     *
     * @param cryptocurrencyID id of cryptocurrency
     * @param portfolioID key of the portfolio
     * @param portfolioID key of the portfolio
     * @param quantity cryptocurrency being transacted
     * @throws NoSuchElementException if the porfolio do not exist
     */
    // TODO 2: Do I take in the API exchange rate or pass in the exchange rate from Frontend
    public void buyCurrency(Long portfolioID, Long cryptocurrencyID, Long quantity, Long exchangeRate){
        // Get portfolio ID
        Optional<Portfolio> optionalPortfolio = readById(portfolioID);
        Portfolio portfolio = optionalPortfolio.orElseThrow();
        // Check if portfolio contains enough money
        Account account = portfolio.getAccount();
        if (account.getAccountBalance() > quantity*exchangeRate){
            Long v = account.getAccountBalance();
            account.setAccountBalance(v-quantity*exchangeRate);
            // If it is owned, increment quantity
            if (portfolio.getOwned().containsKey(cryptocurrencyID)){
                // TODO 1: Is there a better way to do this, seems like a lot of chaining
                Map<Long, Long> owned = portfolio.getOwned();
                owned.put(cryptocurrencyID,owned.get(cryptocurrencyID) + quantity);
                portfolio.setOwned(owned);
            } else {
                Map<Long, Long> owned = portfolio.getOwned();
                owned.put(cryptocurrencyID, quantity);
                portfolio.setOwned(owned);
            }
            // TODO 2: Some way to create a transaction object
            // How do we reference the repository to add transaction object?

        }

    }
    /**
     * Sell Currency.
     *
     * @param cryptocurrencyID id of cryptocurrency
     * @param portfolioID key of the portfolio
     * @param portfolioID key of the portfolio
     * @param quantity cryptocurrency being transacted
     * @throws NoSuchElementException if the porfolio do not exist
     */
    public void sellCurrency(Long portfolioID, Long cryptocurrencyID, Long quantity, Long exchangeRate){
        // Get portfolio ID
        Optional<Portfolio> optionalPortfolio = readById(portfolioID);
        Portfolio portfolio = optionalPortfolio.orElseThrow();
        // Check if portfolio contains enough money
        Account account = portfolio.getAccount();
        if (portfolio.getOwned().get(cryptocurrencyID) >= quantity){
            Map<Long, Long> owned = portfolio.getOwned();
            owned.put(cryptocurrencyID,owned.get(cryptocurrencyID) - quantity);
            portfolio.setOwned(owned);
            // TODO 2: Some way to create a transaction object

            }

        }


}


