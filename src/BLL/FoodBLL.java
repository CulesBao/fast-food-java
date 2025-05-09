package BLL;

import DAL.FoodDAL;
import DTO.FoodDTO;
import DTO.ResponseDTO;

public class FoodBLL {
  private final FoodDAL foodDAL = new FoodDAL();

  public ResponseDTO getFoodsByName(String name) {
    try {
      return new ResponseDTO(true, "Get food by name successfully", foodDAL.getFoodsByName(name));
    } catch (Exception e) {
      return new ResponseDTO(false, e.getMessage(), null);
    }
  }

  public ResponseDTO addNewFood(FoodDTO foodDTO) {
    try {
      foodDAL.createFood(foodDTO);
      return new ResponseDTO(true, "Add new food successfully", null);
    } catch (Exception e) {
      return new ResponseDTO(false, e.getMessage(), null);
    }
  }

  public ResponseDTO updateFood(FoodDTO foodDTO) {
    try {
      foodDAL.updateFood(foodDTO);
      return new ResponseDTO(true, "Update food successfully", null);
    } catch (Exception e) {
      return new ResponseDTO(false, e.getMessage(), null);
    }
  }
}
