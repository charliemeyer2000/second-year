package edu.virginia.sde.javafx.tacos;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HotDogVotes {
    public static Set<String> CHOICES = Set.of("Sandwich", "Taco", "Both", "Neither");

    private final HashMap<String, Integer> votes = new HashMap<>(
            Map.of(
                    "Sandwich", 0,
                    "Taco", 0,
                    "Both", 0,
                    "Neither", 0
            ));

    public void addVote(String hotDogCategory) {
        if (!votes.containsKey(hotDogCategory)) {
            throw new IllegalArgumentException("Invalid vote: " + hotDogCategory);
        }
        votes.put(hotDogCategory, votes.get(hotDogCategory) + 1);
    }

    public int getVotes(String hotDogCategory) {
        return votes.get(hotDogCategory);
    }

    public void setVote(String hotDogCategory, int voteCount) {
        votes.put(hotDogCategory, voteCount);
    }
}
