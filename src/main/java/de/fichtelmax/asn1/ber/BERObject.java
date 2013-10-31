package de.fichtelmax.asn1.ber;

public class BERObject
{
    private byte[] type;
    private byte[] value;
    
    public BERObject( byte[] type, byte[] value )
    {
        this.type = type;
        this.value = value;
    }
    
    public byte[] getType()
    {
        return type;
    }
    
    public byte[] getValue()
    {
        return value;
    }
}
