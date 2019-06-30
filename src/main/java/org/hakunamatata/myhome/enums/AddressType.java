package org.hakunamatata.myhome.enums;

public enum AddressType
{
    PRIMARY(0),
    SECONDARY(1);
 
    // internal state
    private int type;
	
	// constructor
    private AddressType(final int type) {
        this.type = type;
    }
 
    public int getValue() {
        return type;
    }
}