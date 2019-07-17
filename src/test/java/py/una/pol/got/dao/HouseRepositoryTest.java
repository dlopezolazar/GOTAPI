package py.una.pol.got.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import py.una.pol.got.TestConfig;
import py.una.pol.got.entity.House;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {TestConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class HouseRepositoryTest {

    @Mock
    JdbcTemplate jdbcTemplate;

    @InjectMocks
    HouseRepository houseRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllHouse_getAll_Ok(){

//      Arrange
        List<House> houseList = new ArrayList<>();
        houseList.add(new House(1, "Lannister"));

//      Act
        when(jdbcTemplate.query(anyString(), Matchers.<BeanPropertyRowMapper<House>>any())).
                thenReturn(houseList);
        List<House> retornoEsperado = houseRepository.getAllHouses();

//      Assert
        assertThat(houseList, is(retornoEsperado));

    }

    @Test
    public void getHouseById_ExistentId_ReturnAHouse(){

//      Arrange
        int idHouse = 2;
        HouseStub stub = new HouseStub();

//      Act
        when(jdbcTemplate.queryForObject(anyString(), anyObject(), Matchers.<BeanPropertyRowMapper<House>>any())).
                thenReturn(stub.getHouse(idHouse));
        House returnVal = houseRepository.getHouseById(idHouse);

//      Assert
        assertThat(idHouse, is(returnVal.getIdHouse()));
    }

    class HouseStub{

        public House getHouse(Integer idHouse){
            return new House(idHouse, "Lannister");
        }

        List<House> getHouseList(){
            List<House> houseList = new ArrayList<>();
            houseList.add(new House(1, "Lannister"));

            return houseList;
        }
    }
}