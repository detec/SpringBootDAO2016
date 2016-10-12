package sample.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sample.domain.BaseEntity;
import sample.domain.SalesOrder;
import sample.exceptions.BusinessLogicException;
import sample.service.GenericService;

/**
 * Test controller for test SalesOrder
 *
 * @author Andrii Duplyk
 *
 */
@RestController
@RequestMapping("/testorders")
public class TestOrderController {

	@Resource
	private GenericService genericService;

	@GetMapping
	public <T extends BaseEntity> ResponseEntity<List<T>> listAllOrders() {

		@SuppressWarnings("unchecked")
		List<T> entitiesList = (List<T>) genericService.findAll(SalesOrder.class);
		return new ResponseEntity<>(entitiesList, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<SalesOrder> createOrder(@RequestBody SalesOrder entity) throws BusinessLogicException {
		SalesOrder returnEntity = genericService.save(entity);
		return new ResponseEntity<>(returnEntity, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<SalesOrder> updateOrder(@RequestBody SalesOrder entity) throws BusinessLogicException {
		SalesOrder returnEntity = genericService.update(entity);
		return new ResponseEntity<>(returnEntity, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<SalesOrder> getOrder(@PathVariable("id") Long id) {
		SalesOrder returnEntity = genericService.findById(SalesOrder.class, id);
		if (returnEntity == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(returnEntity, HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<SalesOrder> deleteOrder(@PathVariable("id") Long id) throws BusinessLogicException {
		SalesOrder entity = genericService.findById(SalesOrder.class, id);
		genericService.delete(entity);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

}
