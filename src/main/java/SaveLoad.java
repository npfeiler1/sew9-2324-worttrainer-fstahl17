public interface SaveLoad {
    void saveToJSON(Rechtschreibtrainer r, String dateiName);
    void loadFromJSON(Rechtschreibtrainer r, String dateiName);
}
