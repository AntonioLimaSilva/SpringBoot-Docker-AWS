package br.com.luciano.beerstore.model;

public enum BeerType {

    LAGER("Lager"),
    PILSEN("Pilsen"),
    IPA("Ipa");

    private final String description;

    BeerType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
