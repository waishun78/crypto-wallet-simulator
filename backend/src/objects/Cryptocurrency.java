package objects;

import java.net.URI;
import java.time.LocalDateTime;

public class Cryptocurrency extends DomainEntity{
    private String id, symbol, name;
    private long currentPriceUSD;
    private LocalDateTime lastRetrieved, lastUpdated;
    private URI logoURL;
}
