package py.una.pol.got.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import py.una.pol.got.entity.Character;
import py.una.pol.got.entity.House;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Types;
import java.util.List;

@Repository
@Transactional
public class CharacterRepository extends JdbcDaoSupport {

    @Autowired
    private DataSource dataSource;

    Logger logger = LoggerFactory.getLogger(HouseRepository.class);

    private static final String GET_ALL_CHARACTERS = "SELECT c.character_id, c.name, c.house, h.name as house_name " +
                                                        "FROM character c JOIN house h ON h.id_house = c.house";
    private static final String GET_CHARACTER_BY_ID = GET_ALL_CHARACTERS.concat(" WHERE c.character_id = ?");
    private static final String SAVE_CHARACTER = "INSERT INTO character (name, house) VALUES (?, ?)";

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    public List<Character> getAllCharacters() {

        return this.getJdbcTemplate().query(GET_ALL_CHARACTERS, (rs, rowNum) -> {
            Character character = new Character();
            character.setIdCharacter(rs.getInt("character_id"));
            character.setName(rs.getString("name"));
            character.setHouse(new House(rs.getInt("house"), rs.getString("house_name")));
            return character;
        });
    }

    public Character getCharacterById(Integer idCharacter) {

        return this.getJdbcTemplate().queryForObject(GET_CHARACTER_BY_ID, new Object[]{idCharacter},
                new BeanPropertyRowMapper<>(Character.class));

    }

    public void saveCharacter(Character character) throws Exception {
        try{

            this.getJdbcTemplate().update(SAVE_CHARACTER,  new Object[]{character.getName(), character.getHouse().getIdHouse()},
                    new int[]{Types.VARCHAR, Types.INTEGER});

        } catch (DataAccessException ex){
            logger.debug("Error al intentar guardar Character. ", ex.getMessage());
            throw new Exception(ex);
        }
    }
}
