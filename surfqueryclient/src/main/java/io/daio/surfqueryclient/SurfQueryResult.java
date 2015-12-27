package io.daio.surfqueryclient;

public class SurfQueryResult {

    private long timestamp;
    private String date;
    private String time;
    private int wind;
    private int minSwell;
    private int maxSwell;
    private int solidStar;
    private int fadedStar;

    public SurfQueryResult(long timestamp, String date, String time,
                           int wind, int minSwell, int maxSwell,
                           int solidStar, int fadedStar) {

        this.timestamp = timestamp;
        this.date = date;
        this.time = time;
        this.wind = wind;
        this.minSwell = minSwell;
        this.maxSwell = maxSwell;
        this.solidStar = solidStar;
        this.fadedStar = fadedStar;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public int getWind() {
        return wind;
    }

    public int getMinSwell() {
        return minSwell;
    }

    public int getMaxSwell() {
        return maxSwell;
    }

    public int getSolidStar() {
        return solidStar;
    }

    public int getFadedStar() {
        return fadedStar;
    }
}
