package de.fichtelmax.asn1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import de.fichtelmax.asn1.ber.BERParser;

public class Main
{
    public static void main( String[] args ) throws IOException
    {
        BufferedReader input = new BufferedReader( new InputStreamReader( System.in ) );
        StringBuilder builder = new StringBuilder();
        
        String line = null;
        while ( (line = input.readLine()) != null )
        {
            builder.append( line );
        }
        
        System.out.println( "Input was:" );
        System.out.println( builder.toString() );
        
        System.out.println( "=============================" );
        System.out.println( "parsing..." );
        
        HexReader hexReader = new HexReader();
        byte[] data = hexReader.toBytes( builder.toString() );
        
        ASN1Printer printer = new ASN1Printer( System.out, BERParser.DEFAULT );
        
        printer.print( data );
    }
}
