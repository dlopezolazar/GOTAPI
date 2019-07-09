package py.una.pol.got.service;

import py.una.pol.got.entity.House;
import py.una.pol.got.vo.HouseVO;

import java.util.List;

public interface HouseService {
    /**
     *
     * @return A list from all House
     */
    List<House> getAllHouse();

    /**
     *
     * @param idHouse id for house
     * @return A house from id
     */
    House getHouseById(Integer idHouse);

    /**
     * Save a house into a data base
     * @param house object to persist
     * @throws Exception
     */
    void saveHouse(HouseVO house) throws Exception;

    /**
     * Update a house
     * @param house
     */
    void updateHouse(House house) throws Exception;

    /**
     *
     * @param idHouse id for house
     */
    void deleteHouse(Integer idHouse);
}
