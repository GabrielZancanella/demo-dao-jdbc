package Application;

import java.util.Date;

import model.entities.Department;
import model.entities.Seller;

public class program {

	public static void main(String[] args) {

		Department obj = new Department(1, "Books");
		Seller obj2 = new Seller(1, "Frank Castle","frank.castle@gmail.com", new Date(),2050.55, obj);
		
		System.out.println(obj2);	
	}
}
