package entity;

public class AccountGame {
        private int ingameId;
        private String userNameGame;
        private String passwordGame;
        private int price;
        private int status;
        private int gameId;

        public AccountGame() {
        }

        public AccountGame(int ingameId, String userNameGame, String passwordGame,int status, int price, int gameId) {
            this.ingameId = ingameId;
            this.userNameGame = userNameGame;
            this.passwordGame = passwordGame;
            this.price = price;
            this.status = status;
            this.gameId = gameId;
        }

        public int getIngameId() {
            return ingameId;
        }

        public void setIngameId(int ingameId) {
            this.ingameId = ingameId;
        }

        public String getUserNameGame() {
            return userNameGame;
        }

        public void setUserNameGame(String userNameGame) {
            this.userNameGame = userNameGame;
        }

        public String getPasswordGame() {
            return passwordGame;
        }

        public void setPasswordGame(String passwordGame) {
            this.passwordGame = passwordGame;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getGameId() {
            return gameId;
        }

        public void setGameId(int gameId) {
            this.gameId = gameId;
        }

    @Override
    public String toString() {
        return "AccountGame{" +
                "ingameId=" + ingameId +
                ", userNameGame='" + userNameGame + '\'' +
                ", passwordGame='" + passwordGame + '\'' +
                ", price=" + price +
                ", status=" + status +
                ", gameId=" + gameId +
                '}';
    }
}

