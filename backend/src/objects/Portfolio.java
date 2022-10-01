package objects;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Portfolio extends DomainEntity{
    private Map<Cryptocurrency, Number> owned;
    private Collection<Number> lastValue;
}
