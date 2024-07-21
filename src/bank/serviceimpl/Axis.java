package bank.serviceimpl;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import bank.conf.HibernateCon;
import bank.model.Account;
import bank.service.RBI;

public class Axis implements RBI {
	Scanner sc = new Scanner(System.in);
	Account ac = new Account();

	public void createAccount() {

		System.out.println("Welcome to Account opening Section");
		ac = new Account();

		System.out.println("Enter Account number to register");
		long Acno = sc.nextLong();
		ac.setAcno(Acno);

		System.out.println("Enter Account holder name ");
		ac.setAcname(sc.next() + sc.nextLine());

		System.out.println("Enter Account holder Address : ");
		ac.setAcAddress(sc.next());

		System.out.println("Enter your aadhar No : ");
		ac.setAadharNo(sc.nextLong());

		System.out.println("Enter Your PANNo : ");
		ac.setPanNo(sc.next());

		System.out.println("Enter your Mobile Number : ");
		ac.setMobileNo(sc.nextLong());

		System.out.println("Enter your Gender : ");
		ac.setGender(sc.next());

		System.out.println("Enter your balance");
		ac.setBalance(sc.nextDouble());

		SessionFactory sf = HibernateCon.getSessionFactory();
		Session session = sf.openSession();

		session.save(ac);

		session.beginTransaction().commit();

		System.out.println("Account created Successfully...!");

	}

	public void viewAccountDetails() {
		SessionFactory sf = HibernateCon.getSessionFactory();
		Session session = sf.openSession();

		System.out.println("Enter your Account Number To See the details");
		long acno = sc.nextLong();

		Account ac = session.get(Account.class, acno);

		if (ac != null) {
			System.out.println();
			System.out.println("Account number : " + acno);
			System.out.println("Account name : " + ac.getAcname());
			System.out.println("Account Address : " + ac.getAcAddress());
			System.out.println("Account AadhaNo : " + ac.getAadharNo());
			System.out.println("Account PANNO : " + ac.getPanNo());
			System.out.println("Account MobileNo : " + ac.getMobileNo());
			System.out.println("Account holder Gender : " + ac.getGender());
			System.out.println("Account balance : " + ac.getBalance());
			System.out.println();
		} else {
			System.out.println();
			System.out.println("Account not found with this Acno : " + acno);
		}

	}

	public void depositeMoney() {
		SessionFactory sf = HibernateCon.getSessionFactory();
		Session session = sf.openSession();

		System.out.println("Enter your Account number to deposite money : ");
		long acno = sc.nextLong();

		Account ac = session.get(Account.class, acno);

		if (ac != null) {

			double Avbalance = ac.getBalance();

			System.out.println("enter Amount To deposite");
			double damo = sc.nextDouble();
			if (damo > 100 && damo < 50000) {

				ac.setBalance(Avbalance + damo);

				System.out.println("Money Deposited Done...!");
				session.beginTransaction().commit();
			} else {
				System.out.println("please enter amount greater than 100 or less than 50000");
			}
		} else {
			System.out.println();
			System.out.println("Account not found with this Acno : " + acno);
		}
	}

	public void withdrawMoney() {
		SessionFactory fa = HibernateCon.getSessionFactory();
		Session session = fa.openSession();

		System.out.println("Enter your Account number to withdraw money : ");
		long acno = sc.nextLong();
		Account ac = session.get(Account.class, acno);

		if (ac != null) {
			double Avbalance = ac.getBalance();

			System.out.println("enter Amount To withdraw");
			double wamo = sc.nextDouble();
			if (wamo > 100 && wamo < 50000) {

				ac.setBalance(Avbalance - wamo);

				System.out.println("Money withdrawed Done...!");
				session.beginTransaction().commit();
			}
			else {
				System.out.println("please enter amount greater than 100 or less than 50000");
			}
		} else {
			System.out.println();
			System.out.println("Account not found with this Acno : " + acno);
		}

	}

	public void Showbalance() {
		SessionFactory f = HibernateCon.getSessionFactory();
		Session os = f.openSession();

		System.out.println("Enter Account number to see the details: ");
		long l = sc.nextLong();
		Account ac = os.get(Account.class, l);
		if (ac != null) {
			System.out.println("Account holder name is:");
			String acname = ac.getAcname();
			System.out.println(acname);
			double bal = ac.getBalance();
			System.out.println("Your account balance is :" + bal);

		} else {
			System.out.println("");
			System.out.println("Account not found with this Account number:" + l);
		}
		os.beginTransaction().commit();
	}

	public void updateDetails() {

		SessionFactory f = HibernateCon.getSessionFactory();
		Session s = f.openSession();
		System.out.println("Enter account number to update your details :");
		long acno = sc.nextLong();
		Account a1 = s.get(Account.class, acno);
		if (a1 != null) {
			System.out.println("Enetr 2 to update account name ");
			System.out.println("Enetr 3 to update account address ");
			System.out.println("Enetr 4 to update account aadhar number ");
			System.out.println("Enetr 5 to update account pan number ");
			System.out.println("Enetr 6 to update account mobile number ");
			System.out.println("Enetr 7 to update account gender ");
			System.out.println("Enetr 8 to update account balance ");
			int ch = sc.nextInt();

			switch (ch) {
			case 2:
				System.out.println("Enter your name ");
				String na = sc.next() + sc.nextLine();
				a1.setAcname(na);
				break;
			case 3:
				System.out.println("Enter Your Address");
				String a = sc.next();
				a1.setAcAddress(a);

				break;
			case 4:
				System.out.println("Enter your AadharNo");
				long ad = sc.nextLong();
				a1.setAadharNo(ad);
				break;
			case 5:
				System.out.println("Enter Your PANNO");
				String pa = sc.next();
				a1.setPanNo(pa);
				break;
			case 6:
				System.out.println("Enter Your MobileNo");
				long mn = sc.nextLong();
				a1.setMobileNo(mn);
				break;
			case 7:
				System.out.println("Enter Your Gender");
				String g = sc.next();
				a1.setGender(g);
				break;
			case 8:
				System.out.println("Enter your Balance");
				double b = sc.nextDouble();
				a1.setBalance(b);

				break;
			default:
				System.out.println("Please enter correct number....");
				break;
			}

			System.out.println("update Account details successfully.......!");
			s.beginTransaction().commit();
		}

		else {
			System.out.println("Did not find your account for that number:" + acno);
		}
	}

}
