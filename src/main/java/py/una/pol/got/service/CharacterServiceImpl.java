package py.una.pol.got.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import py.una.pol.got.dao.CharacterRepository;
import py.una.pol.got.dao.HouseRepository;
import py.una.pol.got.entity.Character;
import py.una.pol.got.entity.House;
import py.una.pol.got.exception.EmptyResultException;
import py.una.pol.got.exception.NotFoundException;
import py.una.pol.got.vo.CharacterVO;

import java.util.List;

@Service("characterService")
public class CharacterServiceImpl implements CharacterService {

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private HouseRepository houseRepository;

    Logger logger = LoggerFactory.getLogger(CharacterServiceImpl.class);

    public CharacterServiceImpl(CharacterRepository characterRepository, HouseRepository houseRepository) {

        this.characterRepository = characterRepository;
        this.houseRepository = houseRepository;
    }

    @Override
    public List<Character> getAllCharacters() {
        List<Character> characterList = characterRepository.getAllCharacters();

        if(characterList.isEmpty()){
            throw new EmptyResultException("No data.");
        }

        return characterList;
    }

    @Override
    public Character getCharacterById(Integer idCharacter) {
        try{
            return characterRepository.getCharacterById(idCharacter);
        } catch (EmptyResultDataAccessException ex){
            logger.debug(ex.getMessage());
            throw new NotFoundException(idCharacter.toString());
        }
    }

    @Override
    public void saveCharacter(CharacterVO character) throws Exception {

        House house = houseRepository.getHouseByName(character.getHouseName());
        Character entity = new Character();
        entity.setName(character.getCharacterName());
        entity.setHouse(house);

        characterRepository.saveCharacter(entity);
    }
}
