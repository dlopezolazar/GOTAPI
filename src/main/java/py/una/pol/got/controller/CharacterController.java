package py.una.pol.got.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import py.una.pol.got.service.CharacterService;
import py.una.pol.got.vo.CharacterVO;

@RestController
@RequestMapping("/${api.version}/character")
public class CharacterController {

    @Autowired
    @Qualifier("characterService")
    private CharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllCharacters(){

        return new ResponseEntity<>(characterService.getAllCharacters(), HttpStatus.OK);
    }

    @GetMapping(value = "/{character_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCharacterById(@PathVariable(name = "character_id")Integer characterId){

        return new ResponseEntity<>(characterService.getCharacterById(characterId), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveHouse(@RequestBody CharacterVO character) throws Exception {

        characterService.saveCharacter(character);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
