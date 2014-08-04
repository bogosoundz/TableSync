package info.bogossian.tablesync.model.domain;

import java.sql.Timestamp;

public class TimestampAttribute extends Attribute {
	private Timestamp value;

	public Timestamp getValue() {
		return value;
	}

	public void setValue(Timestamp value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "TimestampAttribute [value=" + value + "]";
	}


}
