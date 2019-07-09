package py.una.pol.got.service;

import py.una.pol.got.entity.Character;
import py.una.pol.got.vo.CharacterVO;

import java.util.List;

public interface CharacterService {

    /**
     *
     * @return A list from all Character
     */
    List<Character> getAllCharacters();

    /**
     *
     * @param idCharacter id for character
     * @return A house from id
     */
    Character getCharacterById(Integer idCharacter);

    /**
     * Save a Character into a data base
     * @param character object to persist
     * @throws Exception
     */
    void saveCharacter(CharacterVO character) throws Exception;
}
