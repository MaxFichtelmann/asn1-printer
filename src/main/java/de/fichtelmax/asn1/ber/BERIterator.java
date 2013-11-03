package de.fichtelmax.asn1.ber;

import java.util.Arrays;
import java.util.Iterator;

public class BERIterator implements Iterator<BERObject>
{
    private BERParser parser;
    private byte[]    data;
    private int       cursor;
    
    public BERIterator( BERParser parser, byte[] data )
    {
        this.parser = parser;
        this.data = data;
    }
    
    @Override
    public boolean hasNext()
    {
        return cursor < data.length;
    }
    
    @Override
    public BERObject next()
    {
        byte[] remaining = Arrays.copyOfRange( data, cursor, data.length );
        BERObject berObject = parser.parse( remaining );
        cursor += berObject.getEncoded().length;
        
        return berObject;
    }
    
    @Override
    public void remove()
    {
        // TODO NYI Auto-generated method stub
        throw new UnsupportedOperationException();
    }
    
}
