package cz.muni.fi.pa165.airport_manager.facade;

import cz.muni.fi.pa165.airport_manager.config.DataConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * Dry run integration test.
 *
 * @author Tomas Valka
 * @author 422718@mail.muni.cz
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataConfiguration.class)
public class SampleDataFacadeTest {

    @Autowired
    private SampleDataFacade sampleDataFacade;

    @Test
    public void loadData() throws Exception {
        //running of method loadData() done at DataConfiguration's @PostConstruct
    }
}