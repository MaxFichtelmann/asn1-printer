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


import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;
import java.util.Arrays;

import org.bouncycastle.asn1.DERObjectIdentifier;
import org.bouncycastle.asn1.DERUTF8String;
import org.junit.Before;
import org.junit.Test;

public class BERParserTest
{
    BERParser cut;
    
    @Before
    public void setup()
    {
        // assume friendly subparsers
        cut = new BERParser( new BERTypeParser(), new BERLengthParser(), new BERObjectFactory() );
    }
    
    @Test
    public void utf8String() throws IOException
    {
        String text = "something";
        DERUTF8String utf8String = new DERUTF8String( text );
        
        BERObject berObject = cut.parse( utf8String.getEncoded() );
        
        assertThat( berObject.getValue(), is( equalTo( text.getBytes( "UTF-8" ) ) ) );
    }
    
    @Test
    public void objectIdentifier() throws IOException
    {
        String oidString = "1.2.3.15.300";
        
        byte[] bcEncoded = new DERObjectIdentifier( oidString ).getEncoded();
        
        BERObject berObject = cut.parse( bcEncoded );
        
        byte[] bcValue = Arrays.copyOfRange( bcEncoded, 2, bcEncoded.length );
        assertThat( berObject.getValue(), is( equalTo( bcValue ) ) );
    }
}
