package py.una.pol.got.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import py.una.pol.got.entity.House;
import py.una.pol.got.vo.HouseVO;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Types;
import java.util.List;

@Repository
@Transactional
public class HouseRepository extends JdbcDaoSupport {

    @Autowired
    private DataSource dataSource;

    Logger logger = LoggerFactory.getLogger(HouseRepository.class);

    private static final String GET_ALL_HOUSES = "SELECT * FROM house";
    private static final String GET_HOUSE_BY_ID = "SELECT h.id_house, h.name FROM house h WHERE h.id_house = ?";
    private static final String SAVE_HOUSE = "INSERT INTO house(name) VALUES (?)";
    private static final String GET_HOUSE_BY_NAME = "SELECT h.id_house, h.name FROM house h WHERE h.name = ?";
    private static final String UPDATE_HOUSE = "UPDATE house SET name=? WHERE id_house = ?";
    private static final String DELETE_HOUSE = "DELETE FROM house WHERE id_house = ?";

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    public List<House> getAllHouses() {

        return this.getJdbcTemplate().query(GET_ALL_HOUSES, (rs, rowNum) -> {
            House house = new House();
            house.setIdHouse(rs.getInt("id_house"));
            house.setName(rs.getString("name"));
            return house;
        });
    }

    public House getHouseById(Integer idHouse) throws EmptyResultDataAccessException{

        return this.getJdbcTemplate().queryForObject(GET_HOUSE_BY_ID, new Object[]{idHouse},
                new BeanPropertyRowMapper<>(House.class));

    }

    public void saveHouse(House house) throws Exception {

        try{

            this.getJdbcTemplate().update(SAVE_HOUSE,  new Object[]{house.getName()}, new int[]{Types.VARCHAR});

        } catch (DataAccessException ex){
            logger.debug("Error al intentar guardar House. ", ex.getMessage());
            throw new Exception(ex);
        }
    }

    public HouseVO updateHouse(House house) throws Exception{

        this.getJdbcTemplate().update(UPDATE_HOUSE, new Object[]{house.getName(), house.getIdHouse()},
                new int[]{Types.VARCHAR, Types.INTEGER});

        House h = this.getHouseById(house.getIdHouse());

        return new HouseVO(h.getName());
    }

    public void deleteHouse(Integer idHouse){

        this.getJdbcTemplate().update(DELETE_HOUSE, new Object[]{idHouse});

    }

    public House getHouseByName(String name){

        return this.getJdbcTemplate().query(GET_HOUSE_BY_NAME, new Object[]{name},
                new BeanPropertyRowMapper<>(House.class)).get(0);

    }
}
