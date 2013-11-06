package de.fichtelmax.asn1.ber;

/*
 * #%L
 * asn1-printer
 * %%
 * Copyright (C) 2013 Max Fichtelmann
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 3 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */


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
