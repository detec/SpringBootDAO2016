
package sample.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name = "SalesOrder", indexes = { @Index(columnList = "totalPrice", name = "totalPrice") })
public class SalesOrder extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -8750308410606985239L;

	@Column
	private LocalDateTime date;

	@Column(name = "totalPrice", unique = false, nullable = true, precision = 15, scale = 2)
	private BigDecimal totalPrice;

	@OneToMany(mappedBy = "salesOrder", fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
	@OrderColumn(name = "lineNumber")
	private List<SalesOrderOrderLines> orderLines = new ArrayList<>();

	public SalesOrder(LocalDateTime date, BigDecimal totalPrice, List<SalesOrderOrderLines> orderLines) {

		this.date = date;
		this.totalPrice = totalPrice;
		this.orderLines = orderLines;

	}

	public SalesOrder() {
	}

	@Override
	public String toString() {
		return "[SalesOrder], id " + String.valueOf(this.getId());
	}

	@PrePersist
	@PreUpdate
	private void updateDate() {
		this.date = LocalDateTime.now();
	}

	public LocalDateTime getDate() {
		return this.date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public BigDecimal getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public List<SalesOrderOrderLines> getOrderLines() {
		return this.orderLines;
	}

	public void setOrderLines(List<SalesOrderOrderLines> orderLines) {
		this.orderLines = orderLines;
	}

}