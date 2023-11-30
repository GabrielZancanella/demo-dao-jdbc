package Application;

import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class program2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int operation = 0;
		int id = 0;
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		Department department = null;
		Department newDepartment = null;
		boolean executingSystem = true;
		List<Department> allDepartments = null;
		String departmentName = "";
		String oldDepartmentName = "";

		while (executingSystem) {
			System.out.println("\n=== MENU DEPARTMENT ===");
			System.out.println("1 - Find by ID.");
			System.out.println("2 - Find All.");
			System.out.println("3 - Department Insert.");
			System.out.println("4 - Department Update.");
			System.out.println("5 - Department Delete.");
			System.out.println("6 - Exit system.");

			System.out.print("\nChose one operation: ");
			operation = sc.nextInt();

			while (operation < 1 || operation > 6) {
				System.out.print("\nChose a valid operation! ");
				System.out.print("\nChose one operation: ");
				operation = sc.nextInt();
			}

			switch (operation) {
			case 1:
				System.out.print("\nID to be searched for: ");
				id = sc.nextInt();
				department = departmentDao.findById(id);
				if (department != null) {
					System.out.println(department);
					department = null;
				} else
					System.out.println("This ID not exists!");

				System.out.println("=======================");
				break;
			case 2:
				System.out.println("\nAll Departments:");
				allDepartments = departmentDao.findAll();

				for (Department allDepartmentsAux : allDepartments) {
					System.out.println(allDepartmentsAux);
				}
				System.out.println("=======================");
				break;
			case 3:
				System.out.print("\nEnter the Department name: ");
				sc.nextLine();
				departmentName = sc.nextLine();
				newDepartment = new Department(null, departmentName);
				departmentDao.insert(newDepartment);
				System.out.println("Inserted! New ID =" + newDepartment.getId());
				System.out.println("=======================");
				break;
			case 4:
				System.out.print("\nID to be updated for: ");
				id = sc.nextInt();
				department = departmentDao.findById(id);
				if (department != null) {
					System.out.print("\nEnter the Department name to be updated: ");
					sc.nextLine();
					departmentName = sc.nextLine();
					oldDepartmentName = department.getName();
					department.setName(departmentName);
					departmentDao.update(department);
					System.out.printf("Updated! \n   Old name: %s\n   New name: %s", oldDepartmentName,
							department.getName());
				} else
					System.out.println("This id not exists!");
				System.out.println("=======================");
				break;
			case 5:
				System.out.println("\nEnter the ID to be Deleted for: ");
				id = sc.nextInt();
				departmentDao.deleteById(id);
				System.out.println("Deleted!");
				System.out.println("=======================");
				break;
			case 6:
				executingSystem = false;
				break;
			}
		}
		sc.close();
	}

}
