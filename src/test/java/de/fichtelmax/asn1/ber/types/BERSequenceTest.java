package de.fichtelmax.asn1.ber.types;

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
import java.util.Iterator;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERUTF8String;
import org.junit.Test;

import de.fichtelmax.asn1.ber.BERObject;

public class BERSequenceTest
{
    BERSequence cut;
    
    @Test
    public void create() throws IOException
    {
        DERUTF8String string1 = new DERUTF8String( "some string" );
        DERUTF8String string2 = new DERUTF8String( "some other string" );
        
        byte[] bcEncoded = new DERSequence( new ASN1Encodable[] { string1, string2 } ).getEncoded();
        
        cut = new BERSequence( new byte[] { bcEncoded[1] }, Arrays.copyOfRange( bcEncoded, 2, bcEncoded.length ) );
        
        assertThat( cut.getEncoded(), is( equalTo( bcEncoded ) ) );
        
        Iterator<BERObject> iterator = cut.iterator();
        
        assertThat( iterator.next().getEncoded(), is( equalTo( string1.getEncoded() ) ) );
        assertThat( iterator.next().getEncoded(), is( equalTo( string2.getEncoded() ) ) );
        assertThat( iterator.hasNext(), is( false ) );
    }
}
