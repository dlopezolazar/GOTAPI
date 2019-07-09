package py.una.pol.got.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import py.una.pol.got.TestConfig;
import py.una.pol.got.dao.CharacterRepository;
import py.una.pol.got.dao.HouseRepository;
import py.una.pol.got.entity.Character;
import py.una.pol.got.entity.House;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class, CharacterServiceImpl.class,
        CharacterRepository.class, HouseRepository.class})
public class CharacterServiceImplTest {

    @Mock
    CharacterRepository characterRepository;

    @InjectMocks
    CharacterServiceImpl characterService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllCharacters() {

        List<Character> characterList = new ArrayList<>();
        characterList.add(new Character(1, "Cersei", new House(1, "Lannister")));

        when(characterRepository.getAllCharacters()).thenReturn(characterList);
        List<Character> retVal = characterService.getAllCharacters();

        assertThat(characterList, is(retVal));
    }
}