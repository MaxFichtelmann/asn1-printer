package de.fichtelmax.asn1;

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

import org.junit.Before;
import org.junit.Test;

public class HexReaderTest
{
    HexReader cut;
    
    @Before
    public void setup()
    {
        cut = new HexReader();
    }
    
    @Test
    public void simple()
    {
        String byteString = "00 11 22 33 44 55 AA FF";
        byte[] expected = new byte[] { 0x00, 0x11, 0x22, 0x33, 0x44, 0x55, (byte) 0xAA, (byte) 0xFF };
        
        assertThat( cut.toBytes( byteString ), is( equalTo( expected ) ) );
    }
    
    @Test
    public void noWhiteSpaces()
    {
        String byteString = "001122334455AAFF";
        byte[] expected = new byte[] { 0x00, 0x11, 0x22, 0x33, 0x44, 0x55, (byte) 0xAA, (byte) 0xFF };
        
        assertThat( cut.toBytes( byteString ), is( equalTo( expected ) ) );
    }
    
    @Test
    public void withLineBreaks()
    {
        String byteString = "00 11\n22 33\r 44 55\r\n AA FF";
        byte[] expected = new byte[] { 0x00, 0x11, 0x22, 0x33, 0x44, 0x55, (byte) 0xAA, (byte) 0xFF };
        
        assertThat( cut.toBytes( byteString ), is( equalTo( expected ) ) );
    }
    
    @Test
    public void withInconsistentWhiteSpaces()
    {
        String byteString = "0011 22 33 4455 AA FF";
        byte[] expected = new byte[] { 0x00, 0x11, 0x22, 0x33, 0x44, 0x55, (byte) 0xAA, (byte) 0xFF };
        
        assertThat( cut.toBytes( byteString ), is( equalTo( expected ) ) );
    }
    
    @Test( expected = IllegalArgumentException.class )
    public void invalidCharacterInputShouldThrow()
    {
        String byteString = "xx 11 22 33 44 55 AA FF";
        
        cut.toBytes( byteString );
    }
    
    @Test( expected = IllegalArgumentException.class )
    public void unevenNumberOfHexNumsShouldThrow()
    {
        String byteString = "000 11 22 33 44 55 AA FF";
        
        cut.toBytes( byteString );
    }
}
