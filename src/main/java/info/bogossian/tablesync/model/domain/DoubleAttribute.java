package info.bogossian.tablesync.model.domain;

public class DoubleAttribute extends Attribute {
	private Double value;

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "DoubleAttribute [value=" + value + "]";
	}
}
