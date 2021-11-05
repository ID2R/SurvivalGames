package dev.iiprocraft.sg.api;

public class SurvivalGamesAPI {

    private static final SurvivalGamesAPI API;

    static {
        API = new SurvivalGamesAPI();
    }

    public static SurvivalGamesAPI getAPI() {
        return API;
    }

}
