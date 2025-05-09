package BLL;

import DAL.OrderItemDAL;
import DTO.ResponseDTO;

public class OrderItemBLL {
  private static OrderItemDAL orderItemDAL = new OrderItemDAL();

  public ResponseDTO getOrderItemsByOrderId(int orderId) {
    try {
      return new ResponseDTO(
          true, "Order items fetched successfully", orderItemDAL.getOrderItemsByOrderId(orderId));
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseDTO(
          false, "Error occurred while fetching order items: " + e.getMessage(), null);
    }
  }
}
