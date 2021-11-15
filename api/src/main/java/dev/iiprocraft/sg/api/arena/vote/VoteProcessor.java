package dev.iiprocraft.sg.api.arena.vote;

import dev.iiprocraft.sg.api.SurvivalGamesAPI;
import dev.iiprocraft.sg.api.game.GameState;
import dev.iiprocraft.sg.api.arena.SGArena;
import dev.iiprocraft.sg.api.arena.SGArena.ArenaVote;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class VoteProcessor {

    /**
     * @author Mqzn
     * @purpose processes votes for arenas
     */

    private final Map<String, Set<ArenaVote>> votes;

    public VoteProcessor() {
        votes = new HashMap<>();
        SurvivalGamesAPI.getAPI().getArenaManager()
                .getArenas().stream()
                .filter(arena -> arena.getState() == GameState.WAITING)
                .forEach(arena -> votes.put(arena.getName(), new HashSet<>()));
    }

    public void addVote(UUID voter, String arena) {
        votes.computeIfPresent(arena, (k, v) -> {
            v.add(new ArenaVote(voter, SurvivalGamesAPI.getAPI()
                    .getArenaManager().getArena(arena)));
            return v;
        });
    }

    public void removeVote(UUID voter, String arena) {
        votes.computeIfPresent(arena, (k, v) -> {
            v.removeIf(arenaVote -> arenaVote.getVoter().equals(voter)
                    && arenaVote.getArena().getName().equalsIgnoreCase(arena));
            return v;
        });
    }


    public Set<ArenaVote> getVotesOf(String arenaName) {
        return votes.getOrDefault(arenaName, Collections.emptySet());
    }

    public SGArena getVotedArena() {

        Optional<String> arenaName;
        Optional<Integer> optional = votes.values().stream()
                .map((Set::size)).max((o, o2) -> o2-o);

        arenaName = !optional.isPresent()
                ? Optional.ofNullable(new ArrayList<>(votes.keySet())
                .get(ThreadLocalRandom.current().nextInt(votes.size())))
                : optional.map((max) -> {

            for(Map.Entry<String, Set<ArenaVote>> entry: votes.entrySet()) {
                if(entry.getValue().size() == max) {
                    return entry.getKey();
                }
            }
            return null;
        });

        return arenaName.map(name -> SurvivalGamesAPI.getAPI()
                .getArenaManager().getArena(name)).orElse(null);

    }

}
