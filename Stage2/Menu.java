package carsharing;

import carsharing.dao.CompanyDAOImpl;
import carsharing.models.Company;

import java.util.List;
import java.util.Scanner;

public class Menu {
    private static final Scanner scanner = new Scanner(System.in);
    private static final CompanyDAOImpl companies = new CompanyDAOImpl();

    public static void mainMenu() {
        int option;
        do {
            System.out.println("1. Log in as a manager");

            System.out.println("0. Exit");

            option = scanner.nextInt();

            switch (option) {
                case 1:
                    managerMenu();
                    break;
                case 0:
                    option = 0;
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }

        } while (option != 0);
    }

    private static void managerMenu() {
        boolean menuDoGerente = true;
        do {
            System.out.println("1. Company list");
            System.out.println("2. Create a company");
            System.out.println("0. Back");

            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    listCompanies();
                    break;
                case 2:
                    createACompany();
                    break;
                case 0:
                    mainMenu();
                    break;
            }
        } while (menuDoGerente);
    }

    private static void createACompany() {
        System.out.println("Enter the company name:");
        scanner.nextLine();
        String name = scanner.nextLine();
        companies.create(new Company(0, name));
        System.out.println("The company was created!\n");
    }

    private static void listCompanies() {
        List<Company> companyList = companies.getAll();
        if (companyList.isEmpty()) {
            System.out.println("\nThe company list is empty!\n");
        } else {
            System.out.println("Company list:");
            for (Company company : companyList) {
                System.out.println(company.getId() + ". " + company.getName());
            }
            System.out.println();
        }

    }
}
