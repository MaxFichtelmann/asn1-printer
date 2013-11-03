package de.fichtelmax.asn1;

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
