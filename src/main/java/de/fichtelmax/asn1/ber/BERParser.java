package de.fichtelmax.asn1.ber;

import java.util.Arrays;

public class BERParser
{
    public static BERParser  DEFAULT = new BERParser( new BERTypeParser(), new BERLengthParser(), new BERObjectFactory() );
    
    private BERTypeParser    typeParser;
    private BERLengthParser  lengthParser;
    private BERObjectFactory factory;
    
    public BERParser( BERTypeParser typeParser, BERLengthParser lengthParser, BERObjectFactory factory )
    {
        this.typeParser = typeParser;
        this.lengthParser = lengthParser;
        this.factory = factory;
    }
    
    public BERObject parse( byte[] encoded )
    {
        int cursor = 0;
        try
        {
            
            BERType type = typeParser.parseType( encoded, cursor );
            cursor += type.getType().length;
            BERLength length = lengthParser.parseLength( encoded, cursor );
            if ( !length.isIndefinite() )
            {
                cursor += length.getLengthBytes().length;
            }
            
            byte[] value = Arrays.copyOfRange( encoded, cursor, cursor + length.getLength() );
            
            return factory.create( type.getType(), length.getLengthBytes(), value );
        }
        catch ( Throwable t )
        {
            throw new IncompleteException( t );
        }
    }
}
