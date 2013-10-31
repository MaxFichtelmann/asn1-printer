package de.fichtelmax.asn1.ber;

import java.util.Arrays;

public class BERParser
{
    BERTypeParser   typeParser;
    BERLengthParser lengthParser;
    
    public BERParser( BERTypeParser typeParser, BERLengthParser lengthParser )
    {
        this.typeParser = typeParser;
        this.lengthParser = lengthParser;
    }
    
    public BERObject parse( byte[] encoded )
    {
        int cursor = 0;
        
        BERType type = typeParser.parseType( encoded, cursor );
        cursor += type.getType().length;
        BERLength length = lengthParser.parseLength( encoded, cursor );
        if ( !length.isIndefinite() )
        {
            cursor += length.getLengthBytes().length;
        }
        
        byte[] value = Arrays.copyOfRange( encoded, cursor, cursor + length.getLength() );
        
        return new BERObject( type.getType(), value );
    }
}
