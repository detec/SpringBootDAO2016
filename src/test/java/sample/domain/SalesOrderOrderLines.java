
package sample.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SalesOrderOrderLines", indexes = { @Index(columnList = "salesOrder", name = "salesOrder") })
public class SalesOrderOrderLines extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -3601319955619606261L;

	@ManyToOne
	@JoinColumn(name = "salesOrder", unique = false, nullable = false, foreignKey = @ForeignKey(name = "FK_SalesOrder"))
	private SalesOrder salesOrder;

	private long lineNumber;

	@Column(name = "quantity", unique = false, nullable = true, precision = 10)
	private long quantity;

	public SalesOrderOrderLines(SalesOrder salesOrder) {

		this.salesOrder = salesOrder;

	}

	public SalesOrderOrderLines(long quantity, SalesOrder salesOrder) {

		this.quantity = quantity;
		this.salesOrder = salesOrder;

	}

	public SalesOrderOrderLines() {
	}

	@Override
	public String toString() {
		return "[SalesOrderOrderLines], id " + String.valueOf(this.getId());
	}

	public SalesOrder getSalesOrder() {
		return this.salesOrder;
	}

	public void setSalesOrder(SalesOrder salesOrder) {
		this.salesOrder = salesOrder;
	}

	public long getLineNumber() {
		return this.lineNumber;
	}

	public void setLineNumber(long lineNumber) {
		this.lineNumber = lineNumber;
	}

	public long getQuantity() {
		return this.quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

}