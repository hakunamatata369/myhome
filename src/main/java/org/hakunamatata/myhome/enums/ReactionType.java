package org.hakunamatata.myhome.enums;

public enum ReactionType {

    SHARE(0),
    LIKE(1),
    LOVE(2),
    HAHA(3),
    WOW(4),
    SAD(5),
    ANGRY(6);
 
    // internal state
    private int type;
	
	// constructor
    private ReactionType(final int type) {
        this.type = type;
    }
 
    public int getValue() {
        return type;
    }
}
