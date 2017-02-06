package ar.com.sebastiansatularo;

import ar.com.sebastiansatularo.ga.Fitness;
import ar.com.sebastiansatularo.ga.Population;

import java.util.Collections;
import java.util.function.Predicate;

/**
 * @author Sebastian Scatularo
 */
public class Main {
    private static final String SEARCHED_STRING = "to be or not to be";

    public static void main(String... args) {
        Population<String> population = Population.<String>builder()
                .initialPopulation(Collections.emptyList())
                .fitness(ToBeOrNotToBe::fitness)
                .crosser(ToBeOrNotToBe::crosser)
                .selector(ToBeOrNotToBe::selector)
                .mutator(ToBeOrNotToBe::mutator)
                .build();
        population.evolveUntil(
                ((Predicate<Population<String>>) stringPopulation -> stringPopulation.getGenerations() > 15000)
                        .or(stringPopulation -> stringPopulation.getCreatures().stream().anyMatch(SEARCHED_STRING::equals))
        );
    }

    static class ToBeOrNotToBe {

        public static String crosser(String s) {
            return null;
        }

        public static Integer fitness(String s) {
            return null;
        }


        public static String mutator(String s) {
            return null;
        }

        public static boolean selector(Fitness<String> stringFitness) {
            return false;
        }
    }
}
