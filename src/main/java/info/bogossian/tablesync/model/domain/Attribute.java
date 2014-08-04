package info.bogossian.tablesync.model.domain;

public class Attribute {
	private String name;
	private String typeName;
	
	protected Attribute()
	{
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	public static Attribute getAttribute(String type)
	{
		Attribute attribute=null;
		if("java.lang.String".equalsIgnoreCase(type))
		{
			attribute = new StringAttribute();
		} 
		else if("java.lang.Double".equalsIgnoreCase(type))
		{
			attribute = new DoubleAttribute();
		}
		else if("java.lang.Integer".equalsIgnoreCase(type))
		{
			attribute = new IntegerAttribute();
		}
		else if("java.util.Date".equalsIgnoreCase(type))
		{
			attribute = new DateAttribute();
		} 
		else if("java.sql.TimeStamp".equalsIgnoreCase(type))
		{
			attribute = new TimestampAttribute();
		} 
		if(attribute==null)
		{
			throw new NullPointerException("Could not determine the attribute type: " +type);
		}
		attribute.setTypeName(type);
		return attribute;
	}

	@Override
	public String toString() {
		return "Attribute [name=" + name + ", typeName=" + typeName + "]";
	}
}
