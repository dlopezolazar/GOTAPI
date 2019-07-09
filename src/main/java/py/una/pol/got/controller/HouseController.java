package py.una.pol.got.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import py.una.pol.got.entity.House;
import py.una.pol.got.service.HouseService;
import py.una.pol.got.vo.HouseVO;

import javax.validation.Valid;

@RestController
@RequestMapping("/${api.version}/house")
public class HouseController {

    @Autowired
    @Qualifier("houseService")
    private HouseService houseService;

    public HouseController(HouseService houseService){
        this.houseService = houseService;
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllHouses(){

        return new ResponseEntity<>(houseService.getAllHouse(), HttpStatus.OK);
    }

    @GetMapping(value = "/{house_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getHouseById(@PathVariable(name = "house_id")Integer houseId){

        return new ResponseEntity<>(houseService.getHouseById(houseId), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveHouse(@Valid @RequestBody HouseVO house) throws Exception {

        houseService.saveHouse(house);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateHouse(@Valid @RequestBody House house) throws Exception {

        houseService.updateHouse(house);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping(value = "/{id_house}")
    public ResponseEntity<?> deleteHouse(@PathVariable(name = "id_house")Integer idHouse){

        houseService.deleteHouse(idHouse);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
