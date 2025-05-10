package BLL;

import DAL.FoodDAL;
import DAL.OrderDAL;
import DAL.OrderItemDAL;
import DTO.DetailOrderItem;
import DTO.FindDetailOrder;
import DTO.ResponseDTO;
import java.util.List;

public class OrderBLL {
  private static final OrderDAL orderDAL = new OrderDAL();
  private static final OrderItemDAL orderItemDAL = new OrderItemDAL();
  private static final FoodDAL foodDAL = new FoodDAL();

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
        foodDAL.updateQuantityByTransactionId(detailOrderItem.getId(), detailOrderItem.getQuantity());
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

  public ResponseDTO deleteOrder(int orderId) {
    try {
      orderDAL.deleteOrder(orderId);
      return new ResponseDTO(true, "Delete order successfully", null);
    } catch (Exception e) {
      return new ResponseDTO(false, e.getMessage(), null);
    }
  }
}
