package com.thulasi.easyway.payload.response;

import com.thulasi.easyway.type.MeasurementUnit;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
@Builder
public class SalesOrderResponseDto {

	private Long orderId;

	private String orderRefNo;

	private Long invoiceId;

	private String invoiceRefNo;

	private BigDecimal amount;

}
