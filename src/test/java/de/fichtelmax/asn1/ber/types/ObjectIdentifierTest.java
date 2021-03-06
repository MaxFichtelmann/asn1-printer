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

import org.bouncycastle.asn1.DERObjectIdentifier;
import org.junit.Test;

public class ObjectIdentifierTest
{
    ObjectIdentifier cut;
    
    @Test
    public void create() throws IOException
    {
        String oidString = "1.3.14.25.314001";
        DERObjectIdentifier bcOid = new DERObjectIdentifier( oidString );
        
        byte[] encoded = bcOid.getEncoded();
        byte[] bcOidValue = Arrays.copyOfRange( encoded, 2, encoded.length );
        
        cut = new ObjectIdentifier( new byte[] { encoded[1] }, bcOidValue );
        
        assertThat( cut.getOid(), is( equalTo( oidString ) ) );
    }
}
