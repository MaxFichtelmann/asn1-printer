package de.fichtelmax.asn1.ber;

public class BERType
{
    private byte[]  type;
    private boolean constructed;
    
    public BERType( boolean constructed, byte... type )
    {
        this.constructed = constructed;
        this.type = type;
    }
    
    public byte[] getType()
    {
        return type;
    }
    
    public boolean isConstructed()
    {
        return constructed;
    }
}
