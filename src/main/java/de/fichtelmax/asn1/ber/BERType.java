package de.fichtelmax.asn1.ber;

import java.util.Arrays;

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
    
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode( type );
        return result;
    }
    
    @Override
    public boolean equals( Object obj )
    {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        BERType other = (BERType) obj;
        if ( !Arrays.equals( type, other.type ) )
            return false;
        return true;
    }
}
