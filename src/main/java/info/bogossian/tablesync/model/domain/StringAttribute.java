package info.bogossian.tablesync.model.domain;

public class StringAttribute extends Attribute {
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "StringAttribute [value=" + value + "]";
	}
}
