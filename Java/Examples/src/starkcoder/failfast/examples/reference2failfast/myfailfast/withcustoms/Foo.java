package starkcoder.failfast.examples.reference2failfast.myfailfast.withcustoms;

/**
 * Example of a custom type that you can support with custom checker-failer pairs.
 * 
 * @author Keld Oelykke
 *
 */
public class Foo
{
	public Foo(boolean isBar)
	{
		this.setBar(isBar);
	}
	
	private Boolean isBar;
	public Boolean isBar()
	{
		return this.isBar;
	}
	protected void setBar(boolean isBar)
	{
		this.isBar = isBar;
	}
}
