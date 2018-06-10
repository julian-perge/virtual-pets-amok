package wcci.virtualpetsamok;

import java.util.Scanner;

public class VirtualPetShelterApp {
	private static VirtualPetShelter vps = new VirtualPetShelter();
	private static boolean isOnShift = true;

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		OrganicPet pet1 = new OrganicDog("Jojo", "Jojo's Bizarre Adventure", 75);
		RoboticPet pet2 = new RoboticDog("Vash", "Trigun", 90, 90);
		OrganicPet pet3 = new OrganicCat("Nails", "A band?", 66);
		RoboticPet pet4 = new RoboticCat("Blue", "Lugato from Trigun", 70, 70);

		vps.addPet(pet1);
		vps.addPet(pet2);
		vps.addPet(pet3);
		vps.addPet(pet4);

		System.out.println("Thank you for volunteering at WCCI's Virtual Pet Shelter and Delicatessen!\n");

		printAllPetsInShelter(vps);
		printListOfActions();
		String userMenuChoice = input.nextLine();

		do {
			gameLoop(input, userMenuChoice);
			while (!isValidMenuChoice(userMenuChoice)) {
				System.out.println("That is not correct, please try again.");
				userMenuChoice = input.nextLine();
			}
		} while (isValidMenuChoice(userMenuChoice));
	}

	private static void printListOfActions() {
		System.out.println("\nWhat would you like to do next?");

		System.out.println(
				"0. Check pets stats\n"
				+ "1. Feed the pets\n"
				+ "2. Play with all the pets\n"
				+ "3. Play with a specific pet\n"
				+ "4. Adopt a pet\n" 
				+ "5. Admit a pet\n" 
				+ "6. Oil all robotic pets\n"
				+ "7. Walk a dog\n"
				+ "8. Walk all dogs\n"
				+ "9. Quit");
	}

	private static boolean isValidMenuChoice(String userMenuChoice) {
		if (!userMenuChoice.equals("1") && !userMenuChoice.equals("2") && !userMenuChoice.equals("3")
				&& !userMenuChoice.equals("4") && !userMenuChoice.equals("5") && !userMenuChoice.equals("6")) {
			return false;
		}
		return true;
	}

	private static void printAllPetsInShelter(VirtualPetShelter vps) {
		if (vps.getAllPets().size() == 0) {
			System.out.println("There are no pets in the shelter...\nThey all went to loving homes!");
		} else {
			System.out.println("Type\t  | Name\t|Health\t|Hunger\t|Fun\t|Bladder|Happy\t|Oil");
	        System.out.println("----------------------------------");
			for (VirtualPet pet : vps.getAllPets()) {
				System.out.println(pet.getClass().getSimpleName() + "| " + pet.toString());
				System.out.print("\n");
			}
		}
	}

	private static void printAllPetsAndDescriptions(VirtualPetShelter vps) {
		if (vps.getAllPets().size() == 0) {
			System.out.println("There are no pets in the shelter...\nThey all went to loving homes!");
		} else {
			for (VirtualPet pet : vps.getAllPets()) {
				System.out.println(pet.getName() + " " + pet.getDescription());
			}
		}
	}

	private static Boolean gameLoop(Scanner input, String userMenuChoice) {
		isOnShift = true;

		while (isOnShift) {
			switch (userMenuChoice) {
			case "0":
				printAllPetsInShelter(vps);
				break;
			case "1":
				vps.feedAllOrganicPets();
				System.out.println("\nYou feed all the pets! They are very happy! ^_^");
				break;
			case "2":
				vps.playWithAll();
				System.out.println("\nYou play with all the pets! They seem very tired...");
				break;
			case "3":
				System.out.println("\nWhich pet would you like to interact with?");
				printAllPetsAndDescriptions(vps);
				String petToInteractWith = input.nextLine();
//				vps.returnSpecificPet(petToInteractWith).play();
				System.out.println("You played with " + petToInteractWith + "!");
				break;
			case "4":
				System.out.println("\nWhich pet would you like to adopt?");
				printAllPetsAndDescriptions(vps);
				String petToAdopt = input.nextLine();
				vps.adoptPet(vps.returnSpecificPet(petToAdopt));
				System.out.println("Thank you for adopting " + petToAdopt);
				printAllPetsInShelter(vps);
				break;
			case "5":
				System.out.println("That poor thing! What would you like to name it?");
				String petToAdmitName = input.nextLine();
				System.out.println("And what about a description?");
				String petToAdmitsDesc = input.nextLine();
				System.out.println("What type of pet is " + petToAdmitName
						+ "?\n1. Organic Dog\n2. Organic Cat\n3. Robotic Dog\n4. Robotic Cat");
				VirtualPet petToAdmit = new OrganicDog("", "", 0);
				String typeOfPet = input.nextLine();
				while (!typeOfPet.equals("1") && typeOfPet.equals("2") && typeOfPet.equals("3")
						&& typeOfPet.equals("4")) {
					switch (typeOfPet) {
					case "1":
						petToAdmit = new OrganicDog(petToAdmitName, petToAdmitsDesc, 50);
						break;
					case "2":
						petToAdmit = new OrganicCat(petToAdmitName, petToAdmitsDesc, 50);
						break;
					case "3":
						petToAdmit = new RoboticDog(petToAdmitName, petToAdmitsDesc, 50);
						break;
					case "4":
						petToAdmit = new RoboticCat(petToAdmitName, petToAdmitsDesc, 50);
						break;
					default:
						break;
					}
				}

				System.out.println(
						"Let's make sure " + petToAdmitName + " has a cozy time here until he goes to a loving home.");
				vps.addPet(petToAdmit);
				System.out.println("[" + petToAdmitName + "] " + petToAdmitsDesc + " has joined the shelter crew!");
				break;
			case "6":
				System.out.println("Shift is over. See you tomorrow!");
				isOnShift = false;
				System.exit(0);
				break;
			default:
				System.out.println("I'm sorry, that is not a valid choice.");
				break;
			}
			printListOfActions();
			userMenuChoice = input.nextLine();
		}
		return isOnShift;
	}
}
