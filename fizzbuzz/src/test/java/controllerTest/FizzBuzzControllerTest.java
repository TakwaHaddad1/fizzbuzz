package controllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import controller.FizzBuzzController;

public class FizzBuzzControllerTest {

    private MockMvc mockMvc;

    private FizzBuzzController fizzBuzzController;

    @BeforeEach
    public void setUp() {
        fizzBuzzController = new FizzBuzzController();
        mockMvc = MockMvcBuilders.standaloneSetup(fizzBuzzController).build();
    }
    @Test
    public void testGetResultFizz() {
        String result = fizzBuzzController.getResult(3);
        assertEquals("Fizz", result);
    }

    @Test
    public void testGetResultBuzz() {
        String result = fizzBuzzController.getResult(5);
        assertEquals("Buzz", result);
    }
    @Test
    public void testGetFizzBuzzList() throws Exception {
        mockMvc.perform(get("/3"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$.length()").value(3))
            .andExpect(jsonPath("$[0]").value("1"))
            .andExpect(jsonPath("$[1]").value("2"))
            .andExpect(jsonPath("$[2]").value("Fizz"));
    }
}
