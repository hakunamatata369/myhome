package org.hakunamatata.myhome.enums;

public enum NodeType
{
    FOLDER(0),
    SHORTCUT(1),
    DOCUMENT(2),
    IMAGE(3),
    AUDIO(4),
    VIDEO(5),
    COMMENT(10),
    HOUSE(1001),
    FLAT(1002);
 
    // internal state
    private int type;
	
	// constructor
    private NodeType(final int type) {
        this.type = type;
    }
 
    public int getValue() {
        return type;
    }
}