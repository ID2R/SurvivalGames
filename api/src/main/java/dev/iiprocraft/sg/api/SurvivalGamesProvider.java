package dev.iiprocraft.sg.api;

import lombok.Getter;

/**
 * @author DirectPlan
 */
public class SurvivalGamesProvider {

    @Getter private static SurvivalGames api;

    public static void registerApi(SurvivalGames api) {
        SurvivalGamesProvider.api = api;
    }

}
