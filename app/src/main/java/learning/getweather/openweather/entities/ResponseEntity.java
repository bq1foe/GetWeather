package learning.getweather.openweather.entities;

public class ResponseEntity {
    CoordEntity coord;
    WeatherEntity[] weather;
    String base;
    MainEntity main;
    WindEntity wind;
    CloudsEntity clouds;
    long dt;
    SysEntity sys;
    long id;
    String name;
    int cod;

    public CoordEntity getCoord() {
        return coord;
    }

    public void setCoord(CoordEntity coord) {
        this.coord = coord;
    }

    public WeatherEntity[] getWeather() {
        return weather;
    }

    public void setWeather(WeatherEntity[] weather) {
        this.weather = weather;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public MainEntity getMain() {
        return main;
    }

    public void setMain(MainEntity main) {
        this.main = main;
    }

    public WindEntity getWind() {
        return wind;
    }

    public void setWind(WindEntity wind) {
        this.wind = wind;
    }

    public CloudsEntity getClouds() {
        return clouds;
    }

    public void setClouds(CloudsEntity clouds) {
        this.clouds = clouds;
    }

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public SysEntity getSys() {
        return sys;
    }

    public void setSys(SysEntity sys) {
        this.sys = sys;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }
}
