package info.bogossian.tablesync.model.domain;


public class IntegerAttribute extends Attribute {
	private Integer value;

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "IntegerAttribute [value=" + value + "]";
	}
}
