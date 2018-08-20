package uk.co.ebi.person.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import uk.co.ebi.person.controller.PersonController;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@WebMvcTest(PersonController.class)
public class PersonControllerTest {
	
	@Autowired
    PersonController controller;
	
    @Test
    public void controllerInitializedCorrectly() {
        assertThat(controller).isNotNull();

    }

}
