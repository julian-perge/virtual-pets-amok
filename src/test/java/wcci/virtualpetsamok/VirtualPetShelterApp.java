package wcci.virtualpetsamok;

import java.util.Scanner;

public class VirtualPetShelterApp {
	private static VirtualPetShelter vps = new VirtualPetShelter();
	private static boolean isOnShift = true;

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		OrganicPet pet1 = new OrganicDog("Jojo", "Jojo's Bizarre Adventure", 75);
		OrganicPet pet5 = new OrganicDog("Jack", "blank", 75);
		OrganicPet pet6 = new OrganicDog("Jim", "blank", 75);

		RoboticPet pet2 = new RoboticDog("Vash", "Trigun", 100, 90);
		OrganicPet pet3 = new OrganicCat("Nail", "A band?", 66);
		RoboticPet pet4 = new RoboticCat("Blue", "Lugato from Trigun", 100, 50);

		vps.addPet(pet5);
		vps.addPet(pet6);
		vps.addPet(pet1);
		vps.addPet(pet2);
		vps.addPet(pet3);
		vps.addPet(pet4);

		System.out.println("Thank you for volunteering at WCCI's Virtual Pet Shelter!\n");

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

	private static Boolean gameLoop(Scanner input, String userMenuChoice) {
		isOnShift = true;

		while (isOnShift) {
			switch (userMenuChoice) {
			case "-1":
				printAllPetsInShelter(vps);
				vps.shelterTick();
				vps.shelterTick();
				printAllPetsInShelter(vps);
				break;
			case "0":
				System.out.println("What list of pets would you like to print?\n" + "1. Print all pets in shelter\n"
						+ "2. Print all organic pets\n" + "3. Print all robotic pets");

				String listToPrint = input.nextLine();
				switch (listToPrint) {
				case "1":
					printAllPetsInShelter(vps);
					break;
				case "2":
					printAllOrganicPetsInShelter(vps);
					break;
				case "3":
					printAllRoboticPetsInShelter(vps);
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
				switch (multiPetInput) {
				case "1":
					feedAllOrganicPets(vps);
					break;
				case "2":
					playWithAllOrganicPets(vps);
					break;
				case "3":
					takeOrganicDogsForWalk(vps);
					break;
				case "4":
					oilAllRoboticPets(vps);
					break;
				default:
					break;
				}
				break;
			case "2":
				// "Single pet interaction menu"
				// printAllPetsAndDescriptions(vps);

				System.out.println("\nWhich type of pet would you like to interact with?\n" + "1. Organic Pets\n"
						+ "2. Robotic Pets");
				String petToInteractWith = input.nextLine();

				if (petToInteractWith.equals("1")) {
					petToInteractWith = organicPetInteractionMenu(input);

				} else {
					petToInteractWith = roboticPetInteractionMenu(input);
				}

				System.out.println("Interacted with: " + petToInteractWith);
				break;
			case "3":
				// "Shelter cleaning tasks"
				System.out.println("1. Clean litterbox\n2. Clean dog cages\n3. Clean specific dog cage");
				String cleaningInput = input.nextLine();
				switch (cleaningInput) {
				case "1":
					if (!vps.isLitterBoxClean()) {
						System.out.println("You decide to clean the litterbox");
						vps.cleanLitterBox();
					} else {
						System.out.println("The litterbox does not need cleaned right now!");
					}
					break;
				case "2":
					vps.cleanAllDogCages();
					for (CageForDog cage : vps.cagesForDogs.values()) {
						System.out.println("You cleaned " + cage.getOwnerOfCage()
								+ " cage. Cleanliness of the cage is now: " + cage.getWasteLevelOfCage());
					}
					break;
				case "3":
					System.out.println("Which dog's cage would you like to clean?");
					printAllCages();
					break;
				default:
					break;
				}

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
			case "8":
				vps.shelterTick();
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

	private static void printAllCages() {
		for (CageForDog cage : vps.cagesForDogs.values()) {
			System.out.println(cage.toString());
		}
	}

	private static boolean isValidMenuChoice(String userMenuChoice) {
		if (!userMenuChoice.equals("1") && !userMenuChoice.equals("2") && !userMenuChoice.equals("3")
				&& !userMenuChoice.equals("4") && !userMenuChoice.equals("5") && !userMenuChoice.equals("6")) {
			return false;
		}
		return true;
	}

	/*
	 * 
	 * SINGLE PET METHODS
	 * 
	 */

	private static String organicPetInteractionMenu(Scanner input) {
		printAllOrganicPetsInShelter(vps);
		System.out.println("Which pet would you like to interact with?");
		String petToInteractWith = input.nextLine();
		if (vps.pets.containsKey(petToInteractWith)) {
			OrganicPet orgPet;
			if (vps.pets.get(petToInteractWith) instanceof OrganicDog) {
				orgPet = (OrganicDog) vps.pets.get(petToInteractWith);
				System.out.println("1. Play\n" + "2. Feed\n" + "3. Take " + petToInteractWith
						+ " to use the bathroom\n4. Take for a walk");
			} else {
				orgPet = (OrganicCat) vps.pets.get(petToInteractWith);
				System.out.println("1. Play\n" + "2. Feed\n" + "3. Take " + petToInteractWith + " to use the bathroom");
			}

			String organicMenuInput = input.nextLine();
			switch (organicMenuInput) {
			case "1":
				orgPet.play();
				break;
			case "2":
				orgPet.feed();
				break;
			case "3":
				vps.catUsesLitterBox();
				orgPet.useBathroom();
				break;
			default:
				if (organicMenuInput.equals("4") && orgPet instanceof OrganicDog) {
					((OrganicDog) orgPet).walk();
					System.out.println("You take " + orgPet.getName() + " for a walk.");
					orgPet.tick();
				}
				break;
			}
		}

		return petToInteractWith;
	}

	private static String roboticPetInteractionMenu(Scanner input) {
		printAllRoboticPetsInShelter(vps);
		System.out.println("Which pet would you like to interact with?");
		String petToInteractWith = input.nextLine();
		if (vps.pets.containsKey(petToInteractWith)) {
			RoboticPet roboPet = (RoboticPet) vps.pets.get(petToInteractWith);
			System.out.println("1. Oil\n" + "2. Decommission");
			String robotMenuInput = input.nextLine();
			switch (robotMenuInput) {
			case "1":
				roboPet.oilPet();
				System.out
						.println("I'm sure " + roboPet.getName() + " would appreciate the top-up if he had feelings.\n");
				System.out.println(roboPet.getName() + "'s oil level is now:" + roboPet.getOilLevel());
				break;
			case "2":
				System.out.println("Goodnight, " + roboPet.getName() + ", you served the shelter well.\n");
				vps.disassembleRobotPet(roboPet);
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
	private static void playWithAllOrganicPets(VirtualPetShelter vps) {
		vps.playWithAll();
		for (VirtualPet pet : vps.getOrganicPets()) {
			if (pet instanceof OrganicPet) {
				System.out.println("You play with " + pet.getName() + "! Happiness Level is now: "
						+ ((OrganicPet) pet).getHappiness());
			}
		}
	}

	private static void feedAllOrganicPets(VirtualPetShelter vps) {
		vps.feedAllOrganicPets();
		for (VirtualPet pet : vps.getOrganicPets()) {
			if (pet instanceof OrganicPet) {
				System.out.println(
						"You feed " + pet.getName() + "! Hunger Level is now: " + ((OrganicPet) pet).getHunger());
			}
		}
	}

	private static void takeOrganicDogsForWalk(VirtualPetShelter vps) {
		vps.walkAllOrganicDogs();
		for (VirtualPet pet : vps.getOrganicPets()) {
			if (pet instanceof OrganicDog) {
				System.out.println("You take " + pet.getName() + " out for a walk! Happiness is now: "
						+ ((OrganicPet) pet).getHappiness());
			}
		}
	}

	private static void oilAllRoboticPets(VirtualPetShelter vps) {
		vps.oilAllRoboticPets();
		for (VirtualPet pet : vps.getRoboticPets()) {
			if (pet instanceof RoboticPet) {
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

	private static void printListOfActions() {
		System.out.println("\nWhat would you like to do next?");

		System.out.println("0. Pet List Print Menu\n" + "1. Multi Pet Interaction Menu\n"
				+ "2. Single Pet Interaction Menu\n" + "3. Shelter Cleaning Menu\n" + "4. Adopt a pet\n"
				+ "5. Admit a pet\n" + "7. Walk a dog\n" + "8. Walk all dogs\n" + "9. Quit");
	}

	private static void printAllPetsInShelter(VirtualPetShelter vps) {
		if (vps.getAllPets().size() == 0) {
			System.out.println("There are no pets in the shelter...\nThey all went to loving homes!");
		} else {
			System.out.println("Here are all the pets in the shelter");
			System.out.println("Type\t  |Name\t|Health\t|Hunger\t|Fun\t|Bladder|Happy\t|Oil");
			System.out.println("----------------------------------");
			for (VirtualPet pet : vps.getAllPets()) {
				if (pet instanceof RoboticPet) {
					System.out.println(pet.getClass().getSimpleName() + "|" + pet.getName() + "\t|" + pet.getHealth()
							+ "\t \t \t \t \t|" + ((RoboticPet) pet).getOilLevel());
				} else {
					System.out.println(pet.getClass().getSimpleName() + "|" + pet.toString());
				}
				System.out.print("\n");
			}
		}
	}

	private static void printAllOrganicPetsInShelter(VirtualPetShelter vps) {
		if (vps.getAllPets().size() == 0) {
			System.out.println("There are no pets in the shelter...\nThey all went to loving homes!");
		} else {
			System.out.println("Here are all the organic pets in the shelter");
			System.out.println("Type\t  |Name\t|Health\t|Hunger\t|Fun\t|Bladder|Happy");
			System.out.println("----------------------------------");
			for (VirtualPet pet : vps.getOrganicPets()) {
				System.out.println(pet.getClass().getSimpleName() + "|" + pet.toString());
				System.out.print("\n");
			}
		}
	}

	private static void printAllRoboticPetsInShelter(VirtualPetShelter vps) {
		if (vps.getAllPets().size() == 0) {
			System.out.println("There are no pets in the shelter...\nThey all went to loving homes!");
		} else {
			System.out.println("Here are all the robotic pets in the shelter");
			System.out.println("Type\t  |Name\t|Health\t|Oil");
			System.out.println("----------------------------------");
			for (VirtualPet pet : vps.getRoboticPets()) {
				System.out.println(pet.getClass().getSimpleName() + "|" + pet.toString());
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
}
