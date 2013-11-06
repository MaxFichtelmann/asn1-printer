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


import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

import javax.xml.bind.DatatypeConverter;

import org.bouncycastle.util.Arrays;
import org.junit.Test;

public class BERObjectTest
{
    BERObject cut;
    
    @Test
    public void toStringShouldUseCompleteASN1()
    {
        // not really ASN1/TLV - should not be neccessary for data object
        byte[] type = new byte[] { 0x01 };
        byte[] length = new byte[] { 0x02 };
        byte[] value = new byte[] { 0x03 };
        
        cut = new BERObject( type, length, value );
        
        assertThat( cut.toString(), containsString( DatatypeConverter.printBase64Binary( Arrays.concatenate( type, length, value ) ) ) );
    }
}
