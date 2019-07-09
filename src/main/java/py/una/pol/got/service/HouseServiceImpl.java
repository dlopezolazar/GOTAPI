package py.una.pol.got.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import py.una.pol.got.dao.HouseRepository;
import py.una.pol.got.entity.House;
import py.una.pol.got.exception.EmptyResultException;
import py.una.pol.got.exception.NotFoundException;
import py.una.pol.got.vo.HouseVO;

import java.util.List;

@Service("houseService")
public class HouseServiceImpl implements HouseService{

    @Autowired
    private HouseRepository houseRepository;

    Logger logger = LoggerFactory.getLogger(HouseServiceImpl.class);

    public HouseServiceImpl(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }

    @Override
    public List<House> getAllHouse() {

        List<House> houseList = houseRepository.getAllHouses();
        if(houseList.isEmpty()){
            throw new EmptyResultException("No content");
        }

        return houseList;

    }

    @Override
    public House getHouseById(Integer idHouse){

        try{
            return houseRepository.getHouseById(idHouse);

        } catch (EmptyResultDataAccessException ex){
            logger.debug(ex.getMessage());
            throw new NotFoundException(ex.getMessage());
        }

    }

    @Override
    public void saveHouse(HouseVO house) throws Exception {

        House entity = new House();

        entity.setName(house.getName());

        houseRepository.saveHouse(entity);
    }

    @Override
    public void updateHouse(House house) throws Exception {

        houseRepository.updateHouse(house);

    }

    @Override
    public void deleteHouse(Integer idHouse) {
        houseRepository.deleteHouse(idHouse);
    }
}
