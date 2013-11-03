package de.fichtelmax.asn1.ber.types;

import java.util.Iterator;

import de.fichtelmax.asn1.ASN1Constructed;
import de.fichtelmax.asn1.ber.BERIterator;
import de.fichtelmax.asn1.ber.BERObject;
import de.fichtelmax.asn1.ber.BERParser;

public class BERSequence extends BERObject implements ASN1Constructed
{
    private BERParser parser = BERParser.DEFAULT;
    
    public BERSequence( byte[] length, byte[] value )
    {
        super( new byte[] { 0x30 }, length, value );
    }
    
    public void setParser( BERParser parser )
    {
        this.parser = parser;
    }
    
    @Override
    public Iterator<BERObject> iterator()
    {
        return new BERIterator( parser, getValue() );
    }
    
    @Override
    public String getName()
    {
        return "SEQUENCE";
    }
    
    @Override
    public String getValueString()
    {
        return "...";
    }
}
