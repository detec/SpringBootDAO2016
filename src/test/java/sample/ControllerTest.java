package sample;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import sample.config.AppConfiguration;
import sample.config.DataBaseConfig;
import sample.config.WebAppConfig;
import sample.controller.TestOrderController;
import sample.domain.SalesOrder;
import sample.domain.SalesOrderOrderLines;
import sample.service.GenericService;
import sample.util.Constants;
import sample.util.CustomObjectMapper;

/**
 * @author Andrii Duplyk
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { AppConfiguration.class, DataBaseConfig.class, WebAppConfig.class })
@AutoConfigureMockMvc
public class ControllerTest {

	/**
	 * Note that the converter needs to be autowired into the test in order for
	 * MockMvc to recognize it in the setup() method.
	 */
	@Autowired
	private MappingJackson2HttpMessageConverter jackson2HttpMessageConverter;

	@Autowired
	private WebApplicationContext context;

	/**
	 * REST controller
	 */
	@InjectMocks
	private TestOrderController unit;

	/**
	 * I/O service mock
	 */
	@Mock
	private GenericService genericService;

	private MockMvc mockMvc;

	private ObjectMapper objectMapper;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

		this.mockMvc = MockMvcBuilders.standaloneSetup(unit)

				.setMessageConverters(this.jackson2HttpMessageConverter).build();

		this.objectMapper = context.getBean(CustomObjectMapper.class);

	}

	private SalesOrder generateSampleSalesOrder() {
		SalesOrder salesOrder = new SalesOrder();
		salesOrder.setId(1L);
		salesOrder.setDate(LocalDateTime.now());
		salesOrder.setTotalPrice(new BigDecimal("15.2"));

		SalesOrderOrderLines line = new SalesOrderOrderLines();
		line.setLineNumber(1);
		line.setQuantity(3);
		salesOrder.getOrderLines().add(line);

		return salesOrder;
	}

	private SalesOrder generateSecondSampleSalesOrder() {
		SalesOrder salesOrder = generateSampleSalesOrder();
		salesOrder.setTotalPrice(new BigDecimal("9.3"));
		salesOrder.getOrderLines().get(0).setQuantity(5);

		return salesOrder;
	}

	@Test
	public void testOrderSave() throws Exception {
		SalesOrder salesOrder = generateSampleSalesOrder();

		String postData = objectMapper.writeValueAsString(salesOrder);

		mockMvc.perform(post(Constants.ORDER_ENDPOINT).content(postData).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andExpect(content().string(""));

		verify(genericService).save(salesOrder);
	}

	@Test
	public void testGetAllOrders() throws Exception {
		SalesOrder salesOrder1 = generateSampleSalesOrder();
		SalesOrder salesOrder2 = generateSecondSampleSalesOrder();

		List<SalesOrder> salesOrders = Arrays.asList(salesOrder1, salesOrder2);
		when(genericService.findAll(SalesOrder.class)).thenReturn(salesOrders);

		String expectedSalesOrdersJson = objectMapper.writeValueAsString(salesOrders);
		mockMvc.perform(get(Constants.ORDER_ENDPOINT)).andExpect(status().isOk())
				.andExpect(content().string(expectedSalesOrdersJson));

		verify(genericService).findAll(SalesOrder.class);
	}

}
