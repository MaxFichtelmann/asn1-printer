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

import org.bouncycastle.asn1.DERUTF8String;
import org.junit.Test;

public class UTF8StringTest
{
    UTF8String cut;
    
    @Test
    public void create() throws IOException
    {
        String string = "Test string including strange chars ä ® €";
        DERUTF8String bcString = new DERUTF8String( string );
        
        byte[] bcEncoded = bcString.getEncoded();
        byte[] bcValue = Arrays.copyOfRange( bcEncoded, 2, bcEncoded.length );
        
        cut = new UTF8String( new byte[] { bcEncoded[1] }, bcValue );
        
        assertThat( cut.getString(), is( equalTo( string ) ) );
    }
}
