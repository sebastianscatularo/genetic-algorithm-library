package ar.com.sebastiansatularo.ga;

import java.util.Collection;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Population of candidate solutions
 * called individuals, creatures or phenotypes
 *
 * @author Sebastian Scatularo
 */
public class Population<T> {
    public static <T> Builder<T> builder() {
        return new Builder<>();
    }
    private int generations = 0;
    private final Function<T, Integer> fitness;
    private final Predicate<Fitness<T>> selector;
    private final Function<T, T> crosser;
    private final Function<T, T> mutator;
    private Collection<T> creatures;

    private Population(Builder<T> builder) {
        fitness = builder.fitness;
        selector = builder.selector;
        crosser = builder.crosser;
        mutator = builder.mutator;
        creatures = builder.creatures;
    }

    public void evolveUntil(Predicate<Population<T>> predicate) {
        while (!predicate.test(this)) {
            // Calculate fitness of each creature
            Collection<Fitness<T>> fitnesses = creatures.stream()
                    .map(t -> new Fitness<T>(t, fitness.apply(t)))
                    .collect(Collectors.toList());
            // Select individuals
            Collection<T> selection = fitnesses.stream()
                    .filter(selector).map(Fitness::getCreature)
                    .collect(Collectors.toList());
            // Cross the selected individuals
            Collection<T> newPopulation = selection
                    .stream()
                    .map(crosser)
                    .collect(Collectors.toList());
            // Mutate the new population
            creatures = newPopulation.stream().map(mutator).collect(Collectors.toList());
            generations++;
        }
    }

    public int getGenerations() {
        return generations;
    }

    public Collection<T> getCreatures() {
        return creatures;
    }

    public static class Builder<T> {
        private Function<T, Integer> fitness;
        private Predicate<Fitness<T>> selector;
        private Function<T, T> crosser;
        private Function<T, T> mutator;
        private Collection<T> creatures;

        public Population<T> build() {
            return new Population<T>(this);
        }

        public Builder<T> initialPopulation(Collection<T> creatures) {
            this.creatures = creatures;
            return this;
        }

        public Builder<T> fitness(Function<T, Integer> fitness) {
            this.fitness = fitness;
            return this;
        }

        public Builder<T> selector(Predicate<Fitness<T>> selector) {
            this.selector = selector;
            return this;
        }

        public Builder<T> crosser(Function<T, T> crosser) {
            this.crosser = crosser;
            return this;
        }

        public Builder<T> mutator(Function<T, T> mutator) {
            this.mutator = mutator;
            return this;
        }
    }

}
