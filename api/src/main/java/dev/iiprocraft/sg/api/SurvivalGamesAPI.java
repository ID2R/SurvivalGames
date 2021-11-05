package dev.iiprocraft.sg.api;

public class SurvivalGamesAPI {

    private static SurvivalGamesAPI API;

    public static SurvivalGamesAPI getAPI() {
        return API == null ? API = new SurvivalGamesAPI() : API;
    }



}
