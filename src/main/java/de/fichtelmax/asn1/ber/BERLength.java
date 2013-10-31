package de.fichtelmax.asn1.ber;

public class BERLength
{
    byte[]  lengthBytes;
    boolean indefinite;
    int     length;
    
    public BERLength( int length, byte... lengthBytes )
    {
        this.lengthBytes = lengthBytes;
        this.length = length;
    }
    
    public BERLength( boolean indefinite )
    {
        this.indefinite = indefinite;
    }
    
    public byte[] getLengthBytes()
    {
        return lengthBytes;
    }
    
    public boolean isIndefinite()
    {
        return indefinite;
    }
    
    public int getLength()
    {
        return length;
    }
}
