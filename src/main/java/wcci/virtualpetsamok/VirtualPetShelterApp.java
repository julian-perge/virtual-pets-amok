package wcci.virtualpetsamok;

import java.util.Scanner;

public class VirtualPetShelterApp
{
	private static VirtualPetShelter shelter = new VirtualPetShelter();
	private static boolean isOnShift = true;

	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);

		OrganicPet pet1 = new OrganicDog("Jojo", "Jojo's Bizarre Adventure", 75);
		OrganicPet pet5 = new OrganicDog("Jack", "blank", 75);
		OrganicPet pet6 = new OrganicDog("Jim", "blank", 75);

		RoboticPet pet2 = new RoboticDog("Vash", "Trigun", 100, 90);
		OrganicPet pet3 = new OrganicCat("Nail", "A band?", 66);
		RoboticPet pet4 = new RoboticCat("Blue", "Lugato from Trigun", 100, 50);

		shelter.addPet(pet5);
		shelter.addPet(pet6);
		shelter.addPet(pet1);
		shelter.addPet(pet2);
		shelter.addPet(pet3);
		shelter.addPet(pet4);

		System.out.println("Thank you for volunteering at WCCI's Virtual Pet Shelter!\n");

		printAllPetsInShelter(shelter);
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

	private static Boolean gameLoop(Scanner input, String userMenuChoice)
	{
		isOnShift = true;

		while (isOnShift)
		{
			switch (userMenuChoice)
			{
				case "-1":
					printAllPetsInShelter(shelter);
					shelter.shelterTick();
					shelter.shelterTick();
					printAllPetsInShelter(shelter);
					break;
				case "0":
					System.out.println("What list of pets would you like to print?\n" + "1. Print all pets in shelter\n"
							+ "2. Print all organic pets\n" + "3. Print all robotic pets");

					String listToPrint = input.nextLine();
					switch (listToPrint)
					{
						case "1":
							printAllPetsInShelter(shelter);
							break;
						case "2":
							printAllOrganicPetsInShelter(shelter);
							break;
						case "3":
							printAllRoboticPetsInShelter(shelter);
							break;
						default:
							break;
					}
					break;
				case "1":
					// "Multi pet interaction menu"
					System.out.println("1. Feed all pets\n" + "2. Play with all pets\n" + "3. Walk all dogs\n"
							+ "4. Oil all robotic pets\n" + "5. To go back to the previous menu");
					String multiPetInput = input.nextLine();
					switch (multiPetInput)
					{
						case "1":
							feedAllOrganicPets(shelter);
							shelter.shelterTick();
							break;
						case "2":
							playWithAllOrganicPets(shelter);
							shelter.shelterTick();
							break;
						case "3":
							takeOrganicDogsForWalk(shelter);
							shelter.shelterTick();
							break;
						case "4":
							oilAllRoboticPets(shelter);
							shelter.shelterTick();
							break;
						default:
							break;
					}
					break;
				case "2":
					// "Single pet interaction menu"

					System.out.println("\nWhich type of pet would you like to interact with?\n" + "1. Organic Pets\n"
							+ "2. Robotic Pets");
					String petToInteractWith = input.nextLine();

					if (petToInteractWith.equals("1"))
					{
						petToInteractWith = organicPetInteractionMenu(input);
					}
					else
					{
						petToInteractWith = roboticPetInteractionMenu(input);
					}

					System.out.println("Interacted with: " + petToInteractWith);
					shelter.shelterTick();
					break;
				case "3":
					// "Shelter cleaning tasks"
					System.out.println("1. Clean litterbox\n2. Clean dog cages\n3. Clean specific dog cage");
					String cleaningInput = input.nextLine();
					switch (cleaningInput)
					{
						case "1":
							if (!shelter.isLitterBoxClean())
							{
								System.out.println("You decide to clean the litterbox");
								shelter.cleanLitterBox();
							}
							else
							{
								System.out.println("The litterbox does not need cleaned right now!");
							}
							break;
						case "2":
							shelter.cleanAllDogCages();
							for (CageForDog cage : shelter.cagesForDogs.values())
							{
								System.out.println("You cleaned " + cage.getOwnerOfCage()
										+ " cage. Cleanliness of the cage is now: " + cage.getWasteLevelOfCage());
							}
							break;
						case "3":
							System.out.println("Which dog's cage would you like to clean?");
							printAllCages();
							String cageToClean = input.nextLine();
							shelter.cleanCage((OrganicDog) shelter.pets.get(cageToClean));
							break;
						default:
							break;
					}
					shelter.shelterTick();
					break;
				case "4":
					System.out.println("\nWhich pet would you like to adopt?");
					printAllPetsAndDescriptions(shelter);
					String petToAdopt = input.nextLine();
					shelter.adoptPet(shelter.returnSpecificPet(petToAdopt));
					System.out.println("Thank you for adopting " + petToAdopt + "!");
					shelter.shelterTick();
					printAllPetsInShelter(shelter);
					break;
				case "5":
					System.out.println("That poor thing! What would you like to name it?");
					String petToAdmitName = input.nextLine();
					System.out.println("And what about a description?");
					String petToAdmitsDesc = input.nextLine();
					System.out.println("What type of pet is " + petToAdmitName
							+ "?\n1. Organic Dog\n2. Organic Cat\n3. Robotic Dog\n4. Robotic Cat");
					String typeOfPet = input.nextLine();
					switch (typeOfPet)
					{
						case "1":
							shelter.addPet(new OrganicDog(petToAdmitName, petToAdmitsDesc, 50));
							break;
						case "2":
							shelter.addPet(new OrganicCat(petToAdmitName, petToAdmitsDesc, 50));
							break;
						case "3":
							shelter.addPet(new RoboticDog(petToAdmitName, petToAdmitsDesc, 50));
							break;
						case "4":
							shelter.addPet(new RoboticCat(petToAdmitName, petToAdmitsDesc, 50));
							break;
						default:
							break;
					}
					System.out.println("Let's make sure " + petToAdmitName
							+ " has a cozy time here until he goes to a loving home.\n");

					System.out
							.println("[" + petToAdmitName + "] " + petToAdmitsDesc + " has joined the shelter crew!\n");
					shelter.shelterTick();
					printAllPetsInShelter(shelter);
					break;
				case "8":
					shelter.shelterTick();
					break;
				case "9":
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

	private static boolean isValidMenuChoice(String userMenuChoice)
	{
		if (!userMenuChoice.equals("1") && !userMenuChoice.equals("2") && !userMenuChoice.equals("3")
				&& !userMenuChoice.equals("4") && !userMenuChoice.equals("5") && !userMenuChoice.equals("6"))
		{
			return false;
		}
		return true;
	}

	/*
	 * 
	 * SINGLE PET METHODS
	 * 
	 */

	private static String organicPetInteractionMenu(Scanner input)
	{
		printAllOrganicPetsInShelter(shelter);
		System.out.println("Which pet would you like to interact with?");
		String petToInteractWith = input.nextLine();
		if (shelter.pets.containsKey(petToInteractWith))
		{
			OrganicPet orgPet;
			if (shelter.pets.get(petToInteractWith) instanceof OrganicDog)
			{
				orgPet = (OrganicDog) shelter.pets.get(petToInteractWith);
				System.out.println("1. Play\n" + "2. Feed\n" + "3. Take " + petToInteractWith
						+ " to use the bathroom\n4. Take for a walk");
			}
			else
			{
				orgPet = (OrganicCat) shelter.pets.get(petToInteractWith);
				System.out.println("1. Play\n" + "2. Feed\n" + "3. Take " + petToInteractWith + " to use the bathroom");
			}

			String organicMenuInput = input.nextLine();
			switch (organicMenuInput)
			{
				case "1":
					orgPet.play();
					break;
				case "2":
					orgPet.feed();
					break;
				case "3":
					shelter.catUsesLitterBox();
					orgPet.useBathroom();
					break;
				default:
					if (organicMenuInput.equals("4") && orgPet instanceof OrganicDog)
					{
						((OrganicDog) orgPet).walk();
						System.out.println("You take " + orgPet.getName() + " for a walk.");
						orgPet.tick();
					}
					break;
			}
		}

		return petToInteractWith;
	}

	private static String roboticPetInteractionMenu(Scanner input)
	{
		printAllRoboticPetsInShelter(shelter);
		System.out.println("Which pet would you like to interact with?");
		String petToInteractWith = input.nextLine();
		if (shelter.pets.containsKey(petToInteractWith))
		{
			RoboticPet roboPet = (RoboticPet) shelter.pets.get(petToInteractWith);
			System.out.println("1. Oil\n" + "2. Decommission");
			String robotMenuInput = input.nextLine();
			switch (robotMenuInput)
			{
				case "1":
					roboPet.oilPet();
					System.out.println(
							"I'm sure " + roboPet.getName() + " would appreciate the top-up if he had feelings.\n");
					System.out.println(roboPet.getName() + "'s oil level is now:" + roboPet.getOilLevel());
					break;
				case "2":
					System.out.println("Goodnight, " + roboPet.getName() + ", you served the shelter well.\n");
					shelter.disassembleRobotPet(roboPet);
					break;
				default:
					break;
			}
		}
		return petToInteractWith;
	}

	/*
	 * 
	 * MULTI PET METHODS
	 * 
	 */
	private static void playWithAllOrganicPets(VirtualPetShelter vps)
	{
		vps.playWithAll();
		for (VirtualPet pet : vps.getOrganicPets())
		{
			if (pet instanceof OrganicPet)
			{
				System.out.println("You play with " + pet.getName() + "! Happiness Level is now: "
						+ ((OrganicPet) pet).getHappiness());
			}
		}
	}

	private static void feedAllOrganicPets(VirtualPetShelter vps)
	{
		vps.feedAllOrganicPets();
		for (VirtualPet pet : vps.getOrganicPets())
		{
			if (pet instanceof OrganicPet)
			{
				System.out.println(
						"You feed " + pet.getName() + "! Hunger Level is now: " + ((OrganicPet) pet).getHunger());
			}
		}
	}

	private static void takeOrganicDogsForWalk(VirtualPetShelter vps)
	{
		vps.walkAllOrganicDogs();
		for (VirtualPet pet : vps.getOrganicPets())
		{
			if (pet instanceof OrganicDog)
			{
				System.out.println("You take " + pet.getName() + " out for a walk! Happiness is now: "
						+ ((OrganicPet) pet).getHappiness());
			}
		}
	}

	private static void oilAllRoboticPets(VirtualPetShelter vps)
	{
		vps.oilAllRoboticPets();
		for (VirtualPet pet : vps.getRoboticPets())
		{
			if (pet instanceof RoboticPet)
			{
				System.out.println(
						"You oil up " + pet.getName() + "! Oil Level is now: " + ((RoboticPet) pet).getOilLevel());
			}
		}
	}
	/*
	 * 
	 * PRINTING
	 * 
	 */

	private static void printListOfActions()
	{
		System.out.println("\nWhat would you like to do next?");

		System.out.println("0. Pet List Print Menu\n" + "1. Multi Pet Interaction Menu\n"
				+ "2. Single Pet Interaction Menu\n" + "3. Shelter Cleaning Menu\n" + "4. Adopt a pet\n"
				+ "5. Admit a pet\n" + "9. Quit");
	}
	

	private static void printAllCages()
	{
		for (CageForDog cage : shelter.cagesForDogs.values())
		{
			System.out.println(cage.toString());
		}
	}

	private static void printAllPetsInShelter(VirtualPetShelter vps)
	{
		if (vps.getAllPets().size() == 0)
		{
			System.out.println("There are no pets in the shelter...\nThey all went to loving homes!");
		}
		else
		{
			System.out.println("Here are all the pets in the shelter");
			System.out.println("Type\t  |Name\t|Health\t|Hunger\t|Fun\t|Bladder|Happy\t|Oil");
			System.out.println("----------------------------------");
			for (VirtualPet pet : vps.getAllPets())
			{
				if (pet instanceof RoboticPet)
				{
					System.out.println(pet.getClass().getSimpleName() + "|" + pet.getName() + "\t|" + pet.getHealth()
							+ "\t \t \t \t \t|" + ((RoboticPet) pet).getOilLevel());
				}
				else
				{
					System.out.println(pet.getClass().getSimpleName() + "|" + pet.toString());
				}
				System.out.print("\n");
			}
		}
	}

	private static void printAllOrganicPetsInShelter(VirtualPetShelter vps)
	{
		if (vps.getAllPets().size() == 0)
		{
			System.out.println("There are no pets in the shelter...\nThey all went to loving homes!");
		}
		else
		{
			System.out.println("Here are all the organic pets in the shelter");
			System.out.println("Type\t  |Name\t|Health\t|Hunger\t|Fun\t|Bladder|Happy");
			System.out.println("----------------------------------");
			for (VirtualPet pet : vps.getOrganicPets())
			{
				System.out.println(pet.getClass().getSimpleName() + "|" + pet.toString());
				System.out.print("\n");
			}
		}
	}

	private static void printAllRoboticPetsInShelter(VirtualPetShelter vps)
	{
		if (vps.getAllPets().size() == 0)
		{
			System.out.println("There are no pets in the shelter...\nThey all went to loving homes!");
		}
		else
		{
			System.out.println("Here are all the robotic pets in the shelter");
			System.out.println("Type\t  |Name\t|Health\t|Oil");
			System.out.println("----------------------------------");
			for (VirtualPet pet : vps.getRoboticPets())
			{
				System.out.println(pet.getClass().getSimpleName() + "|" + pet.toString());
				System.out.print("\n");
			}
		}
	}

	private static void printAllPetsAndDescriptions(VirtualPetShelter vps)
	{
		if (vps.getAllPets().size() == 0)
		{
			System.out.println("There are no pets in the shelter...\nThey all went to loving homes!");
		}
		else
		{
			for (VirtualPet pet : vps.getAllPets())
			{
				System.out.println(pet.getName() + " " + pet.getDescription());
			}
		}
	}
}
