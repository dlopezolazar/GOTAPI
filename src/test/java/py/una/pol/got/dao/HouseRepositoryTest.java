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
    public void getAllHouse_Ok(){

//      Given
        List<House> houseList = new ArrayList<>();
        houseList.add(new House(1, "Lannister"));

        when(jdbcTemplate.query(anyString(), Matchers.<BeanPropertyRowMapper<House>>any())).thenReturn(houseList);
        List<House> retVal = houseRepository.getAllHouses();

        assertThat(houseList, is(retVal));

    }

    @Test
    public void getHouseById_Ok(){

        int idHouse = 2;
        House expected = new House(idHouse, "Lannister");

        when(jdbcTemplate.queryForObject(anyString(), anyObject(), Matchers.<BeanPropertyRowMapper<House>>any())).
                thenReturn(expected);
        House returnVal = houseRepository.getHouseById(idHouse);

        assertThat(idHouse, is(returnVal.getIdHouse()));
    }
}