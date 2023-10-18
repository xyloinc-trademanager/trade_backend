package com.tm.app.entity;

import java.sql.Date;

import org.hibernate.annotations.Subselect;
import org.springframework.data.annotation.Immutable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
@Immutable
@Subselect("select DATE_TRUNC('month', update_at) as date, SUM(tpd.quantity) as quantity from t_production tp join t_production_details tpd on (tp.id = tpd.production_id) join t_item_master tim on (tim.id = tp.item_id) where update_at >= CURRENT_DATE - interval '30 days' group by DATE_TRUNC('month', update_at) order by DATE_TRUNC('month', update_at) asc;")
public class MonthlyProductionCount {

	@Id

	private Date date;
	private Integer quantity;

}
