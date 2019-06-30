package org.hakunamatata.myhome.enums;

public enum VehicleType {

	TWO_WHEELER(0),
	FOUR_WHEELER(1);
 
    // internal state
    private int type;
	
	// constructor
    private VehicleType(final int type) {
        this.type = type;
    }
 
    public int getValue() {
        return type;
    }
}
