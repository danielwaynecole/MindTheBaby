
public class GAAlgorithm {

    /* GA parameters */
    private static final double uniformRate = 0.5;
    private static final double mutationRate = 0.015;
    private static final int tournamentSize = 5;
    private static final boolean elitism = true;

    /* Public methods */
    
    // Evolve a population
    public static Population evolvePopulation(Population pop) {
        Population newPopulation = new Population(pop.numBedrooms, pop.numBathrooms, pop.numLivingRooms, pop.numKitchens, pop.numDiningRooms, false);

        // Keep our best individual
        if (elitism) {
            newPopulation.saveIndividual(0, pop.getFittest());
        }

        // Crossover population
        int elitismOffset;
        if (elitism) {
            elitismOffset = 1;
        } else {
            elitismOffset = 0;
        }
        // Loop over the population size and create new individuals with
        // crossover
        for (int i = elitismOffset; i < pop.size(); i++) {
        	Room room1 = tournamentSelection(pop);
            Room room2 = tournamentSelection(pop);
            Room newRoom = crossover(room1, room2);
            newPopulation.saveIndividual(i, newRoom);
        }

        // Mutate population
        for (int i = elitismOffset; i < newPopulation.size(); i++) {
            mutate(newPopulation.getIndividual(i));
        }

        return newPopulation;
    }

    // Crossover individuals
    private static Room crossover(Room indiv1, Room indiv2) {
        Room newSol = new Room(0, 0, 0, 0, 0, 0);

        return newSol;
    }

    // Mutate an individual
    private static void mutate(Room indiv) {
        // Loop through genes

    }

    // Select individuals for crossover
    private static Room tournamentSelection(Population pop) {
        // Create a tournament population
        Population tournament = new Population(pop.numBedrooms, pop.numBathrooms, pop.numLivingRooms, pop.numKitchens, pop.numDiningRooms, false);
        // For each place in the tournament get a random individual
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.size());
            tournament.saveIndividual(i, pop.getIndividual(randomId));
        }
        // Get the fittest
        Room fittest = tournament.getFittest();
        return fittest;
    }
}
