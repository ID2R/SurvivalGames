package dev.iiprocraft.sg.api.arena.vote;

import dev.iiprocraft.sg.api.SurvivalGamesAPI;
import dev.iiprocraft.sg.api.arena.SGArena;
import dev.iiprocraft.sg.api.game.GameState;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class VoteProcessor {

    /**
     * @author Mqzn
     * @purpose processes votes for arenas
     */

    private final HashMap<String, Integer> votes;

    public VoteProcessor() {
        votes = new HashMap<>();
        SurvivalGamesAPI.getAPI().getArenaManager()
                .getArenas().stream()
                .filter(arena -> arena.getState() == GameState.WAITING)
                .forEach(arena -> votes.put(arena.getName(), 0));
    }

    public void addVote(String arena) {
        votes.computeIfPresent(arena, (name, votes) -> {
            votes++;
            return votes;
        });
    }

    public void removeVote(String arena) {
        votes.computeIfPresent(arena, (name, votes) -> {
            votes--;
            return votes;
        });
    }


    public int getVotesOf(String arenaName) {
        return votes.getOrDefault(arenaName, 0);
    }

    public SGArena getVotedArena() {

        Optional<String> arenaName;
        Optional<Integer> optional = votes.values()
                .stream().max((o, o2) -> o2-o);

        arenaName = !optional.isPresent()
                ? Optional.ofNullable(new ArrayList<>(votes.keySet())
                .get(ThreadLocalRandom.current().nextInt(votes.size())))
                : optional.map((max) -> {

            for(Map.Entry<String, Integer> entry: votes.entrySet()) {
                if(entry.getValue().intValue() == max.intValue()) {
                    return entry.getKey();
                }
            }
            return null;
        });

        return arenaName.map(name -> SurvivalGamesAPI.getAPI()
                .getArenaManager().getArena(name)).orElse(null);

    }

}
