package org.app.business;

import org.app.dao.PortfolioRepository;
import org.app.domain.Portfolio;

public class PortfolioService extends AbstractCrudService<Portfolio>{
    protected PortfolioService(PortfolioRepository repository) {
        super(repository);
    }
}
