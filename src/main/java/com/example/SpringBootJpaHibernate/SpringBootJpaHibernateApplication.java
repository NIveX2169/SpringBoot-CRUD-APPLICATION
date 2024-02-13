package com.example.SpringBootJpaHibernate;

import com.example.SpringBootJpaHibernate.DAO.StudentDAOImp;
import com.example.SpringBootJpaHibernate.Entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.*;

@SpringBootApplication
public class SpringBootJpaHibernateApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringBootJpaHibernateApplication.class, args);

	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAOImp studentDao) {
		return runner -> {

			System.out.println("***********Student CRUD Database Application***********");

			int i = -1;
			Scanner sc = new Scanner(System.in);

			do {
				//Main Menu

				System.out.println();
				System.out.println("If You Want To Insert Student Details Press : 1");
				System.out.println("If You Want To Delete Student Details Press : 2");
				System.out.println("If You Want To Fetch  Student Details Press : 3");
				System.out.println("If You Want To Update Student Details Press : 4");
				System.out.println("If You Want To Exit Press : 0 ");
				System.out.print("->");
				i = sc.nextInt();

				if (i == 1)//Creation Driver Code
					createStudent(studentDao);
				else if (i == 2) {

					//Deletion Driver Code

					System.out.println("Delete Using Primary Key Press : P");
					System.out.println("Delete Using Where Clause : DW");

					System.out.print("->");
					String temp = getString(sc).toLowerCase();

					if(temp.equals("p")) {

						int key = -1 ;
						int trial = 0;
						while(true)
						{
							System.out.println("Enter Primary Key");
							key = sc.nextInt();

							if(studentDao.deleteUsingPrimaryKey(key) != 0)
								break;
							else
								System.out.println("Key Does Not Exist Or Wrong Input");

							if(++trial == 2) {
								System.out.println("Please Verify Input Again And Retry Using Main Menu");
								break;
							}

						}

					}
					else if(temp.equals("dw"))
					{
                        deleteUsingQuery(studentDao);
					}

				}
				else if (i == 3) {
					//Details Fetching Driver Code
					System.out.println("If You Want To See Whole Data Press : 1");
					System.out.println("If You Want To See Particular Data Using Name,Email,PrimaryKey Press : 2");
					int menu = sc.nextInt();

					if(menu == 1)
                       getAllStudentDetails(studentDao);
					if(menu == 2) {
						String fname = " ";
						String lname = " ";
						String email = " ";
						System.out.println("Search Data Using FirstName  Press 1 : ");
						System.out.println("Search Data Using LastName   Press 2 : ");
						System.out.println("Search Data Using Email      Press 3 : ");
						System.out.println("Search Data Using PrimaryKey Press 4 : ");
						System.out.print("->");
						menu = sc.nextInt();

						while (menu < 1 || menu >= 5) {
							System.out.println("Wrong Input Enter Again :");
							System.out.print("->");
							menu = sc.nextInt();
							getStudentDetailUsingColumns(studentDao, fname, lname, email);
						}

						if (menu == 1) {
							System.out.println("Enter FirstName : ");
							System.out.print("->");
							fname = sc.next();
							getStudentDetailUsingColumns(studentDao, fname, lname, email);

						} else if (menu == 2) {
							System.out.println("Enter LastName : ");
							System.out.print("->");
							lname = sc.next();
							getStudentDetailUsingColumns(studentDao, fname, lname, email);
						} else if (menu == 3) {
							System.out.println("Enter email : ");
							System.out.print("->");
							email = sc.next();
							getStudentDetailUsingColumns(studentDao, fname, lname, email);
						} else {
							int key = -1;
							System.out.println("Enter PrimaryKey : ");
							System.out.print("->");
							key = sc.nextInt();
							findById(studentDao, key);

						}
					}
				}
				else if (i == 4){

					//Updatation Driver Code
					System.out.println("Update Can Only Be Done Using Primary Key On Columns To Maintain Consistency");

					int menu = -1;
					while(menu != 0) {
						System.out.println("If You Want To Update Columns(FirstName,LastName) Press : 1");
						System.out.println("If You Want To Update Columns(FirstName,Email)    Press : 2");
						System.out.println("If You Want To Update Columns(LastName,Email)     Press : 3");
						System.out.println("If You Want To Update Column(FirstName)           Press : 4");
						System.out.println("If You Want To Update Column(LastName)            Press : 5");
						System.out.println("If You Want To Update Column(Email)               Press : 6");
						System.out.println("If You Want To Update All three Columns           Press : 7");
						System.out.println("If You Want To Exit   Column Update Menu          Press : 0");
						System.out.print("->");
						menu = sc.nextInt();

						if(menu == 0)
							break;

						System.out.println("Enter Primary Id ");
						int id = sc.nextInt();
						Student s = studentDao.findById(id);
						String fname = " ";
						String lname = " ";
						String eMail = " ";

						if(menu == 1){
							System.out.println("Enter FirstName: ");
							System.out.print("->");
							fname = sc.next();
							s.setFirstName(fname);

							System.out.println("Enter LastName: ");
							System.out.print("->");
							lname = sc.next();
							s.setLastName(lname);
						}
						else if (menu == 2) {

							System.out.println();
							System.out.println("Enter FirstName: ");
							System.out.print("->");
							fname = sc.next();
							s.setFirstName(fname);


							System.out.println("Enter Email: ");
							System.out.print("->");
							eMail = sc.next();
							s.setEmail(eMail);


						}
						else if (menu == 3) {

							System.out.println("Enter LastName: ");
							System.out.print("->");
							lname = sc.next();
							s.setLastName(lname);

							System.out.println("Enter Email: ");
							System.out.print("->");
							eMail = sc.next();
							s.setEmail(eMail);


						}
						else if (menu == 4){

							System.out.println("Enter FirstName: ");
							System.out.print("->");
							fname = sc.next();
							s.setFirstName(fname);


						}
						else if (menu == 5)
						{
							System.out.println("Enter LastName: ");
							System.out.println("->");
							lname = sc.next();
							s.setLastName(lname);


						}
						else if (menu == 6)
						{
							System.out.println("Enter Email: ");
							System.out.println("->");
							eMail = sc.next();
							s.setEmail(eMail);


						}
						else if (menu == 7)
						{
							System.out.println("Enter FirstName: ");
							System.out.print("->");
							fname = sc.next();
							s.setFirstName(fname);

							System.out.println("Enter LastName: ");
							System.out.print("->");
							lname = sc.next();
							s.setLastName(lname);

							System.out.println("Enter Email: ");
							System.out.print("->");
							eMail = sc.next();
							s.setLastName(eMail);

						}
						studentDao.update(s);
//						System.out.println(s.toString());

					}

				}

			}while (i != 0);


			System.out.println("Application ShutDown");

		};
	}





	private String getString(Scanner sc) {
		while (true) {
			String input = sc.next();

			if (isString(input)) {
				return input;
			} else {
				System.out.println("Please Enter String Value : ");
			}
		}


	}

	private boolean isString(String input) {
		for (char i : input.toCharArray()) {

			if (Character.isDigit(i))
				return false;
		}
		return true;
	}

	public void createStudent(StudentDAOImp theStudent) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter FirstName Of Student : ");
		String fName = getString(sc);
		System.out.println();
		System.out.print("Enter LastName Of Student : ");
		String lName = getString(sc);
		System.out.println();
		System.out.print("Enter Email Of Student : ");
		String email = sc.next();//Exception


		Student s = new Student(fName, lName, email);
		theStudent.save(s);
		System.out.println("VALUE ENTERED SUCCESSFULLY");
		System.out.println("Success and Id is : " + s.getId());


	}


	public void findById(StudentDAOImp theStudentDao, int id) {
		System.out.println(theStudentDao.findById(id).toString());
	}

	private void deleteUsingPrimary(StudentDAOImp studentDAOImp,int key) {
		studentDAOImp.deleteUsingPrimaryKey(key);
	}


	private void deleteUsingQuery(StudentDAOImp studentDAOImp)
	{
        Scanner sc = new Scanner(System.in);

		System.out.println("If You want to enter all 3 columns names Press : 1");
		System.out.println("If You want to enter FirstName column only  names Press : 2");
		System.out.println("If You want to enter LastName column only  names Press : 3");
		System.out.println("If You want to enter Email column only  names Press : 4");
		System.out.println("If You want to enter  FirstName and LastName columns only names Press : 5");
		System.out.println("If You want to enter  FirstName and email columns columns only names Press : 6");
		System.out.println("If You want to enter  LastName and email columns only names Press : 7");

		System.out.print("Enter your choice : ");
		int menu = sc.nextInt();

		String fName = " ";
		String lName = " ";
		String eMail = " ";

		if(menu == 1)
		{
			System.out.println("Enter First Name : ");
			System.out.print("-> ");
			fName = sc.next();
			System.out.println("Enter Last Name : ");
			System.out.print("-> ");
			lName = sc.next();
			System.out.println("Enter Email Address : ");
			System.out.print("-> ");
			eMail = sc.next();
		}
		else if(menu == 2)
		{

			System.out.println("Enter First Name : ");
			System.out.print("-> ");
			fName = sc.next();

		} else if (menu == 3) {
			System.out.println("Enter Last Name : ");
			System.out.print("-> ");
			lName = sc.next();

		}
		else if(menu == 4)
		{
			System.out.println("Enter Email Address : ");
			System.out.print("-> ");
			eMail = sc.next();
		}
		else if(menu == 5)
		{
			System.out.println("Enter First Name : ");
			System.out.print("-> ");
			fName = sc.next();
			System.out.println("Enter Last Name : ");
			System.out.print("-> ");
			lName = sc.next();
		} else if (menu == 6) {
			System.out.println("Enter First Name : ");
			System.out.print("-> ");
			fName = sc.next();
			System.out.println("Enter Email Address : ");
			System.out.print("-> ");
			eMail = sc.next();
		}
		else if (menu == 7) {
			System.out.println("Enter Last Name : ");
			System.out.print("-> ");
			lName = sc.next();
			System.out.println("Enter Email Address : ");
			System.out.print("-> ");
			eMail = sc.next();
		}



		studentDAOImp.deleteUsingQuery(fName,lName,eMail);
	}

	private void getAllStudentDetails(StudentDAOImp studentDAOImp)
	{
		List<Student> result = studentDAOImp.getDetailsOfAllStudents();

		for (Student r : result)
			System.out.println(r.toString());

	}

	private void getStudentDetailUsingColumns(StudentDAOImp studentDAOImp,String fName,String lName,String email)
	{
		List<Student>result = studentDAOImp.getDetailsUsingCol(fName,lName,email);

		if(result.isEmpty())
			System.out.println("No Details Exist !!");
		else
         for (Student student : result) {
            System.out.println(student.toString());
         }
	}


}










