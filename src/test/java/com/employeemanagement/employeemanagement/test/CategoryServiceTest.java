package com.employeemanagement.employeemanagement.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.employeemanagement.employeemanagement.dto.CategoryDTO;
import com.employeemanagement.employeemanagement.entity.Category;
import com.employeemanagement.employeemanagement.entity.Employee;
import com.employeemanagement.employeemanagement.repository.CategoryRepository;
import com.employeemanagement.employeemanagement.repository.EmployeeRepository;
import com.employeemanagement.employeemanagement.service.CategoryService;

//É necessário importar esses métodos estáticos
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

//Annotation para testes jUnit
@SpringBootTest
public class CategoryServiceTest {
	
	//Cria uma instancia mockada do repositório
	@Mock
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Mock
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@InjectMocks
	@Autowired
	private CategoryService categoryService;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	//Situação em que traz uma lista
	@Test 
	void testGetAll() {
		
		//Instanciando três objetos categorias para testar getAll
		Category category1 = new Category();
		category1.setId(1L);
		category1.setDescription("Intern");
		
		Category category2 = new Category();
		category2.setId(2L);
		category2.setDescription("Trainee");
		
		Category category3 = new Category();
		category3.setId(3L);
		category3.setDescription("Jr I");
		
		//Instanciando array diretamente do construtor
		List<Category> categoryList = Arrays.asList(category1, category2, category3);
		
		//Mockando dados de teste no banco simulado pelo repository
		when(categoryRepository.findAll()).thenReturn(categoryList);
		
		//"Act"
		//Pegando resultado direto do service de Category
		List<CategoryDTO> result = categoryService.getAll();
		
		//"Assert"
		//Testando resultados do teste
		assertThat(result).hasSize(3);
		assertThat(result.get(0).getDescription()).isEqualTo("Intern");
		assertThat(result.get(1).getDescription()).isEqualTo("Trainee");
		assertThat(result.get(2).getDescription()).isEqualTo("Jr I");
		verify(categoryRepository, times(1)).findAll(); //O Número 1 simboliza SE EXECUTOU UMA VEZ A INVOCAÇÃO DO MÉTODO
	}
	
	//Situação em que traz null
	@Test
	void testGetAll_WhenServiceReturnsNull() {
		
		//"Arrange"
		when(categoryService.getAll()).thenReturn(Collections.emptyList());
		
		//"Act"
		List<CategoryDTO> result = categoryService.getAll();
		
		//"Assert"
		assertThat(result).isNull();
		verify(categoryRepository, times(1)).findAll();
	}
	
	
	//Testando getById
	@Test
	void testGetById() {
		
		Optional<Category> category1 = Optional.ofNullable(new Category());
		category1.get().setId(1L);
		category1.get().setDescription("Intern");
		
		Employee employee1 = new Employee();
		employee1.setId(1L);
		employee1.setCpf("111");
		employee1.setFirstName("Pietro Sgarbosa");
		
		List<Employee> employeeList1 = Arrays.asList(employee1);
		Long id = category1.get().getId();
		
		category1.get().setEmployees(employeeList1);
		
		when(categoryRepository.findById(id)).thenReturn(category1);
		
		//"Act"
		Optional<Category> result = Optional.ofNullable(categoryService.findById(id));
		
		//"Assert"
		assertThat(result).isNotNull();
		assertThat(result.get().getDescription()).isEqualTo("Intern");
		assertThat(result.get().getId()).isEqualTo(1L);
		assertThat(result.get().getEmployees().get(0).getFirstName()).isEqualTo("Pietro Sgarbosa");
		verify(categoryRepository, times(1)).findById(id);
	}
	
	//Testando getById para retornar valor diferente do pesquisado
	@Test
	void testGetById_WhenReturnIsDifferent() {
		
		Optional<Category> category1 = Optional.ofNullable(new Category());
		category1.get().setId(1L);
		category1.get().setDescription("Intern");

		Long id = 2L;
		
		when(categoryRepository.findById(id)).thenReturn(category1);
		
		//"Act"
		Optional<Category> result = Optional.ofNullable(categoryService.findById(id));
		
		//"Assert"
		assertThat(result.get().getId()).isNotEqualTo(id);
		verify(categoryRepository, times(1)).findById(id);
	}
	
	//Testando a invocação do método de create, essa é a forma como o Mockito usa para 
	//verificar se uma gravação foi feita com sucesso uma vez que o serviço retorna VOID 
	@Test
	void testCreate() {
		
		//O DTO e a entidade precisam ter o mesmo ID
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setId(1L);
		categoryDTO.setDescription("Intern");
		
		Category category1 = new Category();
		category1.setId(1L);
		category1.setDescription("Intern");
		
		//"Act"
		categoryService.create(categoryDTO);
	
		//A funação refEq certifica de que a entidade é equivalente a conversão do DTO original
		verify(categoryRepository, times(1)).save(refEq(category1));
	}
	
	//Testando cenário de exception ao salvar 
	@Test
	void testCreate_WhenServiceThrowsException() {
	
		//"Act & Assert"
		//Usando assertThrows para testar exceptions simples
		assertThrows(IllegalArgumentException.class, () -> {
			categoryService.create(null);
		});
	}
	
	@Test
	void testUpdate() {
		
		Long id = 1L;
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setId(id);
		categoryDTO.setDescription("Trainee");
		
		Category categoryToBeUpdated = new Category();
		categoryToBeUpdated.setId(id);
		categoryToBeUpdated.setDescription("Intern");
		
		when(categoryRepository.findById(id)).thenReturn(Optional.of(categoryToBeUpdated));
		
		//"Act"
		String result = categoryService.update(categoryDTO);

		//"Assert" assegurando que a string do resultado é a esparada e de que o valor foi
		//alterado corretamente na entidade original
		verify(categoryRepository, times(1)).findById(id);
		verify(categoryRepository, times(1)).save(categoryToBeUpdated);
		assertThat(result).isEqualTo("Category of ID " + id + " updated successfully!");
		assertThat(categoryToBeUpdated.getDescription()).isEqualTo("Trainee");
		
	}
	
	//Testando segunda opção de retorno do update
	@Test
	void testUpdate_SecondReturnCondition() {
		
		Long categoryId = 999L;
		
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setId(categoryId);
		
		Category category = new Category();
		
		when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));

		//"Act"
		String result = categoryService.update(categoryDTO);
		
		assertThat(result).isEqualTo("Category of ID " + categoryDTO.getId() + " not found");
		verify(categoryRepository, times(0)).save(any(Category.class)); //O Número 0 SIMBOLIZA SE O MÉTODO NÃO FOI CHAMADO
	}
	
	@Test
	void testDelete() {
		
		//TODOS OS DADOS MOCKADOS -----------------------/
		Long id = 1L;
		
		Category category = new Category();
		category.setId(id);
		
		List<Employee> employees = new ArrayList<Employee>();
		
		//Condição para deletar, retornar lista vazia
		when(categoryRepository.findById(id)).thenReturn(Optional.of(category));
		when(employeeRepository.findByCategory(category)).thenReturn(employees);
		
		//FIM DOS DADOS MOCKADOS ------------------------/
		
		//"Act" GATILHO PARA ATIVAR O SERVICE
		categoryService.delete(id);
		
		//"Assert" VALIDAÇÕES - COMEÇO DO TESTE
		verify(categoryRepository, times(1)).findById(id);
		verify(employeeRepository, times(1)).findByCategory(category);
		verify(categoryRepository, times(1)).delete(category);
	}

}
