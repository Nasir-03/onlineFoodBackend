package food.example.online.food.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import food.example.online.food.config.AppConfig;
import food.example.online.food.dto.AddressResponse;
import food.example.online.food.dto.ResturantDTO;
import food.example.online.food.dto.ResturantReq;
import food.example.online.food.dto.ResturantResponseDTO;
import food.example.online.food.entity.Address;
import food.example.online.food.entity.Food;
import food.example.online.food.entity.Order;
import food.example.online.food.entity.Resturant;
import food.example.online.food.entity.User;
import food.example.online.food.repository.AdressRepository;
import food.example.online.food.repository.ResturantRepository;
import food.example.online.food.repository.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class ResturantServiceImpl implements ResturantService{

	private ResturantRepository resturantRepository;
	private AdressRepository adressRepository;
	private UserRepository userRepository;
	
	@Autowired
	public ResturantServiceImpl(ResturantRepository resturantRepository, AdressRepository adressRepository,
			UserRepository userRepository) {
		this.resturantRepository = resturantRepository;
		this.adressRepository = adressRepository;
		this.userRepository = userRepository;
	}

	@Override
	public Resturant createResturant(ResturantReq rest, User user) {
		Address address = adressRepository.save(rest.getAddress());
		
		Resturant resturant = new Resturant();
		resturant.setAddress(rest.getAddress());
		resturant.setContactInformation(rest.getContactInformation());
		resturant.setCuisineType(rest.getCuisineType());
		resturant.setDescription(rest.getDescription());
		resturant.setImages(rest.getImages());
		resturant.setName(rest.getName());
		resturant.setOpeningHours(rest.getOpeningHours());
		resturant.setRegisterationDate(LocalDateTime.now());
		resturant.setOwner(user);
		
		return resturantRepository.save(resturant);
	}

	@Override
	public Resturant updateResturant(Long resturantId, ResturantReq updatedRest) {
        Resturant resturant = resturantRepository.findById(resturantId).
        		orElseThrow(()-> new UsernameNotFoundException("user not found"));
        
        if (updatedRest.getCuisineType() != null) {
        	resturant.setCuisineType(updatedRest.getCuisineType());
        }
        
        if (updatedRest.getDescription() != null) {
        	resturant.setCuisineType(updatedRest.getDescription());
        }
        
        if (updatedRest.getName() != null) {
        	resturant.setCuisineType(updatedRest.getName());
        }
		
		return resturantRepository.save(resturant);
	}

//	@Override
//	@Transactional
//	public void deleteResturant(Long id) throws Exception {
//		Resturant resturant = resturantRepository.findById(id).
//        		orElseThrow(()-> new UsernameNotFoundException("user not found"));
//        
//		resturantRepository.delete(resturant);
//	}
	
	@Override
	@Transactional
	public void deleteResturant(Long id) throws Exception {
	    Resturant resturant = resturantRepository.findById(id)
	        .orElseThrow(() -> new Exception("Restaurant not found"));

	    // Important: detach all child references to transient entities
	    if (resturant.getFoods() != null) {
	        for (Food food : resturant.getFoods()) {
	            food.setResturant(null); // detach parent reference
	        }
	        resturant.getFoods().clear();
	    }

	    if (resturant.getOrders() != null) {
	        for (Order order : resturant.getOrders()) {
	            order.setResturant(null);
	        }
	        resturant.getOrders().clear();
	    }

	    if (resturant.getAddress() != null) {
	        resturant.setAddress(null); // detach Address if needed
	    }

	    // Now delete
	    resturantRepository.delete(resturant);
	}

	
	@Override
	public List<Resturant> getAllResturant() { 
		return resturantRepository.findAll();
	}

	@Override
	public List<Resturant> searchResturant(String keyword) {
		return resturantRepository.findBySearchQuery(keyword);
	}

	@Override
	public Resturant findResturantById(Long id) throws Exception {
		Resturant resturant = resturantRepository.findById(id).
        		orElseThrow(()-> new UsernameNotFoundException("Resturant not found not found"));
	 
		return resturant;
	}

	
	public List<ResturantResponseDTO> findResturantByUserId(Long userId) {
	    List<Resturant> restaurants = resturantRepository.findByOwnerId(userId);

	    return restaurants.stream().map(rest -> {
	        ResturantResponseDTO dto = new ResturantResponseDTO();
	        dto.setId(rest.getId());
	        dto.setName(rest.getName());
	        dto.setDescription(rest.getDescription());
	        dto.setCuisineType(rest.getCuisineType());
	        dto.setContactInformation(rest.getContactInformation());
	        dto.setOpeningHours(rest.getOpeningHours());
	        dto.setImages(rest.getImages());
	        dto.setRegisterationDate(rest.getRegisterationDate());
	        dto.setOpen(rest.isOpen());

	        // owner fields
	        if (rest.getOwner() != null) {
	            dto.setOwnerName(rest.getOwner().getFullName());
	            dto.setOwnerEmail(rest.getOwner().getEmail());
	            dto.setOwnerRole(rest.getOwner().getRole());
	        }
	        
	        // Adress fields
//	        if (rest.getAddress() != null) {
//                AddressResponse addr = new AddressResponse();
//                addr.setId(rest.getAddress().getId());
//                addr.setCity(rest.getAddress().getCity());
//                addr.setStreet(rest.getAddress().getStreet());
//                addr.setPinCode(rest.getAddress().getPinCode());
//                addr.setState(rest.getAddress().getState());
//
//                dto.setAddress(addr);
//            }
	        
	        return dto;
	    }).collect(Collectors.toList());
	}

	
	@Override
	public Resturant addToFavourites(Long resturantId, User user) throws Exception {
	    Resturant resturant = resturantRepository.findById(resturantId)
	            .orElseThrow(() -> new Exception("Restaurant not found"));

	    List<Resturant> favourites = user.getFavorites();

	    // check if already in favourites
	    if (favourites.stream().anyMatch(r -> r.getId().equals(resturantId))) {
	        // remove (unfavourite)
	        favourites.removeIf(r -> r.getId().equals(resturantId));
	    } else {
	        // add to favourites
	        favourites.add(resturant);
	    }

	    userRepository.save(user); // persist change in join table
	    return resturant; // return the managed entity
	}


	@Override
	public Resturant updateResturantStatus(Long id) throws Exception {
		Resturant resturant = resturantRepository.findById(id).
        		orElseThrow(()-> new UsernameNotFoundException("user not found"));
		
		resturant.setOpen(!resturant.isOpen());
		return resturantRepository.save(resturant);
	}

	@Override
	public List<Resturant> getFavouritesByUserId(Long userId) throws Exception {
		 return resturantRepository.findFavoritesByUserId(userId);
	}

}
