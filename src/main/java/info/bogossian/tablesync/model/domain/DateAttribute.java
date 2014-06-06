package info.bogossian.tablesync.model.domain;

import java.util.Date;

public class DateAttribute extends Attribute {
	private Date value;

	public Date getValue() {
		return value;
	}

	public void setValue(Date value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "DateAttribute [value=" + value + "]";
	}
}
