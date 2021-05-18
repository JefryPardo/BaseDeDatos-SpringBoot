package com.basedatos;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import com.basedatos.model.Categoria;
import com.basedatos.repository.CategoriasRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;



@SpringBootApplication
public class BasedatosApplication implements CommandLineRunner{

	@Autowired
	private CategoriasRepository repo;

	public static void main(String[] args) {
		SpringApplication.run(BasedatosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		buscarTodoPaginacionOrdenado();
	}

	private void guardar(){
		
		Categoria cat = new Categoria();
		cat.setNombre("Finanzas");
		cat.setDescripcion("Trabajos relacionados con finanzas y contavilidad.");

		repo.save(cat);
		System.out.println(cat);
	}

	private void eliminar(){
		int idCategoria = 2;
		repo.deleteById(idCategoria);
	}


	//CRUD
	private void buscarPorId(){
		Optional<Categoria> optional = repo.findById(1);
		if(optional.isPresent()){
			System.out.println(optional.get());
		}else{
			System.out.println("No se encontro");
		}
	}

	

	private void update(){
		Optional<Categoria> optional = repo.findById(1);
		if(optional.isPresent()){
			Categoria cateTmp = optional.get();
			cateTmp.setNombre("Ing. Software");
			cateTmp.setDescripcion("Desarrollo de sistemas");
			repo.save(cateTmp);
		}else{
			System.out.println("No se encontro");
		}
	}


	private void conteo(){
		long conteo = repo.count();
		System.out.println(conteo);
	}
	//CRUD
	private void eliminarTodo(){
		repo.deleteAll();
	}

	//JPA
	private void eliminarTodoJPA(){
		repo.deleteAllInBatch();
	}

	private void encontrarIds(){
		
		List<Integer> ids = new LinkedList<Integer>();
		
		ids.add(1);
		ids.add(6);
		ids.add(8);

		Iterable<Categoria> cat = repo.findAllById(ids);
		for (Categoria c : cat) {
			System.out.println(c);
		}

	}
	//Usando la interfaz CRUD
	private void buscarTodos(){
		
		Iterable<Categoria> c = repo.findAll();
		for (Categoria categoria : c) {
			System.out.println(categoria);
		}
	}

	//Uso de la interfaz JPA
	private void buscarTodosJPA(){
		List<Categoria> cat = repo.findAll();
		for (Categoria categoria : cat) {
			System.out.println(categoria.getId() + " " + categoria.getNombre());
		}
	}

	//PagingAndSortingRepository  Si quiere que retorne de forma ASC quitar el .descending()  
	private void buscarTodosPASR(){
		List<Categoria> cat = repo.findAll(Sort.by("nombre").descending());
		for (Categoria categoria : cat) {
			System.out.println(categoria.getId() + " " + categoria.getNombre());
		}
	}

	//PagingAndSortingRepository paginacion
	private void buscarTodoPaginacion(){
		Page<Categoria> pagina = repo.findAll(PageRequest.of(1, 5));
		System.out.println("Total registros: " + pagina.getTotalElements());
		for (Categoria categoria : pagina.getContent()) {
			System.out.println(categoria.getId() + " " + categoria.getNombre());
		}
	}

	//PagingAndSortingRepository paginacion y ordenado
	private void buscarTodoPaginacionOrdenado(){
		Page<Categoria> pagina = repo.findAll(PageRequest.of(1, 5,Sort.by("nombre")));
		System.out.println("Total registros: " + pagina.getTotalElements());
		for (Categoria categoria : pagina.getContent()) {
			System.out.println(categoria.getId() + " " + categoria.getNombre());
		}
	}

	

	private void existeId(){
		boolean existe = repo.existsById(1);
		System.out.println(existe);
	}


	private void guardarTodo(){
		List<Categoria> c = new LinkedList<Categoria>();	
		
		Categoria obj1 = new Categoria();
		obj1.setNombre("Soldador");
		obj1.setDescripcion("Soldadura y pintura");

		Categoria obj2 = new Categoria();
		obj2.setNombre("Blockchain");
		obj2.setDescripcion("Trabajos relacionados con Bitcoin");

		c.add(obj1);
		c.add(obj2);

		repo.saveAll(c);
	}

}
