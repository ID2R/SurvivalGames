package dev.iiprocraft.sg.base.game.arena.vote;

import dev.iiprocraft.sg.base.game.arena.SGArena;
import dev.iiprocraft.sg.base.game.arena.SGArena.ArenaVote;
import dev.iiprocraft.sg.base.management.ArenaManager;
import lombok.Data;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Data
public class VoteManager {

    private final Map<String, Set<ArenaVote>> votes = new HashMap<>();

    private final ArenaManager arenaManager;

    public void addVote(UUID voter, String arena) {
        votes.computeIfPresent(arena, (k, v) -> {
            v.add(new ArenaVote(voter, arenaManager.getArena(arena)));
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

        return arenaName.map(arenaManager::getArena).orElse(null);

    }

}
