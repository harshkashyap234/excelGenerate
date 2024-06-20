package com.example.MongoToExcel.Mapper;


import com.example.MongoToExcel.Model.Orders;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Mapper
public interface OrdersClassMapper {

    OrdersClassMapper INSTANCE = Mappers.getMapper(OrdersClassMapper.class);

    default Map<String, Object> toMap(Orders orders) {
        if (orders == null) {
            return null;
        }

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("id", orders.getId());
        map.put("orgCode", orders.getOrgCode());
        map.put("transactionTime", orders.getTransactionTime());
        map.put("transactionType", orders.getTransactionType());
        map.put("transactionReference", orders.getTransactionReference());
        map.put("chargeSubType", orders.getChargeSubType());
        map.put("courierAwbNo", orders.getCourierAwbNo());
        map.put("billingPeriodId", orders.getBillingPeriodId());
        map.put("gstPercentage", orders.getGstPercentage());
        map.put("createdBy", orders.getCreatedBy());
        map.put("updatedBy", orders.getUpdatedBy());
        map.put("createdAt", orders.getCreatedAt());
        map.put("modifiedAt", orders.getModifiedAt());
        map.put("version", orders.getVersion());
        map.put("deleted", orders.getDeleted());
        map.put("_class", orders.get_class());

        // Nested objects
        map.put("chargeDetails", toMap(orders.getChargeDetails()));
        map.put("totalCharges", toMap(orders.getTotalCharges()));
        map.put("totalChargesWithGst", toMap(orders.getTotalChargesWithGst()));

        return map;
    }

    default Map<String, Object> toMap(Orders.ChargeDetails chargeDetails) {
        if (chargeDetails == null) {
            return null;
        }

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("chargeType", chargeDetails.getChargeType());
        map.put("_class", chargeDetails.get_class());
        map.put("orderValue", toMap(chargeDetails.getOrderValue()));
        map.put("percentageFee", chargeDetails.getPercentageFee());
        map.put("activationDate", chargeDetails.getActivationDate());
        map.put("endDate", chargeDetails.getEndDate());
        map.put("platformName", chargeDetails.getPlatformName());
        map.put("liveDate", chargeDetails.getLiveDate());
        map.put("shipmentValue", toMap(chargeDetails.getShipmentValue()));
        map.put("shipmentCreationDate", chargeDetails.getShipmentCreationDate());
        map.put("sourcePincode", chargeDetails.getSourcePincode());
        map.put("sourceCity", chargeDetails.getSourceCity());
        map.put("destinationPincode", chargeDetails.getDestinationPincode());
        map.put("destinationCity", chargeDetails.getDestinationCity());
        map.put("zone", chargeDetails.getZone());
        map.put("courierCode", chargeDetails.getCourierCode());
        map.put("deadWeight", chargeDetails.getDeadWeight());
        map.put("volumeWeight", chargeDetails.getVolumeWeight());
        map.put("chargedWeight", chargeDetails.getChargedWeight());
        map.put("weightSlab", chargeDetails.getWeightSlab());
        map.put("shipmentType", chargeDetails.getShipmentType());
        map.put("paymentMode", chargeDetails.getPaymentMode());
        map.put("freightCharges", toMap(chargeDetails.getFreightCharges()));
        map.put("codCharges", toMap(chargeDetails.getCodCharges()));

        return map;
    }

    default Map<String, Object> toMap(Orders.OrderValue orderValue) {
        if (orderValue == null) {
            return null;
        }

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("currencyCode", orderValue.getCurrencyCode());
        map.put("amount", orderValue.getAmount());

        return map;
    }

    default Map<String, Object> toMap(Orders.ShipmentValue shipmentValue) {
        if (shipmentValue == null) {
            return null;
        }

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("currencyCode", shipmentValue.getCurrencyCode());
        map.put("amount", shipmentValue.getAmount());

        return map;
    }

    default Map<String, Object> toMap(Orders.TotalCharges totalCharges) {
        if (totalCharges == null) {
            return null;
        }

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("currencyCode", totalCharges.getCurrencyCode());
        map.put("amount", totalCharges.getAmount());

        return map;
    }

    default Map<String, Object> toMap(Orders.FreightCharges freightCharges) {
        if (freightCharges == null) {
            return null;
        }

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("currencyCode", freightCharges.getCurrencyCode());
        map.put("amount", freightCharges.getAmount());

        return map;
    }

    default Map<String, Object> toMap(Orders.CodCharges codCharges) {
        if (codCharges == null) {
            return null;
        }

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("currencyCode", codCharges.getCurrencyCode());
        map.put("amount", codCharges.getAmount());

        return map;
    }


}
