package org.hakunamatata.myhome.enums;

public enum MemberType
{
    USER(0),
    GROUP(1),
    ROLE(2);
 
    // internal state
    private int type;
	
	// constructor
    private MemberType(final int type) {
        this.type = type;
    }
 
    public int getValue() {
        return type;
    }
}