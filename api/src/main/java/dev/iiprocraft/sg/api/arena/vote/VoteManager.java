package dev.iiprocraft.sg.api.arena.vote;

import dev.iiprocraft.sg.api.arena.SGArena.ArenaVote;

import java.util.*;

public class VoteManager {

    private final Map<String, Set<ArenaVote>> votes;

    public VoteManager() {
        votes = new LinkedHashMap<>();
    }

    public void addVote(UUID voter, String arena) {
        votes.putIfAbsent(arena, Set.of(new ArenaVote(voter, )));//TODO getArena from arena Manager
    }


    public Set<ArenaVote> getVotesOf(String arenaName) {
        return votes.getOrDefault(arenaName, Collections.emptySet());
    }
}
