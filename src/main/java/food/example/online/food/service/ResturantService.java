package food.example.online.food.service;

import java.util.List;

import food.example.online.food.dto.ResturantDTO;
import food.example.online.food.dto.ResturantReq;
import food.example.online.food.dto.ResturantResponseDTO;
import food.example.online.food.entity.Resturant;
import food.example.online.food.entity.User;

public interface ResturantService {

	public Resturant createResturant(ResturantReq rest,User user);
	
	public Resturant updateResturant(Long resturantId,ResturantReq updatedRest);
	
	public void deleteResturant(Long id)throws Exception;
	
	public List<Resturant> getAllResturant();
	
	public List<Resturant> searchResturant(String keyword);
	
	public Resturant findResturantById(Long id)throws Exception;
	
	public List<ResturantResponseDTO> findResturantByUserId(Long userId)throws Exception;

	
//    public ResturantDTO addToFavouraites(Long resturantId,User user)throws Exception;
    
	 public List<Resturant> getFavouritesByUserId(Long userId) throws Exception;
	 
    public Resturant updateResturantStatus(Long id)throws Exception;

	Resturant addToFavourites(Long resturantId, User user) throws Exception;
}
