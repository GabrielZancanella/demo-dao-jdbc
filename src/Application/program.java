package Application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import db.DbIntegrityException;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int operation = 0;
		int operationUpdate = 0;
		int id = 0;
		SellerDao sellerDao = DaoFactory.createSellerDao();
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		Seller seller = null;
		Seller newseller = null;
		Department department = null;
		boolean executingSystem = true;
		List<Seller> allSellers = null;

		String sellerName = "";
		String sellerEmail = "";
		Date sellerBirthDate = new Date();
		String dateString = "";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Double sellerBaseSalary = 0.0;
		Integer sellerDepartmentId = -1;

		while (executingSystem) {
			System.out.println("\n=== MENU SELLER ===");
			System.out.println("1 - Find by ID.");
			System.out.println("2 - Find by Deparment.");
			System.out.println("3 - Find All.");
			System.out.println("4 - Seller Insert.");
			System.out.println("5 - Seller Update.");
			System.out.println("6 - Seller Delete.");
			System.out.println("7 - Exit system.");

			System.out.print("\nChose one operation: ");
			operation = sc.nextInt();

			while (operation < 1 || operation > 7) {
				System.out.print("\nChose a valid operation! ");
				System.out.print("\nChose one operation: ");
				operation = sc.nextInt();
			}

			switch (operation) {
			case 1:
				System.out.print("\nID to be searched for: ");
				id = sc.nextInt();
				seller = sellerDao.findById(id);
				if (seller != null) {
					System.out.println(seller);
					seller = null;
				} else
					System.out.println("This ID not exists!");
				System.out.println("=======================");
				break;
			case 2:
				System.out.print("\nDeparment ID to be searched for: ");
				id = sc.nextInt();
				department = new Department(id, null);
				List<Seller> list = sellerDao.findByDepartment(department);

				System.out.printf("\nSellers by department %d: \n", id);
				for (Seller seller2 : list) {
					System.out.println(seller2);
				}
				System.out.println("=======================");
				break;
			case 3:
				System.out.println("\nAll Sellers:");
				allSellers = sellerDao.findAll();

				for (Seller allSellersAux : allSellers) {
					System.out.println(allSellersAux);
				}
				System.out.println("=======================");
				break;
			case 4:
				System.out.print("\nEnter the Seller name: ");
				sc.nextLine();
				sellerName = sc.nextLine();
				System.out.print("\nEnter the Seller email: ");
				sc.nextLine();
				System.out.print("\nEnter the Seller birth date (yyyy-MM-dd): ");
				dateString = sc.nextLine();

				try {
					sellerBirthDate = dateFormat.parse(dateString);
				} catch (ParseException e) {
					System.out.println("Formato de data inválido. Por favor, use aaaa-MM-dd.");
					throw new DbIntegrityException(e.getMessage());
				}

				System.out.print("\nEnter the Seller base salary: ");
				sellerBaseSalary = sc.nextDouble();
				System.out.print("\nEnter the Seller department ID: ");
				sellerDepartmentId = sc.nextInt();
				department = departmentDao.findById(sellerDepartmentId);
				if (department != null) {
					newseller = new Seller(null, sellerName, sellerEmail, sellerBirthDate, sellerBaseSalary,
							department);
					sellerDao.insert(newseller);
					System.out.println("Inserted! New ID =" + newseller.getId());
				} else
					System.out.println("Department ID does not exist!");

				System.out.println("=======================");
				break;
			case 5:
				System.out.println("\n   === MENU UPDATE SELLER ===");
				System.out.println("   1 - Update name.");
				System.out.println("   2 - Update email.");
				System.out.println("   3 - Update birth date.");
				System.out.println("   4 - Update base salary.");
				System.out.println("   5 - Update department ID.");
				System.out.print("\nChose one Update operation: ");
				operationUpdate = sc.nextInt();

				System.out.print("\nEnter the Seller ID to be updated: ");
				id = sc.nextInt();
				seller = sellerDao.findById(id);

				if (seller != null) {
					switch (operationUpdate) {
					case 1:
						System.out.print("\nEnter the new Seller name: ");
						sc.nextLine();
						sellerName = sc.nextLine();
						seller.setName(sellerName);
						break;
					case 2:
						System.out.print("\nEnter the new Seller email: ");
						sc.nextLine();
						sellerEmail = sc.nextLine();
						seller.setName(sellerEmail);
						break;
					case 3:
						System.out.print("\nEnter the new Seller birth date(yyyy-MM-dd): ");
						sc.nextLine();
						dateString = sc.nextLine();

						try {
							sellerBirthDate = dateFormat.parse(dateString);
						} catch (ParseException e) {
							System.out.println("Formato de data inválido. Por favor, use aaaa-MM-dd.");
							throw new DbIntegrityException(e.getMessage());
						}
						seller.setBirthDate(sellerBirthDate);
						break;
					case 4:
						System.out.print("\nEnter the new Seller base salary: ");
						sellerBaseSalary = sc.nextDouble();
						seller.setBaseSalary(sellerBaseSalary);
						break;
					case 5:
						System.out.print("\nEnter the new Seller department ID: ");
						sellerDepartmentId = sc.nextInt();
						department = departmentDao.findById(sellerDepartmentId);
						if (department != null) {
							seller.setDepartment(department);
						} else
							System.out.println("Department ID does not exist!");
						break;
					}

					sellerDao.update(seller);
					System.out.println("Updated! " + seller);
				} else
					System.out.println("This id not exists!");
				System.out.println("=======================");
				break;
			case 6:
				System.out.print("\nEnter the ID to be Deleted for: ");
				id = sc.nextInt();
				sellerDao.deleteById(id);
				System.out.println("Deleted!");
				System.out.println("=======================");
				break;
			case 7:
				executingSystem = false;
				break;
			}
		}
		sc.close();
	}
}
