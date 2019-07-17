package py.una.pol.got.controller;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import py.una.pol.got.TestConfig;
import py.una.pol.got.dao.HouseRepository;
import py.una.pol.got.entity.House;
import py.una.pol.got.exception.EmptyResultException;
import py.una.pol.got.service.HouseService;
import py.una.pol.got.service.HouseServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HouseController.class, HouseServiceImpl.class, HouseRepository.class, TestConfig.class})
public class HouseControllerTest {

    @Mock
    HouseService houseService;

    @InjectMocks
    HouseController houseController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllHouses_houseNoEmptyList_OK(){

        //Arrange
        List<House> houseList = new ArrayList<>();
        houseList.add(new House(1, "Lannister"));
        houseList.add(new House(2, "Stark"));
        ResponseEntity responseMock = new ResponseEntity<>(houseList, HttpStatus.OK);

        //Action
        when(houseService.getAllHouse()).thenReturn(houseList);
        ResponseEntity response = houseController.getAllHouses();

        //Assert
        assertThat(response.getStatusCode(), is(responseMock.getStatusCode()));
    }

    @Test(expected = EmptyResultException.class)
    public void getAllHouses_houseEmptyList_noContent(){

        //Arrange
        List<House> houseList = new ArrayList<>();
        ResponseEntity responseMock = new ResponseEntity<>(houseList, HttpStatus.NO_CONTENT);

        //Action
        when(houseService.getAllHouse()).thenThrow(EmptyResultException.class);
        ResponseEntity response = houseController.getAllHouses();

        //Assert
//        assertThat(response.getStatusCode(), is(responseMock.getStatusCode()));

    }


}