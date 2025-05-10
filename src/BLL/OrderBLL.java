package BLL;

import DAL.OrderDAL;
import DAL.OrderItemDAL;
import DTO.DetailOrderItem;
import DTO.FindDetailOrder;
import DTO.ResponseDTO;
import java.util.List;

public class OrderBLL {
  private final static OrderDAL orderDAL = new OrderDAL();
  private final static OrderItemDAL orderItemDAL = new OrderItemDAL();

  public ResponseDTO createNewOrder(
      int staffId,
      String customerName,
      String customerPhoneNumber,
      List<DetailOrderItem> detailOrderItemList) {
    try {
      int orderId = orderDAL.createNewOrder(staffId, customerName, customerPhoneNumber);
      for (DetailOrderItem detailOrderItem : detailOrderItemList) {
        orderItemDAL.addDetailOrderItem(
            orderId, detailOrderItem.getId(), detailOrderItem.getQuantity());
      }
      return new ResponseDTO(true, "Create new order successfully", null);
    } catch (Exception e) {
      return new ResponseDTO(false, e.getMessage(), null);
    }
  }

  public ResponseDTO getDetailOrderByDateTime(int staffId, String startDate, String endDate) {
    try {
      List<FindDetailOrder> detailOrderItems =
          orderDAL.getDetailOrderByDateTime(staffId, startDate, endDate);
      return new ResponseDTO(true, "Get detail order successfully", detailOrderItems);
    } catch (Exception e) {
      return new ResponseDTO(false, e.getMessage(), null);
    }
  }

  public ResponseDTO updateOrderStatus(int orderId, String status) {
    try {
      orderDAL.updateOrderStatus(orderId, status);
      return new ResponseDTO(true, "Update status order successfully", null);
    } catch (Exception e) {
      return new ResponseDTO(false, e.getMessage(), null);
    }
  }
}
