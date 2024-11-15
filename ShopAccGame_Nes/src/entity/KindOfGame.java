package entity;

public class KindOfGame {
        private int gameId;
        private String nameGame;
        private String platform;
        private String genre;

        public KindOfGame(int gameId, String nameGame, String platform, String genre) {
            this.gameId = gameId;
            this.nameGame = nameGame;
            this.platform = platform;
            this.genre = genre;
        }

        public int getGameId() {
            return gameId;
        }

        public void setGameId(int gameId) {
            this.gameId = gameId;
        }

        public String getNameGame() {
            return nameGame;
        }

        public void setNameGame(String nameGame) {
            this.nameGame = nameGame;
        }

        public String getPlatform() {
            return platform;
        }

        public void setPlatform(String platform) {
            this.platform = platform;
        }

        public String getGenre() {
            return genre;
        }

        public void setGenre(String genre) {
            this.genre = genre;
        }
    }

