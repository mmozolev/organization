package currency;

public enum Currency {
    RUB (643, "RUB"),
    USD (840, "USD"),
    EUR (978, "EUR"),
    CNY (156, "CNY"),
    GBP (826, "GBP");

    private final int id;
    private final String name;

    Currency(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
