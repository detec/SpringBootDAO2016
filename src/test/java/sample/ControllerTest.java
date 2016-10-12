package sample;

import static org.mockito.Mockito.when;
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
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

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
// @RunWith(MockitoJUnitRunner.class)
@RunWith(SpringRunner.class)
// @ComponentScan(basePackages = { "sample.util", "sample.dao",
// "sample.controller", "sample.dao" })
// @ContextConfiguration(classes = { AppConfiguration.class,
// DataBaseConfig.class, WebAppConfig.class })
public class ControllerTest {

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

	private ObjectMapper objectMapper = new CustomObjectMapper();

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(unit).build();
	}

	private SalesOrder generateSampleSalesOrder() {
		SalesOrder salesOrder = new SalesOrder();
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

		// verify(genericService).save(salesOrder);
	}

	@Test
	public void testGetAllOrders() throws Exception {
		SalesOrder salesOrder1 = generateSampleSalesOrder();
		SalesOrder salesOrder2 = generateSecondSampleSalesOrder();

		List<SalesOrder> salesOrders = Arrays.asList(salesOrder1, salesOrder2);
		when(genericService.findAll(SalesOrder.class)).thenReturn(salesOrders);

		String expectedSalesOrdersJson = objectMapper.writeValueAsString(salesOrders);
		// mockMvc.perform(get(Constants.ORDER_ENDPOINT)).andExpect(status().isOk())
		// .andExpect(content().string(expectedSalesOrdersJson));

		// verify(genericService).findAll(SalesOrder.class);
	}

}
