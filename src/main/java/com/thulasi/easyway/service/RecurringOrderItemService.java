package com.thulasi.easyway.service;

import com.thulasi.easyway.payload.request.RecurringOrderRequestDto;
import com.thulasi.easyway.payload.response.ResponseEntityDto;

public interface RecurringOrderItemService {

    ResponseEntityDto createRecurringOrder(RecurringOrderRequestDto dto);

    ResponseEntityDto addToRecurringOrderItem(Long recurringOrderId, Long productId);

    ResponseEntityDto removeFromRecurringOrderItem(Long recurringOrderId, Long productId);

    ResponseEntityDto getMyRecurringOrder();
}
