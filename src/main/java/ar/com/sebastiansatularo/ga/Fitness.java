package ar.com.sebastiansatularo.ga;

/**
 * @author Sebastian Scatularo
 */
public final class Fitness<T> {
    private final T creature;
    private final Integer fitness;

    Fitness(T creature, Integer fitness) {
        this.creature = creature;
        this.fitness = fitness;
    }

    T getCreature() {
        return creature;
    }

    public Integer getFitness() {
        return fitness;
    }
}
