package Application;

import java.util.List;
import java.util.Scanner;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class program2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n=== START DEPARTMENT TESTS ==");
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		
		System.out.println("=== TEST 1: department findById ===");
		Department department = departmentDao.findById(1);
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
		int id = sc.nextInt();
		departmentDao.deleteById(id);
		System.out.println("Deleted!");
		System.out.println("\n=== END DEPARTMENT TESTS ==");
		
		sc.close();
	}

}
