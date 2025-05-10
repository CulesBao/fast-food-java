package BLL;

import DAL.FoodDAL;
import DTO.FoodDTO;
import DTO.ResponseDTO;
import java.math.BigDecimal;

public class FoodBLL {
  private final FoodDAL foodDAL = new FoodDAL();

  private void checkFood(FoodDTO foodDTO) throws Exception {
    if (foodDTO.getName() == null || foodDTO.getName().isEmpty()) {
      throw new Exception("Food name cannot be null or empty");
    }
    if (foodDTO.getPrice() == null || foodDTO.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
      throw new Exception("Food price must be greater than zero");
    }
    if (foodDTO.getQuantity() < 0) {
      throw new Exception("Food quantity cannot be negative");
    }
  }

  public ResponseDTO getFoodsByName(String name) {
    try {
      return new ResponseDTO(true, "Get food by name successfully", foodDAL.getFoodsByName(name));
    } catch (Exception e) {
      return new ResponseDTO(false, e.getMessage(), null);
    }
  }

  public ResponseDTO addNewFood(FoodDTO foodDTO) {
    try {
      checkFood(foodDTO);
      foodDAL.createFood(foodDTO);
      return new ResponseDTO(true, "Add new food successfully", null);
    } catch (Exception e) {
      return new ResponseDTO(false, e.getMessage(), null);
    }
  }

  public ResponseDTO updateFood(FoodDTO foodDTO) {
    try {
      checkFood(foodDTO);
      foodDAL.updateFood(foodDTO);
      return new ResponseDTO(true, "Update food successfully", null);
    } catch (Exception e) {
      return new ResponseDTO(false, e.getMessage(), null);
    }
  }

  public ResponseDTO deleteFood(int foodId) {
    try {
      foodDAL.deleteFood(foodId);
      return new ResponseDTO(true, "Delete food successfully", null);
    } catch (Exception e) {
      return new ResponseDTO(false, e.getMessage(), null);
    }
  }
}
