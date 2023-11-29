package Application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n=== START SELLER TESTS ==");
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		System.out.println("=== TEST 1: seller findById ===");
		Seller seller = sellerDao.findById(7);
		System.out.println(seller);	

		System.out.println("\n=== TEST 2: seller findByDepartment ===");
		Department department = new Department(2, null);
		List<Seller> list = sellerDao.findByDepartment(department);
		
		for (Seller seller2 : list) {
			System.out.println(seller2);	
		}
		
		System.out.println("\n=== TEST 3: seller findAll ===");
		list = sellerDao.findAll();
		
		for (Seller seller2 : list) {
			System.out.println(seller2);	
		}
		
		System.out.println("\n=== TEST 4: seller insert ===");
		Seller newSeller = new Seller(null, "Walace Ronald", "walaceronald@gmail.com", new Date(), 4000.0, department);
		sellerDao.insert(newSeller);
		System.out.println("Inserted! New id ="+ newSeller.getId());
		
		System.out.println("\n=== TEST 5: seller update ===");
		seller = sellerDao.findById(9);
		seller.setEmail("walaceronald@teste.com.br");
		sellerDao.update(seller);
		System.out.println("Updated! "+ seller);
		
		System.out.println("\n=== TEST 6: seller delete ===");
		int id = sc.nextInt();
		sellerDao.deleteById(id);
		System.out.println("Deleted!");
		System.out.println("\n=== END SELLER TESTS ==");
	
	
	
		System.out.println("\n=== START DEPARTMENT TESTS ==");
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		
		System.out.println("=== TEST 1: department findById ===");
		department = departmentDao.findById(1);
		System.out.println(department);	
		
		
		System.out.println("\n=== TEST 2: department findAll ===");
		List<Department> list2 = departmentDao.findAll();
		
		for (Department department2 : list2) {
			System.out.println(department2);	
		}
		
		System.out.println("\n=== TEST 3: department insert ===");
		Department newDepartment = new Department(null, "Inserted");
		departmentDao.insert(newDepartment);
		System.out.println("Inserted! New id ="+ newDepartment.getId());
		
		
		System.out.println("\n=== TEST 4: department update ===");
		department = departmentDao.findById(9);
		department.setName("Sports");
		departmentDao.update(department);
		System.out.println("Updated! "+ department);
		
		
		System.out.println("\n=== TEST 6: department delete ===");
		id = sc.nextInt();
		departmentDao.deleteById(id);
		System.out.println("Deleted!");
		System.out.println("\n=== END DEPARTMENT TESTS ==");
		
		sc.close();
	}
}
