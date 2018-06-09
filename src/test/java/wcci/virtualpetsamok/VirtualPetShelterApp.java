package wcci.virtualpetsamok;

import java.util.Scanner;

public class VirtualPetShelterApp {
	private static VirtualPetShelter vps = new VirtualPetShelter();
	private static boolean isOnShift = true;

	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);

		VirtualPet pet1 = new OrganicDog("Jojo", "Jojo's Bizarre Adventure", 75);
		VirtualPet pet2 = new RoboticDog("Vash", "Trigun", 90, 90);
		VirtualPet pet3 = new OrganicCat("Nails", "A band?", 66);
		VirtualPet pet4 = new RoboticCat("Blue", "Lugato from Trigun", 70, 70);

		vps.addPet(pet1);
		vps.addPet(pet2);
		vps.addPet(pet3);
		vps.addPet(pet4);

		System.out.println("Thank you for volunteering at WCCI's Virtual Pet Shelter and Delicatessen!\n");

		printAllPetsInShelter(vps);
		printListOfActions();
		String userMenuChoice = input.nextLine();

		do
		{
			gameLoop(input, userMenuChoice);
			while (!isValidMenuChoice(userMenuChoice))
			{
				System.out.println("That is not correct, please try again.");
				userMenuChoice = input.nextLine();
			}
		} while (isValidMenuChoice(userMenuChoice));
	}

	private static void printListOfActions()
	{
		System.out.println("\nWhat would you like to do next?");

		System.out.println(
				"0. Check pets stats\n1. Feed the pets\n2. Play with all the pets\n3. Play with a specific pet\n"
						+ "4. Adopt a pet\n" + "5. Admit a pet\n" + "6. Quit");
	}

	private static boolean isValidMenuChoice(String userMenuChoice)
	{
		if (!userMenuChoice.equals("1") && !userMenuChoice.equals("2") && !userMenuChoice.equals("3")
				&& !userMenuChoice.equals("4") && !userMenuChoice.equals("5")
				&& !userMenuChoice.equals("6")) { return false; }
		return true;
	}

	private static void printAllPetsInShelter(VirtualPetShelter vps)
	{
		if(vps.getPets().size() == 0)
		{
			System.out.println("There are no pets in the shelter...\nThey all went to loving homes!");
		}
		else
		{
			for (VirtualPet pet : vps.getPets())
			{
				if(pet instanceof OrganicPet)
				{
					System.out.println(pet.getName() + "\t|" + ((OrganicPet) pet).toString()) ;
				}
				System.out.println(pet.getName() + "\t" + pet.getHealth() + "\t" + pet. + "\t" + pet.getEnergy());
			}
		}
	}

	private static void printAllPetsAndDescriptions(VirtualPetShelter vps)
	{
		if(vps.getPets().size() == 0)
		{
			System.out.println("There are no pets in the shelter...\nThey all went to loving homes!");
		}
		else
		{
			for (VirtualPet pet : vps.getPets())
			{
				System.out.println("[" + pet.getName() + "]\t" + pet.getDesc());
			}
		}
	}

	private static Boolean gameLoop(Scanner input, String userMenuChoice)
	{
		isOnShift = true;

		while (isOnShift)
		{
			switch (userMenuChoice)
			{
				case "0":
					printAllPetsInShelter(vps);
					break;
				case "1":
					vps.feedAll();
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
					vps.returnSpecificPet(petToInteractWith).play();
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
					String petToAdmit = input.nextLine();
					System.out.println("And what about a description?");
					String petToAdmitsDesc = input.nextLine();
					System.out.println(
							"Let's make sure " + petToAdmit + " has a cozy time here until he goes to a loving home.");
					vps.addPet(new VirtualPet(petToAdmit, petToAdmitsDesc));
					System.out.println("[" + petToAdmit + "] " + petToAdmitsDesc + " has joined the shelter crew!");
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
