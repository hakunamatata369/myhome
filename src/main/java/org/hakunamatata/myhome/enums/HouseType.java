package org.hakunamatata.myhome.enums;

public enum HouseType {

	INDEPENDENT_HOUSE(0),
    STANDALONE_APARTMENT(1),
    GATEDCOMMUNITY_APARTMENT(2),
    GATEDCOMMUNITY_VILLA(3);
 
    // internal state
    private int type;
	
	// constructor
    private HouseType(final int type) {
        this.type = type;
    }
 
    public int getValue() {
        return type;
    }
}
