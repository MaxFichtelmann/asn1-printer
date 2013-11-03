package de.fichtelmax.asn1.ber;

public class IncompleteException extends RuntimeException
{
    private static final long serialVersionUID = 5302636385504181272L;
    
    public IncompleteException()
    {
        super();
    }
    
    public IncompleteException( String message, Throwable cause )
    {
        super( message, cause );
    }
    
    public IncompleteException( String message )
    {
        super( message );
    }
    
    public IncompleteException( Throwable cause )
    {
        super( cause );
    }
}
